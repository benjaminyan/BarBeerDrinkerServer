package com.ericandben.cs336.backendapp.sells;
import com.ericandben.cs336.backendapp.bar.Bar;
import com.ericandben.cs336.backendapp.item.Item;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Bars")
public class Sells {
    @Id
    @ManyToOne
    @JoinColumn(name = "bar")
    private Bar barObj;


    @Id
    @ManyToOne
    @JoinColumn(name = "item")
    private Item itemObj;

    private double price;
    
    public Bar getBarObj() {
        return this.barObj;
    }

    public void setBarObj(Bar barObj) {
        this.barObj = barObj;
    }

    public Item getItemObj() {
        return this.itemObj;
    }

    public void setItemObj(Item itemObj) {
        this.itemObj = itemObj;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}