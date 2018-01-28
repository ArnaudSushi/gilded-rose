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

    @Test
    public void decayOfDefaultObjectQualityByOne() {
        Item defaultItem = new Item("testObject", 5, 10);
        Item[] items = new Item[]{defaultItem};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité du produit testé").isEqualTo(9);
    }

    @Test
    public void UpdateQualityAgedBrieQuality(){
        Item defaultItem = new Item("Aged Brie", 5, 10);
        Item[] items = new Item[]{defaultItem};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité de Aged Brie").isEqualTo(11);
    }

    @Test
    public void UpdateQualityBackstageQuality(){
        Item defaultItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        Item[] items = new Item[]{defaultItem};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité de Backstage ").isEqualTo(13);
    }

    @Test
    public void UpdateQualityDefaultSellIn(){
        Item defaultItem = new Item("azerty", 5, 10);
        Item[] items = new Item[]{defaultItem};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.sellIn).as("nombre de jours pour vendre le produit").isEqualTo(4);
    }

    @Test
    public void UpdateQualitySulfurasSellIn(){
        Item defaultItem = new Item("Sulfuras, Hand of Ragnaros", 5, 10);
        Item[] items = new Item[]{defaultItem};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.sellIn).as("nombre de jours pour vendre Sulfuras, Hand of Ragnaros").isEqualTo(5);
    }
}