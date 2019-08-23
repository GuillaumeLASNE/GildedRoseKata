package com.gildedrose;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final int MAXIMUM_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateOneItemQuality(items[i]);
        }
    }

    private void updateOneItemQuality(Item item) {
        if (SULFURAS.equals(item.name)) {
            return;
        }
        if (item.name.equals(AGED_BRIE)
                || item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality < MAXIMUM_QUALITY) {
                increaseQuality(item);

                if (item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.sellIn < 11) {
                        increaseQualitySafely(item);
                    }

                    if (item.sellIn < 6) {
                        increaseQualitySafely(item);
                    }
                }
            }
        } else {
            if (item.quality > 0) {
                decreaseQuality(item);
            }
        }

        if (!item.name.equals(SULFURAS)) {
            decreaseSellIn(item);
        }

        if (item.sellIn < 0) {
            if (item.name.equals(AGED_BRIE)) {
                increaseQualitySafely(item);
            } else {
                if (item.name.equals(BACKSTAGE_PASSES)) {
                    setQualityToZero(item);
                } else {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS)) {
                            decreaseQuality(item);
                        }
                    }
                }
            }
        }
    }

    private void setQualityToZero(Item item) {
        item.quality = 0;
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQuality(Item item) {
        item.quality = item.quality + 1;
    }

    private void increaseQualitySafely(Item item) {
        if (item.quality < MAXIMUM_QUALITY) {
            increaseQuality(item);
        }
    }

    private void decreaseQuality(Item item) {
        item.quality = item.quality - 1;
    }
}