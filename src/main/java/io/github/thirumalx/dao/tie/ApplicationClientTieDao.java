package io.github.thirumalx.dao.tie;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.TieDao;
import io.github.thirumalx.dao.columns.TieColumns;
import io.github.thirumalx.model.tie.ApplicationClient;

/**
 * @author Thirumal M
 * Data Access Object for the association between Clients and Applications.
 */
@Repository
public class ApplicationClientTieDao extends TieDao<ApplicationClient> {

    public ApplicationClientTieDao(JdbcClient jdbc) {
        super(jdbc, TieColumns.ApplicationClientServedby.TABLE, TieColumns.ApplicationClientServedby.ANCHOR1, TieColumns.ApplicationClientServedby.ANCHOR2, TieColumns.ApplicationClientServedby.METADATA, TieColumns.ApplicationClientServedby.CHANGED_AT);
    }

    @Override
    protected RowMapper<ApplicationClient> rowMapper() {
        return (rs, rowNum) -> {
            Long applicationId = rs.getObject(TieColumns.ApplicationClientServedby.ANCHOR1) != null ? rs.getLong(TieColumns.ApplicationClientServedby.ANCHOR1) : null;
            Long clientId = rs.getObject(TieColumns.ApplicationClientServedby.ANCHOR2) != null ? rs.getLong(TieColumns.ApplicationClientServedby.ANCHOR2) : null;
            Long metadataId = rs.getObject(TieColumns.ApplicationClientServedby.METADATA) != null ? rs.getLong(TieColumns.ApplicationClientServedby.METADATA) : null;
            Timestamp ts = rs.getTimestamp(TieColumns.ApplicationClientServedby.CHANGED_AT);
            Instant changedAt = ts != null ? ts.toInstant() : null;
            return new ApplicationClient(applicationId, clientId, metadataId, changedAt);
        };
    }
    
}
