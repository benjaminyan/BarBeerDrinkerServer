package com.ericandben.cs336.backendapp.transaction;
import com.ericandben.cs336.backendapp.bar.*;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Embeddable
public class TransactionKey implements Serializable {

    private static final long serialVersionUID = 4L;

    private Long tid;
    private String bar;

   
    public Long getTid() {
        return this.tid;
    }
    
    public void setTid(Long tid) {
        this.tid = tid;
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
        if (!(o instanceof TransactionKey)) return false;
        TransactionKey that = (TransactionKey) o;
        return Objects.equals(getTid(), that.getTid()) &&
                Objects.equals(getBar(), that.getBar());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTid(), getBar()); // TODO should we use the names instead?
    }

    public String toString() {
        return "";
        //return "TransactionKey [tid = " + this.tid + ", bar = " + this.getBar().getName() + "]"; 
    }


}