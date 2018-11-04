package com.ericandben.cs336.backendapp.drinker;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

import com.ericandben.cs336.backendapp.transaction.*;

@Entity
@Table(name = "Drinkers")
public class Drinker {
    @Id
    private String name;

    private String city;

    private String zip;

    private String state;

    private String addr;

    private String phone;

    @OneToMany(mappedBy = "drinker") // name of the property in Transaction class
    private Set<Transaction> transactions;

    



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
    




}
