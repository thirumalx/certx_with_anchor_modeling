package io.github.thirumalx.model;

public interface Attribute<T> {
    
    public static final Long METADATA_ACTIVE = 1L;

    Long getAnchorId();
    T getValue();
    Long getMetadataId();

}
