package io.github.thirumalx.dao.attribute;

import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.model.attribute.ClientEmailAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ClientEmailAttributeDao extends AttributeDao<ClientEmailAttribute> {

    protected ClientEmailAttributeDao(JdbcClient jdbc) {
        super(jdbc, "certx.cl_eid_client_email", "cl_eid_cl_id", "cl_eid_client_email", "cl_eid_changedat",
                "metadata_cl_eid");
    }

    @Override
    protected RowMapper<ClientEmailAttribute> rowMapper() {
        return (rs, rowNum) -> ClientEmailAttribute.builder()
                .id(rs.getLong("cl_eid_cl_id"))
                .email(rs.getString("cl_eid_client_email"))
                .changedAt(rs.getObject("cl_eid_changedat", OffsetDateTime.class).toInstant())
                .metadata(rs.getLong("metadata_cl_eid"))
                .build();
    }
}
