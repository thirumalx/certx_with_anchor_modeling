package io.github.thirumalx.dao.attribute;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.dao.columns.AttributeColumns;
import io.github.thirumalx.model.attribute.ClientNameAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ClientNameAttributeDao extends AttributeDao<ClientNameAttribute> {

    protected ClientNameAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ClientName.TABLE, AttributeColumns.ClientName.FK, AttributeColumns.ClientName.VALUE, AttributeColumns.ClientName.METADATA);
    }

    @Override
    protected RowMapper<ClientNameAttribute> rowMapper() {
        return (rs, rowNum) -> ClientNameAttribute.builder()
                .id(rs.getLong(AttributeColumns.ClientName.FK))
                .name(rs.getString(AttributeColumns.ClientName.VALUE))
                .metadata(rs.getLong(AttributeColumns.ClientName.METADATA))
                .build();
    }
}
