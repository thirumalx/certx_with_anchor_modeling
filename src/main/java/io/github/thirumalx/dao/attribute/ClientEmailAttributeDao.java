package io.github.thirumalx.dao.attribute;

import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.dao.columns.AttributeColumns;
import io.github.thirumalx.model.attribute.ClientEmailAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ClientEmailAttributeDao extends AttributeDao<ClientEmailAttribute> {

    protected ClientEmailAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ClientEmail.TABLE, AttributeColumns.ClientEmail.FK, AttributeColumns.ClientEmail.VALUE, AttributeColumns.ClientEmail.CHANGED_AT,
                AttributeColumns.ClientEmail.METADATA);
    }

    @Override
    protected RowMapper<ClientEmailAttribute> rowMapper() {
        return (rs, rowNum) -> ClientEmailAttribute.builder()
                .id(rs.getLong(AttributeColumns.ClientEmail.FK))
                .email(rs.getString(AttributeColumns.ClientEmail.VALUE))
                .changedAt(rs.getObject(AttributeColumns.ClientEmail.CHANGED_AT, OffsetDateTime.class).toInstant())
                .metadata(rs.getLong(AttributeColumns.ClientEmail.METADATA))
                .build();
    }
}
