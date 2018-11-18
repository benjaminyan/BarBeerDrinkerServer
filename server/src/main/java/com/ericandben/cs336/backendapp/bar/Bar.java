package com.ericandben.cs336.backendapp.bar;
import com.ericandben.cs336.backendapp.transaction.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ericandben.cs336.backendapp.drinker.*;
import com.ericandben.cs336.backendapp.sells.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table(name = "Bars")
public class Bar {
    @Id
    private String name;

    private String license;

    private String city;

    private String zip;

    private String state;

    private String addr;

    private String phone;

    private double tax;

    public Bar() { }
    public Bar(String name) {
        this.name = name;
    }

    // This maps the bar to its transactions
    /*
    @JsonIgnore
    @OneToMany(mappedBy = "pkey.bar") // pkey is a property of Transaction class
    private Set<Transaction> transactions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Frequents",
               joinColumns = {@JoinColumn(name = "bar")},
               inverseJoinColumns = {@JoinColumn(name = "drinker")})
    private Set<Drinker> drinkers;
    */

    // This map the bar to the items it sells
    
    @JsonIgnore
    @OneToMany(mappedBy = "bar") // pkey is a property of Sells class
    private Set<Sells> itemsSold;
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return this.license;
    }

    public void setLicense(String license) {
        this.license = license;
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

    public double getTax() {
        return this.tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }


    /*

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Drinker> getDrinkers() {
        return this.drinkers;
    }

    public void setDrinkers(Set<Drinker> drinkers) {
        this.drinkers = drinkers;
    }

    */

    /*
    public Set<Sells> getItemsSold() {
        return this.itemsSold;
    }

    public void setItemsSold(Set<Sells> itemsSold) {
        this.itemsSold = itemsSold;
    }
    */


}
