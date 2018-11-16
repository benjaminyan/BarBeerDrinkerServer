package com.ericandben.cs336.backendapp.softdrink;
import com.ericandben.cs336.backendapp.item.Item;
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

    @Override
    public int hashCode() {
        if (this.name == null) return 0;
        return Objects.hash(this.name);
    }
    
}