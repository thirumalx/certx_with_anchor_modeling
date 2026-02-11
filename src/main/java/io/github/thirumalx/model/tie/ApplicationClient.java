package io.github.thirumalx.model.tie;

import java.time.Instant;

import io.github.thirumalx.model.HistorizedTie;
import lombok.Data;
/**
 * @author Thirumal M
 * Represents the association between a Client and an Application.
 * This tie allows us to link clients to their respective applications, enabling us to manage client-specific data within the context of an application.
 */
@Data
public class ApplicationClient implements HistorizedTie {

    private final Long applicationId; // Client ID
    private final Long clientId; // Application ID
    private final Long metadataId; // Metadata ID for additional information
    private final Instant changedAt; // Timestamp for when the tie was last changed

    public ApplicationClient(Long applicationId, Long clientId, Long metadataId, Instant changedAt) {
        this.applicationId = applicationId;
        this.clientId = clientId;
        this.metadataId = metadataId;
        this.changedAt = changedAt;
    }

    @Override
    public Long getAnchor1Id() {
        return applicationId;
    }

    @Override
    public Long getAnchor2Id() {
        return clientId;
    }

    @Override
    public Long getMetadataId() {
        return metadataId;
    }

    @Override
    public Instant changedAt() {
        return changedAt;
    }
  
}
