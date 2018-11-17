package com.ericandben.cs336.backendapp.likes;
import com.ericandben.cs336.backendapp.drinker.*;
import com.ericandben.cs336.backendapp.item.Item;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;


@Entity
@IdClass(LikesKey.class)
@Table(name = "Likes")
public class Likes implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @ManyToOne
    @JoinColumn(name = "drinker")
    private Drinker drinker;

    @Id
    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;

    public Drinker getDrinker() {
        return this.drinker;
    }

    public void setDrinker(Drinker drinker) {
        this.drinker = drinker;
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
        if (!(o instanceof Likes)) return false;
        Likes that = (Likes) o;
        return Objects.equals(getDrinker(), that.getDrinker()) &&
                Objects.equals(getItem(), that.getItem());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getDrinker().getName(), getItem().getName()); // TODO should we use the names instead?
    }




}