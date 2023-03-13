package _14_log_broker;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class LogBroker {

    private Map<Integer, RequestData> requestDataMap;
    private Queue<RequestData> timeOutCheckData;
    private Integer configuredTimeOut;
    private Set<Integer> duplicates;

    ReentrantLock lock = new ReentrantLock();

    public LogBroker() {
        requestDataMap = new HashMap<>();
        timeOutCheckData = new PriorityQueue<>(new SortByExpectedTimeOut());
        configuredTimeOut = Constants.configuredTimeOut;
        duplicates = new HashSet<>();
    }

    public void storeRequest(RequestData requestData) {
        lock.lock();
        try {
            if (duplicates.contains(requestData.id)) {
                return;
            }
            duplicates.add(requestData.id);
            System.out.println("Request Id: " + requestData.id + ", start time: " + requestData.startTime);
            if (!requestDataMap.containsKey(requestData.id)) {
                timeOutCheckData.add(requestData);
            }
            requestDataMap.putIfAbsent(requestData.id, requestData);
        } finally {
            lock.unlock();
        }
    }

    public void removeRequest(RequestData requestData) {

        lock.lock();
        try {
            if (requestDataMap.containsKey(requestData.id)) {
                System.out.println("Request Id: " + requestData.id + ", end time: " + requestData.endTime);
                if(requestData.endTime < requestData.startTime){
//                    half for
                    requestData.endTime = requestData.startTime + new Random().nextInt(2 * Constants.configuredTimeOut);
                }
                Integer difference = requestData.endTime - requestDataMap.get(requestData.id).startTime;
                if (difference > configuredTimeOut) {
                    System.out.println("remove alert:: Request Id: " + requestData.id + ", start time: " + requestDataMap.get(requestData.id).startTime + ", end time: " + requestData.endTime);
                }
                requestDataMap.remove(requestData.id);
            }
        } finally {
            lock.unlock();
        }
    }

    public void checkTimeout(Integer currentTime) {

        lock.lock();
        try {
            while (!timeOutCheckData.isEmpty()) {
                RequestData requestData = timeOutCheckData.peek();
                if (requestDataMap.get(requestData.id) == null) {
                    System.out.println(requestData.toString() + " is ignored"
                            + ", current time: " + currentTime);
                    timeOutCheckData.poll();
                } else if (currentTime >= requestData.expectedTimeOut) {
                    timeOutCheckData.poll();
                    if (requestDataMap.containsKey(requestData.id)) {
                        System.out.println("checkout alert:: Request Id: " + requestData.id
                                + ", start time: " + requestDataMap.get(requestData.id).startTime
                                + ", end time: " + requestData.endTime
                                + ", current time: " + currentTime);
                        requestDataMap.remove(requestData.id);
                    }
                } else {
                    System.out.println("checkout alert ignored:: Request Id: " + requestData.id
                            + ", start time: " + requestDataMap.get(requestData.id).startTime
                            + ", end time: " + requestData.endTime
                            + ", current time: " + currentTime);
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
