package specification;

import se.crisp.edu.refactor.gildedrose.GildedRose;
import se.crisp.edu.refactor.gildedrose.Item;

public class TestUtility {
    private static final String DONE = "done";
    protected final GildedRose gildedRose;

    public TestUtility() {
        gildedRose = new GildedRose();
    }

    public String updateQuality(String days) {
        for (int n = 0; n < Integer.parseInt(days); n++) {
            gildedRose.updateQuality();
        }
        return DONE;
    }

    public Item getItem(String itemName) {
        return gildedRose.getItem(itemName);
    }

    public Item setUpItem(String itemName, String itemQuality, String itemSellIn) {
        Item item = new Item(itemName, Integer.parseInt(itemSellIn), Integer.parseInt(itemQuality));
        gildedRose.addItem(item);
        return item;
    }
}
