package io.github.thirumalx.model;
/**
 * @author Thirumal
 * The SimpleTie interface represents a non-historized association between two Anchors in our data model. It extends the Tie interface, indicating that it is a specific type of Tie that does not maintain historical changes or timestamps. This allows us to represent relationships between Anchors that do not require tracking of changes over time, providing a simpler way to manage associations in our data model when historical context is not necessary.
 */
public non-sealed interface SimpleTie extends Tie {

}
