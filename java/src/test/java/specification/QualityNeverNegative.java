package specification;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class QualityNeverNegative extends TestUtility {

    public boolean isAboveOrEqualToZero(String itemName) {
        return getItem(itemName).getQuality() >= 0;
    }
}
