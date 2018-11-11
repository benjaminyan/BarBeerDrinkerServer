package com.ericandben.cs336.backendapp.likestest;
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


class LikesId implements Serializable{
   
    // This must be a String. Otherwise, there is an error on trying to add a new Likes tuple.
    // See https://stackoverflow.com/questions/29079263/how-to-use-idclass-annotation-with-composite-key
    // https://stackoverflow.com/questions/1468498/idclass-jpa-annotation
    private String item;

    // This should be a String too, but it seems to work as a Drinker
    private Drinker drinker;

    public LikesId(){}
    public LikesId(Drinker drinker, String item){
        this.drinker = drinker;
        this.item = item;
    }


    public Drinker getDrinker() {
        return this.drinker;
    }

    public void setDrinker(Drinker drinker) {
        this.drinker = drinker;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public int hashCode(){        
        return Objects.hash(getDrinker().getName(), getItem()); 
    }

    @Override
    public boolean equals(Object o){
        boolean flag = false;
        LikesId myId = (LikesId) o;

        if((o instanceof LikesId) 
                && (this.getDrinker().equals(myId.getDrinker()))
                && (this.getItem().equals(myId.getItem()))) {
            flag = true;
        }
        return flag;
    }
// rest of the code with getters only
}



@Entity
@IdClass(LikesId.class)
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