package com.ericandben.cs336.backendapp.beer;
import com.ericandben.cs336.backendapp.item.Item;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Beers")
public class Beer extends Item {
    @Id
    private String name;

    private String manf;

    public String getManf() {
        return this.manf;
    }

    public void setManf(String manf) {
        this.manf = manf;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}