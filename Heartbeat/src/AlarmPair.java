public class AlarmPair {
    
    private String id;
    private String alarmType;
    
    public AlarmPair(String id, String alarmType) {
        this.id = id;
        this.alarmType = alarmType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    
}