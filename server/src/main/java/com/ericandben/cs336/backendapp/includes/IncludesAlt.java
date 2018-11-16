package com.ericandben.cs336.backendapp.includes;
import com.ericandben.cs336.backendapp.transaction.Transaction;
import com.ericandben.cs336.backendapp.bar.Bar;
import com.ericandben.cs336.backendapp.item.Item;

import java.io.Serializable;
//import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.FetchType;

@Entity
@Table(name = "Includes")
@IdClass(IncludesKey.class)
public class IncludesAlt {

    //@EmbeddedId
    //private IncludesKey pkey; // Consists of a Transaction and an Item


    @Id
    private int tid;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bar") // name of column (in Sells table) that is foreign key into Bars table
    private Bar bar; // TODO should this be a string?

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item")
    private Item item; // TODO should this be a string?

    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL) // MUST have this or else we will get a compilation error (can't determine type...)
    // MUST have this, or else the generated SQL will be like "includes.transaction_bar" rather than "includes.bar"
    @JoinColumns({
        @JoinColumn(name = "tid", referencedColumnName="tid", insertable=false, updatable=false),
        @JoinColumn(name = "bar", referencedColumnName="bar", insertable=false, updatable=false)
    })
    private Transaction transaction;





    /*
    public IncludesKey getPkey() {
        return this.pkey;
    }

    public void setPkey(IncludesKey pkey) {
        this.pkey = pkey;
    }
    */

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "";
        //return "Includes [key = " + this.pkey.toString() + ", quantity = " + this.quantity + "]";
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