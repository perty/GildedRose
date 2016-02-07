package se.crisp.edu.refactor.gildedrose;

import java.util.List;

public interface Inventory {
    List<Item> getItems();

    void addItem(Item item);
}
