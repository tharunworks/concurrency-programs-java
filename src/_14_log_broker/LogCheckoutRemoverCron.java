package _14_log_broker;

public class LogCheckoutRemoverCron implements Runnable{

    private LogBroker logBroker;

    public LogCheckoutRemoverCron(LogBroker logBroker) {
        this.logBroker = logBroker;
    }

    @Override
    public void run() {
        Integer currentTime = 50;
        while (true){
            try {
                logBroker.checkTimeout(currentTime);
                currentTime += 5;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
