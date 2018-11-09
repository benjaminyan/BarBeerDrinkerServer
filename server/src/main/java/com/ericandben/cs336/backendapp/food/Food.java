package com.ericandben.cs336.backendapp.food;
import com.ericandben.cs336.backendapp.item.Item;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Foods")
@PrimaryKeyJoinColumn(name = "name")
public class Food extends Item {
    @Id
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    
}