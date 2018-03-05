package fr.esiea;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;

public class ItemTest {

    @Test
    public void creationTest() {
        Item creationTestItem = new Item("creationTestItem", 10, 10);
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(creationTestItem.name).as("Test object name").isEqualTo("creationTestItem");
        softAssert.assertThat(creationTestItem.sellIn).as("Test object sellIn").isEqualTo(10);
        softAssert.assertThat(creationTestItem.quality).as("Test object quality").isEqualTo(10);
        softAssert.assertAll();
    }

    @Test
    public void toStringTest() {
        Item stringTestItem = new Item("toStringTestItem", 10, 10);
        Assertions.assertThat(stringTestItem.toString()).as("Display of an object").isEqualTo("toStringTestItem, 10, 10");
    }

    @Test
    public void UpdateQualityNegativeSellInPositiveQualityDefault(){
        Item defaultItem = new Item("azerty", -1, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(defaultItem);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité de 'azerty' ").isEqualTo(8);
    }

    @Test
    public void UpdateQualityNegativeSellInNegativeQualityDefault(){
        Item defaultItem = new Item("azerty", -1, -1);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(defaultItem);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité de 'azerty' ").isEqualTo(-1);
    }

    @Test
    public void UpdateQualityDefaultSellIn(){
        Item defaultItem = new Item("azerty", 5, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(defaultItem);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.sellIn).as("nombre de jours pour vendre le produit").isEqualTo(4);
    }

    @Test
    public void decayOfDefaultObjectQualityByOne() {
        Item defaultItem = new Item("testObject", 5, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(defaultItem);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(defaultItem.quality).as("qualité du produit testé").isEqualTo(9);
    }

}