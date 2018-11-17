package com.ericandben.cs336.backendapp.likes;

import com.ericandben.cs336.backendapp.drinker.*;
import com.ericandben.cs336.backendapp.item.Item;

import java.io.Serializable;
import java.util.Objects;

class LikesKey implements Serializable{
   
    // This must be a String. Otherwise, there is an error on trying to add a new Likes tuple.
    // See https://stackoverflow.com/questions/29079263/how-to-use-idclass-annotation-with-composite-key
    // https://stackoverflow.com/questions/1468498/idclass-jpa-annotation
    private String item;

    // This should be a String too, but it seems to work as a Drinker
    private Drinker drinker;

    public LikesKey(){}
    public LikesKey(Drinker drinker, String item){
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
        LikesKey myId = (LikesKey) o;

        if((o instanceof LikesKey) 
                && (this.getDrinker().equals(myId.getDrinker()))
                && (this.getItem().equals(myId.getItem()))) {
            flag = true;
        }
        return flag;
    }
// rest of the code with getters only
}