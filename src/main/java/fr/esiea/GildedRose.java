package fr.esiea;

import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;
import java.util.List;

public class GildedRose implements Runnable{
    private ItemPersistence items;
    
    //Default constructor with empty DB
    public GildedRose() {
        items = new HashMapPersistence();
    }
    
    //Constructor with given ItemPersistence DB
    public GildedRose(ItemPersistence items) {
        this.items = items;
    }
    
    
    public void addAnItem(Item newItem) {
        items.saveItem(newItem);
    }
    
    //Updates quality for each and every element of the DB
    public void updateQuality() {

        for (Item listBrowser : items) {
            listBrowser.updateQuality();
        }
    }
    
    //Returns a list with all the items in the DB
    public List<Item> getItems() {
        return items.getAllItems();
    }
    
    //When run as a thread, it simply updates the quality of every item
    @Override
    public void run() {
        updateQuality();
    }
}