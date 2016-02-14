package se.crisp.edu.refactor.gildedrose;

import java.util.List;

public interface InventoryIf {
    List<Item> getItems();

    void addItem(Item item);
}
