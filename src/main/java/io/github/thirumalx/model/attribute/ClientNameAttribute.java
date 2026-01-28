package io.github.thirumalx.model.attribute;

import io.github.thirumalx.model.SimpleAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thirumal M
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientNameAttribute implements SimpleAttribute<String> {

    private Long id;
    private String name;
    private Long metadata;

    @Override
    public Long getAnchorId() {
        return id;
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public Long getMetadataId() {
        return metadata;
    }

}
