package com.ericandben.cs336.backendapp.drinker;
import com.ericandben.cs336.backendapp.item.Item;
import com.ericandben.cs336.backendapp.bar.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.Set;

import com.ericandben.cs336.backendapp.transaction.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // This maps the drinker to their transactions
    @JsonIgnore
    @OneToMany(mappedBy = "drinker") // name of the property in Transaction class
    private Set<Transaction> transactions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Likes",
               joinColumns={@JoinColumn(name = "drinker")},
               inverseJoinColumns = {@JoinColumn(name = "item")})
    private Set<Item> itemsLiked;
    
    @ManyToMany(mappedBy = "drinkers")
    private Set<Bar> barsFrequented;

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
    
    public Set<Item> getItemsLiked() {
        return this.itemsLiked;
    }

    public void setItemsLiked(Set<Item> itemsLiked) {
        this.itemsLiked = itemsLiked;
    }

    public Set<Bar> getBarsFrequented() {
        return this.barsFrequented;
    }

    public void setBarsFrequented(Set<Bar> barsFrequented) {
        this.barsFrequented = barsFrequented;
    }

}
