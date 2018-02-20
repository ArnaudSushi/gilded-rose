package spring.services;

import fr.esiea.Item;

public interface ItemBuilder {
    Item build(String nom, int price, int quality);
}
