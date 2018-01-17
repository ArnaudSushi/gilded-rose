package fr.esiea;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

    @Test
    public void creationTest() {
        Item testItem = new Item("creationTestItem", 10, 10);
        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(testItem.name).as("Test object name").isEqualTo("creationTestItem");
        softAssert.assertThat(testItem.sellIn).as("Test object sellIn").isEqualTo(10);
        softAssert.assertThat(testItem.quality).as("Test object quality").isEqualTo(10);
        softAssert.assertAll();
    }
}