package com.ericandben.cs336.backendapp.hour;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
import java.time.Duration;
import javax.persistence.Basic;

@Entity
@Table(name = "Hours")
public class Hour {

    @EmbeddedId
    private HourKey pkey;

    @Basic
    private java.sql.Time opening;
    private java.sql.Time closing;

    public HourKey getPkey() {
        return this.pkey;
    }

    public void setPkey(HourKey pkey) {
        this.pkey = pkey;
    }

    public java.sql.Time getOpening() {
        return this.opening;
    }

    public void setOpening(java.sql.Time opening) {
        this.opening = opening;
    }

    public java.sql.Time getClosing() {
        return this.closing;
    }

    public void setClosing(java.sql.Time closing) {
        this.closing = closing;
    }
    

    


}