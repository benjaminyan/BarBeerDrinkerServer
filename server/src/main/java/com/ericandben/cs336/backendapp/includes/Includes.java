package com.ericandben.cs336.backendapp.includes;

import java.io.Serializable;
//import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;

@Entity
@Table(name = "Includes")
public class Includes {

    @EmbeddedId
    private IncludesKey pkey; // Consists of a TransactionKey and an Item

    private int quantity;


    public IncludesKey getPkey() {
        return this.pkey;
    }

    public void setPkey(IncludesKey pkey) {
        this.pkey = pkey;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Includes [key = " + this.pkey.toString() + ", quantity = " + this.quantity + "]";
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