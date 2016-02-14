package se.crisp.edu.refactor.gildedrose;


public class GildedRose {

    static final String AGED_BRIE = "Aged Brie";
    static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private InventoryIf inventory;

    public static void main(String[] args) {
        new GildedRose(new Inventory());
    }

    public GildedRose(InventoryIf inventory) {
        System.out.println("OMGHAI!");

        this.inventory = inventory;
        updateQuality();
    }

    public InventoryIf getInventory() {
        return inventory;
    }

    public void updateQuality() {
        for (Item item : inventory.getItems()) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        updateQuality(item);

        updateSellIn(item);
    }

    private void updateQuality(Item item) {
        switch (item.getName()) {
            case AGED_BRIE:
            case BACKSTAGE_PASSES:
                increaseQuality(item);
                if (BACKSTAGE_PASSES.equals(item.getName())) {
                    if (item.getSellIn() < 11) {
                        increaseQuality(item);
                    }
                    if (item.getSellIn() < 6) {
                        increaseQuality(item);
                    }
                }
                break;
            case SULFURAS:
                break;
            default:
                decreaseQuality(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!SULFURAS.equals(item.getName())) {
            item.setSellIn(item.getSellIn() - 1);
        }
        if (item.getSellIn() < 0) {
            handleExpired(item);
        }
    }

    private void handleExpired(Item item) {
        switch (item.getName()) {
            case AGED_BRIE:
                increaseQuality(item);
                break;
            case BACKSTAGE_PASSES:
                item.setQuality(0);
                break;
            case SULFURAS:
                break;
            default:
                decreaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        item.setQuality(Math.min(50, item.getQuality() + 1));
    }

    private void decreaseQuality(Item item) {
        item.setQuality(Math.max(0, item.getQuality() - 1));
    }
}