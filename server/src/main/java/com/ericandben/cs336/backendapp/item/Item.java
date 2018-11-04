package com.ericandben.cs336.backendapp.item;

import com.ericandben.cs336.backendapp.drinker.Drinker;
import com.ericandben.cs336.backendapp.sells.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.Set;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    private String name;

    @ManyToMany(mappedBy = "itemsLiked")
    private Set<Drinker> likers;


    @OneToMany(mappedBy = "pkey.item") // TODO why does this work?
    private Set<Sells> barsSellingThis;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Drinker> getLikers() {
        return this.likers;
    }

    public void setLikers(Set<Drinker> likers) {
        this.likers = likers;
    }

    public Set<Sells> getBarsSellingThis() {
        return this.barsSellingThis;
    }

    public void setBarsSellingThis(Set<Sells> barsSellingThis) {
        this.barsSellingThis = barsSellingThis;
    }
    
}