package fr.esiea;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

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
}