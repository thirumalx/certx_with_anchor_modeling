package io.github.thirumalx.model;

import java.time.Instant;

/**
 * @author Thirumal
 * The HistorizedTie interface represents a historized association between two Anchors in our data model
 */
public non-sealed interface HistorizedTie extends Tie {

      Instant changedAt();
      
}
