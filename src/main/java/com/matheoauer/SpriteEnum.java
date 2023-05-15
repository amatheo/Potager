package com.matheoauer;

/**
 * This class is to define the sprites of the vegetables
 * <p>It goes top to bottom and left to right</p>
 */
public enum SpriteEnum {
    WATERMELON(0),
    CUCUMBER(1),
    CORN(2),
    BANANA(3),
    APPLE(4),
    PEAR(5),
    PLUM(6),
    EGGPLANT(7),
    STRAWBERRY(8);

    private final int index;

    SpriteEnum(int index) {
        this.index = index;
    }

    public int getAtlasIndex() {
        return index;
    }
}
