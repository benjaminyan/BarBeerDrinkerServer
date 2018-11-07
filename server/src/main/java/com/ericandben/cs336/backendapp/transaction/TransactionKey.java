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

    @Column(name = "tid")
    private int tid;

    @ManyToOne
    @JoinColumn(name = "bar") // name of column (in Transactions table) that is foreign key into Bars table
    private Bar bar; // TODO should this be a string?

   
    public int getTid() {
        return this.tid;
    }

    
    public void setTid(int tid) {
        this.tid = tid;
    }

    public Bar getBar() {
        return this.bar;
    }

    
    public void setBar(Bar bar) {
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
        return "TransactionKey [tid = " + this.tid + ", bar = " + this.getBar().getName() + "]"; 
    }


}