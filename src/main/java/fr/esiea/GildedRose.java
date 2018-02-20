package fr.esiea;

import java.util.ArrayList;
import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose() {
        items = new ArrayList<Item>();
    }

    public void addAnItem(Item newItem) {
        items.add(newItem);
    }

    public void updateQuality() {

        for (Item listBrowser : items) {
            listBrowser.updateQuality();
        }
    }
}