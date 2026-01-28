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
import io.github.thirumalx.model.attribute.ApplicationStatusAttribute;

/**
 * @author Thirumal
 */
@Repository
public class ApplicationStatusAttributeDao extends AttributeDao<ApplicationStatusAttribute> {

    private final JdbcClient jdbc;

    protected ApplicationStatusAttributeDao(JdbcClient jdbc) {
        super(jdbc, "certx.ap_sta_application_status", "ap_sta_ap_id", "ap_sta_sta_id", "ap_sta_changedat",
                "metadata_ap_sta");
        this.jdbc = jdbc;
    }

    /**
     * Specialized insert for knotted attribute where the value is a Long (STA_ID)
     */
    public Map<String, Object> insert(Long anchorId, Long statusId, Instant changedAt, Long metadata) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.sql(
                "INSERT INTO certx.ap_sta_application_status (ap_sta_ap_id, ap_sta_sta_id, ap_sta_changedat, metadata_ap_sta) "
                        +
                        "VALUES (:id, :value, :changedAt, :metadata) RETURNING ap_sta_ap_id, ap_sta_changedat")
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
                .apStaApId(rs.getLong("ap_sta_ap_id"))
                .apStaStaId(rs.getLong("ap_sta_sta_id"))
                .apStaChangedAt(rs.getObject("ap_sta_changedat", OffsetDateTime.class).toInstant())
                .metadataApSta(rs.getLong("metadata_ap_sta"))
                .build();
    }
}
