package io.github.thirumalx.dao.attribute;

import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.dao.columns.AttributeColumns;
import io.github.thirumalx.model.attribute.ClientMobileNumberAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ClientMobileNumberAttributeDao extends AttributeDao<ClientMobileNumberAttribute> {

    protected ClientMobileNumberAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ClientMobileNumber.TABLE, AttributeColumns.ClientMobileNumber.FK, AttributeColumns.ClientMobileNumber.VALUE,
                AttributeColumns.ClientMobileNumber.CHANGED_AT,
                AttributeColumns.ClientMobileNumber.METADATA);
    }

    @Override
    protected RowMapper<ClientMobileNumberAttribute> rowMapper() {
        return (rs, rowNum) -> ClientMobileNumberAttribute.builder()
                .id(rs.getLong(AttributeColumns.ClientMobileNumber.FK))
                .mobileNumber(rs.getString(AttributeColumns.ClientMobileNumber.VALUE))
                .changedAt(rs.getObject(AttributeColumns.ClientMobileNumber.CHANGED_AT, OffsetDateTime.class).toInstant())
                .metadata(rs.getLong(AttributeColumns.ClientMobileNumber.METADATA))
                .build();
    }
}
