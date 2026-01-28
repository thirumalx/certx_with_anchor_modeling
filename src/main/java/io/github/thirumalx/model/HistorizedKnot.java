package io.github.thirumalx.model;

import java.time.Instant;
/**
 * @author Thirumal
 * A historized knot is a knot that has a history.
 */
public non-sealed interface HistorizedKnot<T> extends Knot<T> {

    Instant changedAt();

}
