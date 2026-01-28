package io.github.thirumalx.dao.attribute;

import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.model.attribute.ClientMobileNumberAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ClientMobileNumberAttributeDao extends AttributeDao<ClientMobileNumberAttribute> {

    protected ClientMobileNumberAttributeDao(JdbcClient jdbc) {
        super(jdbc, "certx.cl_mno_client_mobilenumber", "cl_mno_cl_id", "cl_mno_client_mobilenumber",
                "cl_mno_changedat",
                "metadata_cl_mno");
    }

    @Override
    protected RowMapper<ClientMobileNumberAttribute> rowMapper() {
        return (rs, rowNum) -> ClientMobileNumberAttribute.builder()
                .id(rs.getLong("cl_mno_cl_id"))
                .mobileNumber(rs.getString("cl_mno_client_mobilenumber"))
                .changedAt(rs.getObject("cl_mno_changedat", OffsetDateTime.class).toInstant())
                .metadata(rs.getLong("metadata_cl_mno"))
                .build();
    }
}
