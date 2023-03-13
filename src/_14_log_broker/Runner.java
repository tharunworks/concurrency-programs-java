package _14_log_broker;

public class Runner {
    void init(){
        LogBroker logBroker = new LogBroker();
        Thread producer1 = new Thread(new LogStartProducer(logBroker));
        producer1.start();
//        Thread producer2 = new Thread(new LogProducer(logBroker));
//        producer2.start();
        Thread endProducer1 = new Thread(new LogEndProducer(logBroker));
        endProducer1.start();
        Thread logCheckoutRemover = new Thread(new LogCheckoutRemoverCron(logBroker));
        logCheckoutRemover.start();
    }
}
