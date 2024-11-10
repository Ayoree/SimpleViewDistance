package com.ayoree.simpleviewdistance.utility;

import com.ayoree.simpleviewdistance.Config;

public class ClampUtility {

    private static final int MAX_POSSIBLE = 32;
    private static final int MIN_POSSIBLE = 2;

    public int clampChunkValue(int amount) {
        amount = Math.min(MAX_POSSIBLE, amount);
        amount = Math.max(MIN_POSSIBLE, amount);
        amount = Math.max(Config.minDistance, amount);
        return amount;
    }
}
