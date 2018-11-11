package com.ericandben.cs336.backendapp.item;

import com.ericandben.cs336.backendapp.drinker.Drinker;
import com.ericandben.cs336.backendapp.sells.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // This was important
@Table(name = "Items")
public class Item {
    @Id
    private String name;

    // Drinkers who like this item
    //@ManyToMany(mappedBy = "itemsLiked")
    //private Set<Drinker> likers;


    // Bars selling this item
    //@JsonIgnore
    //@OneToMany(mappedBy = "pkey.item") // pkey is a property of Sells
    //private Set<Sells> barsSellingThis;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
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
    */

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
    
    
}