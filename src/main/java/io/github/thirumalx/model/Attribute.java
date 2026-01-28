package io.github.thirumalx.model;
/**
 * @author Thirumal
 * An attribute is a value that is associated with an anchor.
 */
public sealed interface Attribute<T> permits HistorizedAttribute, SimpleAttribute{
    
    public static final Long METADATA_ACTIVE = 1L;

    Long getAnchorId();
    T getValue();
    Long getMetadataId();

}
