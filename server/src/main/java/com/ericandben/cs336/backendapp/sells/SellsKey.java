package com.ericandben.cs336.backendapp.sells;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.item.*;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Embeddable
public class SellsKey implements Serializable {

    private static final long serialVersionUID = 4L;

    @ManyToOne
    @JoinColumn(name = "bar") // name of column (in Sells table) that is foreign key into Bars table
    private Bar bar; // TODO should this be a string?

    @ManyToOne
    @JoinColumn(name = "item")
    private Item item; // TODO should this be a string?

   
    public Bar getBar() {
        return this.bar;
    }
    
    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SellsKey)) return false;
        SellsKey that = (SellsKey) o;
        return Objects.equals(getBar(), that.getBar()) &&
                Objects.equals(getItem(), that.getItem());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getBar(), getItem()); // TODO should we use the names instead?
    }


}