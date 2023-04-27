package com.github.budwing.java17.sealed;

import java.time.temporal.ValueRange;

public non-sealed class Autumn extends Season {
    @Override
    public ValueRange monthRange() {
        return ValueRange.of(7, 9);
    }
}
