package io.github.thirumalx.dao.tie;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.TieDao;
import io.github.thirumalx.model.tie.ApplicationClient;

/**
 * @author Thirumal M
 * Data Access Object for the association between Clients and Applications.
 */
@Repository
public class ApplicationClientTieDao extends TieDao<ApplicationClient> {

    public ApplicationClientTieDao(JdbcClient jdbc) {
        super(jdbc, "application_client", "application_id", "client_id", "metadata_id", "changed_at");
    }

    @Override
    protected RowMapper<ApplicationClient> rowMapper() {
        return (rs, rowNum) -> {
            Long applicationId = rs.getObject("application_id") != null ? rs.getLong("application_id") : null;
            Long clientId = rs.getObject("client_id") != null ? rs.getLong("client_id") : null;
            Long metadataId = rs.getObject("metadata_id") != null ? rs.getLong("metadata_id") : null;
            Timestamp ts = rs.getTimestamp("changed_at");
            Instant changedAt = ts != null ? ts.toInstant() : null;
            return new ApplicationClient(applicationId, clientId, metadataId, changedAt);
        };
    }
    
}
