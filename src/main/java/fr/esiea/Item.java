package fr.esiea;

import java.util.Objects;

//Class for default or generic items, base for specific items implementation

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
    
    
    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    /*
     * Method to update the quality of an item
     */
    public void updateQuality(){
        if (quality > 0) quality--;
        sellIn--;
        if (sellIn < 0 && quality > 0) quality--;

    }
    
    /*
     * Two objects are considered equal when their name attribute is equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

}