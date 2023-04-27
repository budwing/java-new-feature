package com.github.budwing.java17.sealed;

import java.time.temporal.ValueRange;

/**
 * Permitted subclasses have the following constraints:
 * -- must be accessible by the sealed class at compile time.
 * -- must directly extend the sealed class.
 * -- must have exactly one of the following modifiers to describe how it continues the sealing initiated by its superclass:
 *    ** final: Cannot be extended further
 *    ** sealed: Can only be extended by its permitted subclasses
 *    ** non-sealed: Can be extended by unknown subclasses; a sealed class cannot prevent its permitted subclasses from doing this
 * -- must be in the same module as the sealed class
 */
public sealed abstract class Season
        permits Autumn, Spring, Summer {
    public abstract ValueRange monthRange();
}
