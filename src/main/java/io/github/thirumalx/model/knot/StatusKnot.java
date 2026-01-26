package io.github.thirumalx.model.knot;

import io.github.thirumalx.model.Knot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/**
 * @author Thirumal
 */
@Data
@AllArgsConstructor
@Builder
public class StatusKnot implements Knot{
    
    private Long staId;
    private String staStatus;
    private Long metadataSta;

    @Override
    public Long getId() {
        return staId;
    }

    @Override
    public String description() {
        return staStatus;
    }

    @Override
    public Long getMetadata() {
        return metadataSta;
    }
}
