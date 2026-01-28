package io.github.thirumalx.dao.attribute;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.model.attribute.ClientNameAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ClientNameAttributeDao extends AttributeDao<ClientNameAttribute> {

    protected ClientNameAttributeDao(JdbcClient jdbc) {
        super(jdbc, "certx.cl_nam_client_name", "cl_nam_cl_id", "cl_nam_client_name", "metadata_cl_nam");
    }

    @Override
    protected RowMapper<ClientNameAttribute> rowMapper() {
        return (rs, rowNum) -> ClientNameAttribute.builder()
                .id(rs.getLong("cl_nam_cl_id"))
                .name(rs.getString("cl_nam_client_name"))
                .metadata(rs.getLong("metadata_cl_nam"))
                .build();
    }
}
