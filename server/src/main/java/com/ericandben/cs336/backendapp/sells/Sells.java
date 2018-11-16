package com.ericandben.cs336.backendapp.sells;
import com.ericandben.cs336.backendapp.bar.Bar;
import com.ericandben.cs336.backendapp.item.Item;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.IdClass;

@Entity
@IdClass(SellsKey.class)
@Table(name = "Sells")
public class Sells {

    //@EmbeddedId
    //private SellsKey pkey;

    private double price;
    

    // TODO consider making a separate class for the key of Sells

    // private static final long serialVersionUID = 4L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bar") // name of column (in Sells table) that is foreign key into Bars table
    private Bar bar; // TODO should this be a string?

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
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


    /*
    public SellsKey getPkey() {
        return this.pkey;
    }

    public void setPkey(SellsKey pkey) {
        this.pkey = pkey;
    }
    */

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sells)) return false;
        Sells that = (Sells) o;
        return Objects.equals(getBarObj(), that.getBarObj()) &&
                Objects.equals(getItemObj(), that.getItemObj());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getBarObj(), getItemObj()); // TODO should we use the names instead?
    }
    */


}