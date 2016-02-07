package se.crisp.edu.refactor.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private static final String SOME_ITEM_NAME = "Some item name";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACK_STAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURA = "Sulfuras, Hand of Ragnaros";
    private static final int PAST_SELL_DAY = -1;
    private static final int FUTURE_SELL = 20;
    private static final int MAX_QUALITY = 50;

    @Test
    public void given_a_not_specific_item_then_sell_in_days_decreases_by_1() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SOME_ITEM_NAME, 2, 0));
        new GildedRose(inventory);
        assertEquals(1, getSellInOfFirstItem(inventory));
    }

    @Test
    public void given_a_not_specific_item_then_quality_decreases_by_1() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SOME_ITEM_NAME, FUTURE_SELL, 1));
        new GildedRose(inventory);
        assertEquals(0, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_a_not_specific_item_then_quality_decreases_to_zero() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SOME_ITEM_NAME, FUTURE_SELL, 0));
        new GildedRose(inventory);
        assertEquals(0, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_a_not_specific_item__and_past_sell_date_then_quality_decreases_to_zero() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SOME_ITEM_NAME, PAST_SELL_DAY, 0));
        new GildedRose(inventory);
        assertEquals(0, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_a_not_specific_item_and_past_sell_date_then_quality_decreases_by_2() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SOME_ITEM_NAME, PAST_SELL_DAY, 7));
        new GildedRose(inventory);
        assertEquals(5, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_aged_brie_item_then_quality_increases_by_1() {
        TestInventory inventory = getTestInventoryWithItem(new Item(AGED_BRIE, FUTURE_SELL, 1));
        new GildedRose(inventory);
        assertEquals(2, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_aged_brie_item_then_quality_increases_to_50() {
        TestInventory inventory = getTestInventoryWithItem(new Item(AGED_BRIE, FUTURE_SELL, MAX_QUALITY));
        new GildedRose(inventory);
        assertEquals(MAX_QUALITY, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_aged_brie_item_and_past_sell_date_then_quality_increases_by_2() {
        TestInventory inventory = getTestInventoryWithItem(new Item(AGED_BRIE, PAST_SELL_DAY, 1));
        new GildedRose(inventory);
        assertEquals(3, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_aged_brie_item_and_past_sell_date_then_quality_increases_by_2_to_50() {
        TestInventory inventory = getTestInventoryWithItem(new Item(AGED_BRIE, PAST_SELL_DAY, MAX_QUALITY));
        new GildedRose(inventory);
        assertEquals(MAX_QUALITY, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_backstage_item_then_quality_increases_by_1() {
        TestInventory inventory = getTestInventoryWithItem(new Item(BACK_STAGE, FUTURE_SELL, 1));
        new GildedRose(inventory);
        assertEquals(2, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_backstage_item_and_within_10_days_then_quality_increases_by_2() {
        TestInventory inventory = getTestInventoryWithItem(new Item(BACK_STAGE, 10, 1));
        new GildedRose(inventory);
        assertEquals(3, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_backstage_item_and_within_5_days_then_quality_increases_by_3() {
        TestInventory inventory = getTestInventoryWithItem(new Item(BACK_STAGE, 5, 1));
        new GildedRose(inventory);
        assertEquals(4, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_backstage_item_and_past_sell_date_then_quality_is_zero() {
        TestInventory inventory = getTestInventoryWithItem(new Item(BACK_STAGE, PAST_SELL_DAY, 30));
        new GildedRose(inventory);
        assertEquals(0, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_sulfura_item_then_quality_never_changes() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SULFURA, FUTURE_SELL, 80));
        new GildedRose(inventory);
        assertEquals(80, getQualityOfFirstItem(inventory));
    }

    @Test
    public void given_sulfura_item_then_sell_date_never_changes() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SULFURA, FUTURE_SELL, 80));
        new GildedRose(inventory);
        assertEquals(FUTURE_SELL, getSellInOfFirstItem(inventory));
    }

    @Test
    public void given_sulfura_item__and_past_sell_date_then_quality_never_changes() {
        TestInventory inventory = getTestInventoryWithItem(new Item(SULFURA, PAST_SELL_DAY, 80));
        new GildedRose(inventory);
        assertEquals(80, getQualityOfFirstItem(inventory));
    }


    private int getSellInOfFirstItem(TestInventory inventory) {
        return inventory.getItems().get(0).getSellIn();
    }

    private int getQualityOfFirstItem(TestInventory inventory) {
        return inventory.getItems().get(0).getQuality();
    }

    private TestInventory getTestInventoryWithItem(Item item) {
        TestInventory inventory = new TestInventory();
        inventory.addItem(item);
        return inventory;
    }


}