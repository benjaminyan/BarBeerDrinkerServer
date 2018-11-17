package com.ericandben.cs336.backendapp.transaction;

import java.sql.Time;
import java.util.Date;
import com.ericandben.cs336.backendapp.drinker.*;
import com.ericandben.cs336.backendapp.bar.Bar;

import javax.persistence.AssociationOverride;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

@Entity
@IdClass(TransactionKey.class)
@Table(name = "Transactions")
public class Transaction {

    @Id
    @Column(name = "tid")
    private Long tid;

    @Id
    @ManyToOne
    @JoinColumn(name = "bar") // name of column (in Transactions table) that is foreign key into Bars table
    private Bar bar;

    @ManyToOne
    @JoinColumn(name = "drinker") // name of column (in Transactions table) that is foreign key into Drinkers table
    private Drinker drinker;

    private double amountPaid;
    private double tip;

    private Date dateTime;

    @Column(name = "t_time")
    private Time tTime;

    public Long getTid() {
        return this.tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }


    public Bar getBar() {
        return this.bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Drinker getDrinker() {
        return this.drinker;
    }

    public void setDrinker(Drinker drinker) {
        this.drinker = drinker;
    }

    public double getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getTip() {
        return this.tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public Date getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Time gettTime() {
        return this.tTime;
    }

    public void settTime(Time tTime) {
        this.tTime = tTime;
    }

}