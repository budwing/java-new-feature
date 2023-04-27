package com.github.budwing.java17.sealed;

import java.time.temporal.ValueRange;

public final class Spring extends Season {
    @Override
    public ValueRange monthRange() {
        return ValueRange.of(1,3);
    }
}
