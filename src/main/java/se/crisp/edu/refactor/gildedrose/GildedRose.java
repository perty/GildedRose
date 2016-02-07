package se.crisp.edu.refactor.gildedrose;

import java.util.List;


public class GildedRose {

    private Inventory inventory;

    public static void main(String[] args) {
        new GildedRose(new InventoryImpl());
    }

    public GildedRose(Inventory inventory) {
        this.inventory = inventory;
        System.out.println("OMGHAI!");

        updateQuality();
    }

    public void updateQuality() {
        List<Item> items = inventory.getItems();

        for (Item item : items) {
            ItemWrapper itemWrapper = ItemWrapper.getWrapper(item);
            itemWrapper.updateQuality();
            itemWrapper.updateSellIn();
        }
    }

    private abstract static class ItemWrapper {
        private static final int MAX_QUALITY = 50;

        private Item item;

        public static ItemWrapper getWrapper(Item item) {
            switch (item.getName()) {
                case SulfuraItem.name:
                    return new SulfuraItem(item);
                case AgedBrieItem.name:
                    return new AgedBrieItem(item);
                case BackStagePassItem.name:
                    return new BackStagePassItem(item);
            }
            return new OtherItem(item);
        }

        public ItemWrapper(Item item) {
            this.item = item;
        }

        public int getSellIn() {
            return item.getSellIn();
        }

        public void decreaseQuality() {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }

        public void decreaseSellIn() {
            item.setSellIn(item.getSellIn()  - 1);
        }

        public void increaseQuality() {
            if (item.getQuality() < MAX_QUALITY) {
                item.setQuality(item.getQuality() + 1);
            }
        }

        void worthLess() {
            item.setQuality(0);
        }

        public abstract void updateQuality();

        public abstract void updateSellIn();

        boolean pastSellDay() {
            return item.getSellIn() < 0;
        }
    }

    private static class SulfuraItem extends ItemWrapper {
        private static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
        public static final String name = SULFURAS_NAME;

        public SulfuraItem(Item item) {
            super(item);
            item.setQuality(80);
        }

        @Override
        public void updateQuality() {

        }

        @Override
        public void updateSellIn() {

        }
    }

    private static class AgedBrieItem extends ItemWrapper {
        private static final String AGED_BRIE_NAME = "Aged Brie";
        public static final String name = AGED_BRIE_NAME;

        public AgedBrieItem(Item item) {
            super(item);
        }

        @Override
        public void updateQuality() {
            increaseQuality();
        }

        @Override
        public void updateSellIn() {
            decreaseSellIn();
            if (pastSellDay()) {
                increaseQuality();
            }
        }
    }

    private static class BackStagePassItem extends ItemWrapper {
        private static final String BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert";
        public static final String name = BACKSTAGE_NAME;

        public BackStagePassItem(Item item) {
            super(item);
        }

        @Override
        public void updateQuality() {
            increaseQuality();
            if (getSellIn() < 11) {
                increaseQuality();
            }
            if (getSellIn() < 6) {
                increaseQuality();
            }
        }

        @Override
        public void updateSellIn() {
            decreaseSellIn();
            if (getSellIn() < 0) {
                worthLess();
            }
        }
    }

    private static class OtherItem extends ItemWrapper {

        public OtherItem(Item item) {
            super(item);
        }

        @Override
        public void updateQuality() {
            decreaseQuality();
        }

        @Override
        public void updateSellIn() {
            decreaseSellIn();
            if (pastSellDay()) {
                decreaseQuality();
            }
        }
    }
}