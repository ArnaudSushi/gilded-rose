package fr.esiea;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;


public class GildedRoseTest {

    @Test
    public void notEmptyCreationTest() {
        Item pawntem = new Item("basic", 10, 10);

        ArrayList<Item> items = new ArrayList<>();
        items.add(pawntem);

        GildedRose gildedRose = new GildedRose(items);
        Assertions.assertThat(gildedRose.getItems()).as("Les objets vandables").isEqualTo(items);
    }

    @Test
    public void emptyCreationTest() {
        ArrayList<Item> items = new ArrayList<>();

        GildedRose gildedRose = new GildedRose();
        Assertions.assertThat(gildedRose.getItems()).as("La taverne").isEqualTo(items);
    }

    @Test
    public void addItemTest() {
        Item pawnItem = new Item("basicItem", 10, 10);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(pawnItem);

        GildedRose gildedRose = new GildedRose();
        gildedRose.addAnItem(pawnItem);
        Assertions.assertThat(gildedRose.getItems()).as("Les objets vandable").isEqualTo(items);
    }
}