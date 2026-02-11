package io.github.thirumalx.model;

/**
 * @author Thirumal
 * The Tie interface represents an association between two Anchors in our data model.
 * It serves as a marker interface to indicate that a class is a Tie, which is used to link related Anchors together. In our case, we will have a specific implementation of Tie called ApplicationClient that represents the association between a Client and an Application. This allows us to manage client-specific data within the context of an application, enabling us to maintain the integrity and relationships of our data model effectively.
 */
public sealed interface Tie permits HistorizedTie, SimpleTie {
    
    Long getAnchor1Id();
    Long getAnchor2Id();
    Long getMetadataId();

}
