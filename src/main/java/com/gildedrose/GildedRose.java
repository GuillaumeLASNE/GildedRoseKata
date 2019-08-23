package com.gildedrose;

class GildedRose {
    static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final int MAXIMUM_QUALITY = 50;
    private static final int MINIMUM_QUALITY = 0;
    private static final int TEN_DAYS_LEFT_UNTIL_CONCERT = 10;
    private static final int FIVE_DAYS_LEFT_UNTIL_CONCERT = 5;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateOneItemQuality(items[i]);
        }
    }

    void updateOneItemQuality(Item item) {
        if (SULFURAS.equals(item.name)) {
            return;
        }

        decreaseSellIn(item);

        if (item.name.equals(BACKSTAGE_PASSES)) {

            increaseQuality(item);

            if (item.sellIn < TEN_DAYS_LEFT_UNTIL_CONCERT) {
                increaseQuality(item);
            }

            if (item.sellIn < FIVE_DAYS_LEFT_UNTIL_CONCERT) {
                increaseQuality(item);
            }
        } else if (item.name.equals(AGED_BRIE)) {
            increaseQuality(item);
        } else {
            decreaseQuality(item);
        }


        if (item.sellIn < MINIMUM_QUALITY) {
             if (item.name.equals(BACKSTAGE_PASSES)) {
                setQualityToMinimum(item);
            } else if (item.name.equals(AGED_BRIE)) {
                increaseQuality(item);
            } else{
                decreaseQuality(item);
            }
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MINIMUM_QUALITY) {
            item.quality = item.quality - 1;
        }
    }

    private void setQualityToMinimum(Item item) {
        item.quality = MINIMUM_QUALITY;
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAXIMUM_QUALITY) {
            item.quality = item.quality + 1;
        }
    }

}