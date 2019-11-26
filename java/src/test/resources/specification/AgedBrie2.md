
# Aged Brie just gets better

## [Example](- "aged-brie")

Say we have a brie "[Aged Brie](- "#itemName")".
It has a quality of [10](- "#itemQuality")
and should be sold in [5](- "#itemSellIn") days.
It is in the [inventory](- "setUpItem(#itemName, #itemQuality, #itemSellIn)").

However, after [15](- "#days") has [passed](- "updateQuality(#days)"),
the quality is still [higher](- "c:assertTrue=isHigher(#itemName, #itemQuality)").
