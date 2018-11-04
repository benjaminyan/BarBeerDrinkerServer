package com.ericandben.cs336.backendapp.transaction;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @EmbeddedId
    private TransactionKey pkey;

    private String drinker;

    private double amountPaid;
    private double tip;

    private Date dateTime;

   
    public TransactionKey getPkey() {
        return this.pkey;
    }

    public void setPkey(TransactionKey pkey) {
        this.pkey = pkey;
    }

    @ManyToOne
    @JoinColumn(name = "drinker") // name of column (in Transactions table) that is foreign key into Drinkers table
    public String getDrinker() {
        return this.drinker;
    }

    public void setDrinker(String drinker) {
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



}