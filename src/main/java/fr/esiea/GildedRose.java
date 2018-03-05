package fr.esiea;

import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;
import java.util.List;

public class GildedRose implements Runnable{
    private ItemPersistence items;

    public GildedRose() {
        items = new HashMapPersistence();
    }

    public GildedRose(ItemPersistence items) {
        this.items = items;
    }

    public void addAnItem(Item newItem) {
        items.saveItem(newItem);
    }

    public void updateQuality() {

        for (Item listBrowser : items) {
            listBrowser.updateQuality();
        }
    }

    public List<Item> getItems() {
        return items.getAllItems();
    }

    @Override
    public void run() {
        updateQuality();
    }
}