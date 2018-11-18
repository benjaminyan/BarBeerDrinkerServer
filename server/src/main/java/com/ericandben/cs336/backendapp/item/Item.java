package com.ericandben.cs336.backendapp.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import java.util.Set;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // This was important
@Table(name = "Items")
public abstract class Item {
    @Id
    private String name;

    // Drinkers who like this item
    //@ManyToMany(mappedBy = "itemsLiked")
    //private Set<Drinker> likers;

    @Column(name = "ItemType")
    private String itemType;
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
        if (this.name == null) return 0;
        return Objects.hash(this.name);
    }

    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    
}