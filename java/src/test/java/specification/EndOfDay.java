package specification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import se.crisp.edu.refactor.gildedrose.GildedRose;
import se.crisp.edu.refactor.gildedrose.Item;

import static org.junit.Assert.assertEquals;

public class EndOfDay {
    private final GildedRose gildedRose;
    private Item item;

    public EndOfDay() {
        gildedRose = new GildedRose();
    }

    @When("a day has passed")
    public void aDayHasPassed() {
        gildedRose.updateQuality();
    }

    @Given("an item {string} on the shelf with quality {int} and sell in {int} days")
    public void anItemOnTheShelfWithQualityAndSellInDays(String itemName, int itemQuality, int itemSellIn) {
        this.item = new Item(itemName, itemSellIn, itemQuality);
        gildedRose.addItem(item);
    }

    @Then("the quality is down to {int}")
    public void theQualityIsDownTo(int itemQuality) {
        assertEquals(itemQuality, item.quality);
    }

    @And("sell in is {int} days")
    public void sellInIsDays(int itemSellin) {
        assertEquals(itemSellin, item.sellIn);
    }
}
