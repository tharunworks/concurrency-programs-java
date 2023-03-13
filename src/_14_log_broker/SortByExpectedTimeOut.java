package _14_log_broker;

import java.util.Comparator;

public class SortByExpectedTimeOut implements Comparator<RequestData> {
    @Override
    public int compare(RequestData o1, RequestData o2) {
        return o1.expectedTimeOut - o2.expectedTimeOut;
    }
}
