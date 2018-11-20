package com.ericandben.cs336.backendapp.frequents;
import com.ericandben.cs336.backendapp.bar.Bar;
import com.ericandben.cs336.backendapp.item.Item;
import com.ericandben.cs336.backendapp.drinker.Drinker;

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
@IdClass(FrequentsKey.class)
@Table(name = "Frequents")
public class Frequents {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drinker")
    private Drinker drinker; // TODO should this be a string?

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bar") // name of column (in Sells table) that is foreign key into Bars table
    private Bar bar; // TODO should this be a string?

   
    public Bar getBar() {
        return this.bar;
    }
    
    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Drinker getDrinker() {
        return this.drinker;
    }

    public void setDrinker(Drinker drinker) {
        this.drinker = drinker;
    }


}