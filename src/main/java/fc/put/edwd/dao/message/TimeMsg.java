package fc.put.edwd.dao.message;

import java.util.Calendar;

/**
 * Created by marcin on 22.10.16.
 */
public class TimeMsg {
    private Integer id;
    private Integer hour;
    private Integer minute;

    public TimeMsg(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        this.setHour(c.get(Calendar.HOUR_OF_DAY));
        this.setMinute(c.get(Calendar.MINUTE));
    }

    public TimeMsg(Integer hour, Integer minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public TimeMsg(Integer id, Integer hour, Integer minute) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeMsg timeMsg = (TimeMsg) o;

        if (!hour.equals(timeMsg.hour)) return false;
        return minute.equals(timeMsg.minute);

    }

    @Override
    public int hashCode() {
        int result = hour.hashCode();
        result = 31 * result + minute.hashCode();
        return result;
    }
}
