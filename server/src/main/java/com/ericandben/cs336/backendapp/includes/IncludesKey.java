package com.ericandben.cs336.backendapp.includes;
import com.ericandben.cs336.backendapp.transaction.*;
import com.ericandben.cs336.backendapp.item.*;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;

@Embeddable
public class IncludesKey implements Serializable {

    private static final long serialVersionUID = 4L;

    // TODO should we use a TransactionKey? Yes.

    // Note that in the TransactionKey class, the columns specified are "tid" and "bar".
    // This is because in the Transactions table, the relevant columns are called "tid" and "bar"
    // IncludesKey, however, is meant to be used with the Includes table. But it so happens that
    // in the Includes table, the relevant columns are also called "tid" and "bar". If they weren't,
    // we could override the column names specified in TransactionKey.

    private TransactionKey transactionKey;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item")
    private Item item; // TODO should this be a string?

   
    public TransactionKey getTransactionKey() {
        return this.transactionKey;
    }
    
    public void setTransactionKey(TransactionKey transactionKey) {
        this.transactionKey = transactionKey;
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
        if (!(o instanceof IncludesKey)) return false;
        IncludesKey that = (IncludesKey) o;
        return Objects.equals(getTransactionKey(), that.getTransactionKey()) &&
                Objects.equals(getItem(), that.getItem());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTransactionKey(), getItem().getName()); // TODO should we use the names instead?
    }

    public String toString() {
        return "IncludesKey [transactionkey = " + this.transactionKey.toString() + ", item = " + this.item.getName() + "]";
    }


}