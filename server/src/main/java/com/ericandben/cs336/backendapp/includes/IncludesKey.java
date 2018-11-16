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

public class IncludesKey implements Serializable {

    private static final long serialVersionUID = 4L;
    private static final Logger logger = LoggerFactory.getLogger(IncludesController.class);	


    // TODO should we use a TransactionKey? Yes.

    // Note that in the TransactionKey class, the columns specified are "tid" and "bar".
    // This is because in the Transactions table, the relevant columns are called "tid" and "bar"
    // IncludesKey, however, is meant to be used with the Includes table. But it so happens that
    // in the Includes table, the relevant columns are also called "tid" and "bar". If they weren't,
    // we could override the column names specified in TransactionKey.

    private TransactionKey transaction;
    private String item;


    public TransactionKey getTransaction() {
        return this.transaction;
    }

    public void setTransaction(TransactionKey tkey) {
        this.transaction = tkey;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncludesKey)) return false;
        IncludesKey that = (IncludesKey) o;
        return Objects.equals(getTransaction(), that.getTransaction()) &&
                Objects.equals(getItem(), that.getItem());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTransaction(), getItem()); // TODO should we use the names instead?
    }

    public String toString() {
        return "IncludesKey [transaction = " + getTransaction() + ", item = " + getItem() + "]";
    }


}