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
import io.github.thirumalx.model.attribute.ClientStatusAttribute;

/**
 * @author Thirumal
 */
@Repository
public class ClientStatusAttributeDao extends AttributeDao<ClientStatusAttribute> {

    private final JdbcClient jdbc;

    protected ClientStatusAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ClientStatus.TABLE, AttributeColumns.ClientStatus.FK, AttributeColumns.ClientStatus.VALUE, AttributeColumns.ClientStatus.CHANGED_AT,
                AttributeColumns.ClientStatus.METADATA);
        this.jdbc = jdbc;
    }

    /**
     * Specialized insert for knotted attribute where the value is a Long (STA_ID)
     */
    public Map<String, Object> insert(Long anchorId, Long statusId, Instant changedAt, Long metadata) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.sql(
                "INSERT INTO " + AttributeColumns.ClientStatus.TABLE + " (" + AttributeColumns.ClientStatus.FK + ", " + AttributeColumns.ClientStatus.VALUE + ", " + AttributeColumns.ClientStatus.CHANGED_AT + ", " + AttributeColumns.ClientStatus.METADATA + ") "
                        +
                        "VALUES (:id, :value, :changedAt, :metadata) RETURNING " + AttributeColumns.ClientStatus.FK + ", " + AttributeColumns.ClientStatus.CHANGED_AT)
                .param("id", anchorId)
                .param("value", statusId)
                .param("changedAt", java.sql.Timestamp.from(changedAt))
                .param("metadata", metadata)
                .update(keyHolder);
        return keyHolder.getKeys();
    }

    @Override
    protected RowMapper<ClientStatusAttribute> rowMapper() {
        return (rs, rowNum) -> ClientStatusAttribute.builder()
                .id(rs.getLong(AttributeColumns.ClientStatus.FK))
                .knotId(rs.getLong(AttributeColumns.ClientStatus.VALUE))
                .changedAt(rs.getObject(AttributeColumns.ClientStatus.CHANGED_AT, OffsetDateTime.class).toInstant())
                .metadata(rs.getLong(AttributeColumns.ClientStatus.METADATA))
                .build();
    }
}
