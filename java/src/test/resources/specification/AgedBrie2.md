
# Aged Brie just gets better

## [Example](- "aged-brie")

Say we have a brie "[Aged Brie](- "#itemName")".
It has a quality of [10](- "#itemQuality")
and should be sold in [5](- "#itemSellIn") days.
It is in the [inventory](- "setUpItem(#itemName, #itemQuality, #itemSellIn)").

However, after [15](- "#days") has [passed](- "updateQuality(#days)"),
the quality is still [higher](- "c:assertTrue=isHigher(#itemName, #itemQuality)").


|[item] [] [Name](- "#itemName") | [Quality](- "#itemQuality") | [Sell In](- "#itemSellIn") |[Days](- "#days")| [New quality](- "?=getQuality(#itemName)")|
| -------------------------------| --------------------------- | ---------------------------|-----------------|------------------|
|Aged Brie             |10|5|15|10|

[item]: - "#item=setUpItem(#itemName, #itemQuality, #itemSellIn)" 