package specification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import se.crisp.edu.refactor.gildedrose.GildedRose;
import se.crisp.edu.refactor.gildedrose.Item;

import static org.junit.Assert.assertEquals;

public class QualityDegrades {
    private final GildedRose gildedRose;
    private Item item;

    public QualityDegrades() {
        gildedRose = new GildedRose();
    }

    @Given("there is an item {string} with quality {string} and due sell in {string}")
    public void thereIsAnItemWithQualityAndDueSellIn(String itemName, String itemQuality, String itemSellIn) {
        this.item = new Item(itemName, Integer.parseInt(itemSellIn), Integer.parseInt(itemQuality));
        gildedRose.addItem(item);
    }

    @And("{string} days has passed")
    public void daysHasPassed(String days) {
        for(int n = 0; n < Integer.parseInt(days); n++) {
            gildedRose.updateQuality();
        }
    }

    @Then("the quality will be {string}")
    public void theQualityWillBe(String itemQuality) {
        assertEquals(Integer.parseInt(itemQuality), item.quality);
    }
}
