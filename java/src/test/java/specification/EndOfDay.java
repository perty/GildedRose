package specification;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import se.crisp.edu.refactor.gildedrose.Item;

import static org.junit.Assert.assertEquals;

public class EndOfDay {
    private World world;

    public EndOfDay(World world) {
        this.world = world;
    }

    @When("a day has passed")
    public void aDayHasPassed() {
        world.getGildedRose().updateQuality();
    }

    @Given("an item {string} on the shelf with quality {int} and sell in {int} days")
    public void anItemOnTheShelfWithQualityAndSellInDays(String itemName, int itemQuality, int itemSellIn) {
        world.setItem(new Item(itemName, itemSellIn, itemQuality));
        world.getGildedRose().addItem(world.getItem());
    }

    @Then("the quality is down to {int}")
    public void theQualityIsDownTo(int itemQuality) {
        assertEquals(itemQuality, world.getItem().quality);
    }

    @And("sell in is {int} days")
    public void sellInIsDays(int itemSellin) {
        assertEquals(itemSellin, world.getItem().sellIn);
    }
}
