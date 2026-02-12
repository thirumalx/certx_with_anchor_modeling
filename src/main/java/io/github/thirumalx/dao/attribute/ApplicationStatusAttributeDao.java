package io.github.thirumalx.dao.attribute;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.dao.columns.AttributeColumns;
import io.github.thirumalx.model.attribute.ApplicationStatusAttribute;

/**
 * @author Thirumal
 */
@Repository
public class ApplicationStatusAttributeDao extends AttributeDao<ApplicationStatusAttribute> {

    private final JdbcClient jdbc;

    protected ApplicationStatusAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ApplicationStatus.TABLE, AttributeColumns.ApplicationStatus.FK, AttributeColumns.ApplicationStatus.VALUE, AttributeColumns.ApplicationStatus.CHANGED_AT,
                AttributeColumns.ApplicationStatus.METADATA);
        this.jdbc = jdbc;
    }

    /**
     * Specialized insert for knotted attribute where the value is a Long (STA_ID)
     */
    public Map<String, Object> insert(Long anchorId, Long statusId, Instant changedAt, Long metadata) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.sql(
                "INSERT INTO " + AttributeColumns.ApplicationStatus.TABLE + " (" + AttributeColumns.ApplicationStatus.FK + ", " + AttributeColumns.ApplicationStatus.VALUE + ", " + AttributeColumns.ApplicationStatus.CHANGED_AT + ", " + AttributeColumns.ApplicationStatus.METADATA + ") "
                        +
                        "VALUES (:id, :value, :changedAt, :metadata) RETURNING " + AttributeColumns.ApplicationStatus.FK + ", " + AttributeColumns.ApplicationStatus.CHANGED_AT)
                .param("id", anchorId)
                .param("value", statusId)
                .param("changedAt", java.sql.Timestamp.from(changedAt))
                .param("metadata", metadata)
                .update(keyHolder);
        return keyHolder.getKeys();
    }

    @Override
    protected RowMapper<ApplicationStatusAttribute> rowMapper() {
        return (rs, rowNum) -> ApplicationStatusAttribute.builder()
                .apStaApId(rs.getLong(AttributeColumns.ApplicationStatus.FK))
                .apStaStaId(rs.getLong(AttributeColumns.ApplicationStatus.VALUE))
                .apStaChangedAt(rs.getObject(AttributeColumns.ApplicationStatus.CHANGED_AT, OffsetDateTime.class).toInstant())
                .metadataApSta(rs.getLong(AttributeColumns.ApplicationStatus.METADATA))
                .build();
    }
}
