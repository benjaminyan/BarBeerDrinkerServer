package com.ericandben.cs336.backendapp.frequents;

import java.io.Serializable;
import java.util.Objects;

public class FrequentsKey implements Serializable {

    private static final long serialVersionUID = 4L;

    private String drinker;
    private String bar;

    public String getDrinker() {
        return this.drinker;
    }

    public void setDrinker(String drinker) {
        this.drinker = drinker;
    }

    public String getBar() {
        return this.bar;
    }
    
    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FrequentsKey)) return false;
        FrequentsKey that = (FrequentsKey) o;
        return Objects.equals(getDrinker(), that.getDrinker()) &&
                Objects.equals(getBar(), that.getBar());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getDrinker(), getBar());
    }

}