package _14_log_broker;

class RequestData {
    Integer id, startTime, endTime;

    Integer expectedTimeOut;

    public RequestData(Integer id, Integer startTime, Integer endTime) {
        this.id = id;
        this.startTime = startTime;
        this.expectedTimeOut = this.startTime + Constants.configuredTimeOut;
        this.endTime = endTime;
    }

    public void addStartRequestData(Integer id, Integer startTime) {
        this.id = id;
        this.startTime = startTime;
        this.expectedTimeOut = this.startTime + Constants.configuredTimeOut;
    }

    public void addEndRequestData(Integer id, Integer endTime) {
        this.id = id;
        this.endTime = endTime;
    }

    public RequestData() {
    }


    @Override
    public String toString() {
        return "RequestData{" +
                "Request Id: " + id +
                ", start time:" + startTime +
                ", end time=" + endTime +
                ", expected timeOut=" + expectedTimeOut +
                '}';
    }
}
