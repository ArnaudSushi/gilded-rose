package fr.esiea;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class BackstagePassTest {

    @Test
    public void updateQualityNegativeSellInBackstage(){
        Item backstagePass = new BackstagePass("Backstage passes to a TAFKAL80ETC concert", -1, 10);
        Item[] items = new Item[]{backstagePass};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(backstagePass.quality).as("qualité de Backstage ").isEqualTo(0);
    }

    @Test
    public void updateQualityBackstageQuality(){
        Item backstagePass = new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        Item[] items = new Item[]{backstagePass};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        Assertions.assertThat(backstagePass.quality).as("qualité de Backstage ").isEqualTo(13);
    }

}