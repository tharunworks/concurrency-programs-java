package _14_log_broker;

import java.util.Random;

public class LogStartProducer implements Runnable {

    private LogBroker logBroker;

    public LogStartProducer(LogBroker logBroker) {
        this.logBroker = logBroker;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            RequestData requestData = new RequestData();
            requestData.addStartRequestData(new Random().nextInt(20), i + 20);
            logBroker.storeRequest(requestData);
        }
    }
}
