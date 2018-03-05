package fr.esiea;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import persistence.items.HashMapPersistence;
import persistence.items.ItemPersistence;

public class SulfurasTest {

    @Test
    public void UpdateQualityNegativeSellInPositiveQualitySulfuras(){
        Item sulfuras = new Sulfuras("Sulfuras, Hand of Ragnaros", -1, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(sulfuras);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(sulfuras.quality).as("qualité de Sulfuras ").isEqualTo(10);
    }

    @Test
    public void UpdateQualitySulfurasSellIn(){
        Item sulfuras = new Sulfuras("Sulfuras, Hand of Ragnaros", 5, 10);
        ItemPersistence items = new HashMapPersistence();
        items.saveItem(sulfuras);
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(sulfuras.sellIn).as("nombre de jours pour vendre Sulfuras, Hand of Ragnaros").isEqualTo(5);
    }

}