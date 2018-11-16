package com.ericandben.cs336.backendapp.includes;
import com.ericandben.cs336.backendapp.transaction.*;
import com.ericandben.cs336.backendapp.item.*;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
public class IncludesKeyOld implements Serializable {

    private static final long serialVersionUID = 4L;
    private static final Logger logger = LoggerFactory.getLogger(IncludesController.class);	


    // TODO should we use a TransactionKey? Yes.

    // Note that in the TransactionKey class, the columns specified are "tid" and "bar".
    // This is because in the Transactions table, the relevant columns are called "tid" and "bar"
    // IncludesKey, however, is meant to be used with the Includes table. But it so happens that
    // in the Includes table, the relevant columns are also called "tid" and "bar". If they weren't,
    // we could override the column names specified in TransactionKey.

    @ManyToOne(cascade = CascadeType.ALL) // MUST have this or else we will get a compilation error (can't determine type...)
    // MUST have this, or else the generated SQL will be like "includes.transaction_bar" rather than "includes.bar"
    @JoinColumns({
        @JoinColumn(name = "tid", referencedColumnName="tid"),
        @JoinColumn(name = "bar", referencedColumnName="bar")
    })
    private TransactionOld transaction;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item")
    private Item item; // TODO should this be a string?

   
    public TransactionOld getTransaction() {
        return this.transaction;
    }
    
    public void setTransaction(TransactionOld transaction) {
        this.transaction = transaction;
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
        if (!(o instanceof IncludesKeyOld)) return false;
        IncludesKeyOld that = (IncludesKeyOld) o;
        return Objects.equals(getTransaction(), that.getTransaction()) &&
                Objects.equals(getItem(), that.getItem());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTransaction().getPkey().hashCode(), getItem().getName()); // TODO should we use the names instead?
    }

    public String toString() {
        return "IncludesKey [transaction = " + this.transaction.toString() + ", item = " + this.item.getName() + "]";
    }


}