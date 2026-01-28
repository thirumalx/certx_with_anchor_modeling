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
import io.github.thirumalx.model.attribute.ClientStatusAttribute;

/**
 * @author Thirumal
 */
@Repository
public class ClientStatusAttributeDao extends AttributeDao<ClientStatusAttribute> {

    private final JdbcClient jdbc;

    protected ClientStatusAttributeDao(JdbcClient jdbc) {
        super(jdbc, "certx.cl_sta_client_status", "cl_sta_cl_id", "cl_sta_sta_id", "cl_sta_changedat",
                "metadata_cl_sta");
        this.jdbc = jdbc;
    }

    /**
     * Specialized insert for knotted attribute where the value is a Long (STA_ID)
     */
    public Map<String, Object> insert(Long anchorId, Long statusId, Instant changedAt, Long metadata) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.sql(
                "INSERT INTO certx.cl_sta_client_status (cl_sta_cl_id, cl_sta_sta_id, cl_sta_changedat, metadata_cl_sta) "
                        +
                        "VALUES (:id, :value, :changedAt, :metadata) RETURNING cl_sta_cl_id, cl_sta_changedat")
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
                .id(rs.getLong("cl_sta_cl_id"))
                .knotId(rs.getLong("cl_sta_sta_id"))
                .changedAt(rs.getObject("cl_sta_changedat", OffsetDateTime.class).toInstant())
                .metadata(rs.getLong("metadata_cl_sta"))
                .build();
    }
}
