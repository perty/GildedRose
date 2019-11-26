package specification;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class TimePassedSteps {
    private final World world;

    public TimePassedSteps(World world) {
        this.world = world;
    }

    @And("{int} days has passed")
    public void daysHasPassed(int days) {
        for (int n = 0; n < days; n++) {
            world.getGildedRose().updateQuality();
        }
    }

    @When("a day has passed")
    public void aDayHasPassed() {
        world.getGildedRose().updateQuality();
    }

}
