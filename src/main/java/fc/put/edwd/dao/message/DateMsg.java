package fc.put.edwd.dao.message;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;

public class DateMsg {
    private Integer id;
    private Integer year;
    private Integer month;
    private Integer day;

    public DateMsg(Long date){
        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(Instant.ofEpochSecond(date)));
        this.setYear(c.get(Calendar.YEAR));
        this.setMonth(c.get(Calendar.MONTH));
        this.setDay(c.get(Calendar.DAY_OF_WEEK));
    }

    public DateMsg(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        DateMsg o = (DateMsg)obj;
        if (this.year.intValue() == o.getYear().intValue()
            && this.month.intValue() == o.getMonth().intValue()
            && this.day.intValue() == o.getDay().intValue()){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.getYear().hashCode() + this.getMonth().hashCode() + this.getDay().hashCode();
    }
}
