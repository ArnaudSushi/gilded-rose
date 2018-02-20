package spring.services;

import fr.esiea.AgedBrie;
import fr.esiea.BackstagePass;
import fr.esiea.Item;
import fr.esiea.Sulfuras;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public enum ItemType {
    AGED_BRIE(AgedBrie::new), BACKSTAGE(BackstagePass::new), SULFURAS(Sulfuras::new);

    private final ItemBuilder builder;

    ItemType(ItemBuilder builder) {
        this.builder = builder;
    }

    public Item build(String name, int sellIn, int quality) {
        return builder.build(name, sellIn, quality);
    }

    //Return the values of the enum
    public static List<String> listTypes(){
        Class cls = ItemType.class;
        List<String> types = new ArrayList<>();
        for(Object o: cls.getEnumConstants()) {
            types.add(o.toString());
        }
        return types;
    }
}
