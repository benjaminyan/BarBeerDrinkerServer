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

public class IncludesKeyAlt implements Serializable {

    private static final long serialVersionUID = 4L;
    private static final Logger logger = LoggerFactory.getLogger(IncludesController.class);	


    // TODO should we use a TransactionKey? Yes.

    // Note that in the TransactionKey class, the columns specified are "tid" and "bar".
    // This is because in the Transactions table, the relevant columns are called "tid" and "bar"
    // IncludesKey, however, is meant to be used with the Includes table. But it so happens that
    // in the Includes table, the relevant columns are also called "tid" and "bar". If they weren't,
    // we could override the column names specified in TransactionKey.


    private Long tid; // TODO consider replacing tid and bar with a TransactionKey reference
    private String bar;
    private String item;


    public Long getTid() {
        return this.tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getBar() {
        return this.bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
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
        if (!(o instanceof IncludesKeyAlt)) return false;
        IncludesKeyAlt that = (IncludesKeyAlt) o;
        return Objects.equals(getTid(), that.getTid()) &&
                Objects.equals(getBar(), that.getBar()) &&
                Objects.equals(getItem(), that.getItem());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getTid(), getBar(), getItem()); // TODO should we use the names instead?
    }

    public String toString() {
        return "IncludesKey [tid = " + getTid() + ", bar = " + getBar() + ", item = " + getItem() + "]";
    }


}