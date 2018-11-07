package com.ericandben.cs336.backendapp.hour;
import com.ericandben.cs336.backendapp.bar.*;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Objects;


@Embeddable
public class HourKey implements Serializable {

    private static final long serialVersionUID = 4L;

    @ManyToOne
    @JoinColumn(name = "bar")
    private Bar bar;

    private int weekday;

    public Bar getBar() {
        return this.bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public int getWeekday() {
        return this.weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourKey)) return false;
        HourKey that = (HourKey) o;
        return Objects.equals(getBar(), that.getBar()) && Objects.equals(getWeekday(),that.getWeekday());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getBar(),getWeekday()); // TODO should we use the names instead?
    }
}
