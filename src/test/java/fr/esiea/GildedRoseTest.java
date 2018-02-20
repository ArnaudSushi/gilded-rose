package fr.esiea;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class GildedRoseTest {

    @Test
    public void creationTest() {
        Item pawnItem = new Item("basicItem", 10, 10);
        Item[] items = new Item[]{pawnItem};
        GildedRose gildedRose = new GildedRose(items);
        Assertions.assertThat(gildedRose.items).as("Les objets vandable").isEqualTo(items);
    }




















}