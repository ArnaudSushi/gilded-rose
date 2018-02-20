package fr.esiea;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    private List<Item> items;

    public GildedRose() {
        items = new ArrayList<Item>();
    }

    public GildedRose(ArrayList<Item> items) {
        this.items = items;
    }

    public void addAnItem(Item newItem) {
        items.add(newItem);
    }

    public void updateQuality() {

        for (Item listBrowser : items) {
            listBrowser.updateQuality();
        }
    }

    public List<Item> getItems() {
        return items;
    }
}