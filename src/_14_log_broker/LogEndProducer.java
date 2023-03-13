package _14_log_broker;

import java.util.Random;

public class LogEndProducer implements Runnable {

    private LogBroker logBroker;

    public LogEndProducer(LogBroker logBroker) {
        this.logBroker = logBroker;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            RequestData requestData = new RequestData();
            requestData.addEndRequestData(new Random().nextInt(20), 30 + i);
            logBroker.removeRequest(requestData);

            Integer isCleanTrigger = new Random().nextInt(10);
//            if(isCleanTrigger < 2){
//                logBroker.
//            }
        }
    }
}
