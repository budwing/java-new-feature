package com.github.budwing.java17.sealed;

import java.time.temporal.ValueRange;

/**
 * The permits clause can be omitted if its permitted classes are defined in the same file.
 */
public abstract sealed class Summer extends Season {
}

/**
 * the class can not be public
 */

final class EarlySummer extends Summer {
    @Override
    public ValueRange monthRange() {
        return ValueRange.of(4, 4);
    }
}

final class MidSummer extends Summer {
    @Override
    public ValueRange monthRange() {
        return ValueRange.of(5, 5);
    }
}

final class LateSummer extends Summer {
    @Override
    public ValueRange monthRange() {
        return ValueRange.of(6, 6);
    }
}
