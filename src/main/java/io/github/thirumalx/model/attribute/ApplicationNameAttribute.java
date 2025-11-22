package io.github.thirumalx.model.attribute;

import io.github.thirumalx.model.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/**
 * @author Thirumal M
 */
@Data
@Builder
@AllArgsConstructor
public class ApplicationNameAttribute implements Attribute<String> {
    
    private Long apNamApId;
    private String apNamApplicationName;
    private  Long metadataApNam;

    @Override
    public Long getAnchorId() {
        return apNamApId;
    }

    @Override
    public String getValue() {
        return apNamApplicationName;
    }
    
    @Override
    public Long getMetadataId() {
        return metadataApNam;
    }    
}
