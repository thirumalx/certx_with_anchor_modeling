package io.github.thirumalx.model.attribute;

import java.time.Instant;

import io.github.thirumalx.model.HistorizedAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Thirumal
 *         A knotted historized attribute representing the application status.
 */
@Data
@AllArgsConstructor
@Builder
public class ApplicationStatusAttribute implements HistorizedAttribute<Long> {

    private Long apStaApId;
    private Long apStaStaId;
    private Instant apStaChangedAt;
    private Long metadataApSta;

    @Override
    public Long getAnchorId() {
        return apStaApId;
    }

    @Override
    public Long getValue() {
        return apStaStaId;
    }

    @Override
    public Long getMetadataId() {
        return metadataApSta;
    }

    @Override
    public Instant changedAt() {
        return apStaChangedAt;
    }
}
