package com.ericandben.cs336.backendapp.item;

import com.ericandben.cs336.backendapp.drinker.Drinker;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    private String name;

    @ManyToMany(mappedBy = "itemsLiked")
    private Set<Drinker> likers;
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}