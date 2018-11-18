package com.ericandben.cs336.backendapp.softdrink;
import com.ericandben.cs336.backendapp.item.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;

@Entity
@Table(name = "SoftDrinks")
@PrimaryKeyJoinColumn(name = "name")
public class SoftDrink extends Item {
    @Id
    private String name;

    /* Had to change it to sManf because when querying for beers manf, it was picking this classes manf by mistake.
    */
    @Column(name = "manf")
    private String sManf;

    public String getSManf() {
        return this.sManf;
    }

    public void setSManf(String sManf) {
        this.sManf = sManf;
    }

    /*public String getManf() {
        return this.manf;
    }

    public void setManf(String manf) {
        this.manf = manf;
    }*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        if (this.name == null) return 0;
        return Objects.hash(this.name);
    }
    
}