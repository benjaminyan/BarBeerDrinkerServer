package com.ericandben.cs336.backendapp.transaction;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Embeddable
public class TransactionKey implements Serializable {

    private static final long serialVersionUID = 4L;

    private int tid;

    private String bar;

   
    public int getTid() {
        return this.tid;
    }

    @Column(name = "tid")
    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getBar() {
        return this.bar;
    }

    @ManyToOne
    @JoinColumn(name = "bar") // name of column (in Transactions table) that is foreign key into Bars table
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
        return Objects.hash(getTid(), getBar());
    }


}