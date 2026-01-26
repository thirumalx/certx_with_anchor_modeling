package io.github.thirumalx.model;
/**
 * @author Thirumal
 * A knot represents a finite set of values used to describe states of entities or relationships.
 */
public interface Knot {
    
    public static final Long DELETED = 0L;
    
    Long getId();
    String description();
    Long getMetadata();
}