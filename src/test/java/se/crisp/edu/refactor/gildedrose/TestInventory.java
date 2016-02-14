package se.crisp.edu.refactor.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class TestInventory implements InventoryIf {

    List<Item> items = new ArrayList<>();

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }
}
