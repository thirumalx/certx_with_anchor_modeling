package io.github.thirumalx.dao.attribute;

import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.dao.columns.AttributeColumns;
import io.github.thirumalx.model.attribute.ApplicationNameAttribute;

/**
 * @author Thirumal M
 */
@Repository
public class ApplicationNameAttributeDao extends AttributeDao<ApplicationNameAttribute> {

    protected ApplicationNameAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ApplicationName.TABLE, AttributeColumns.ApplicationName.FK, AttributeColumns.ApplicationName.VALUE, AttributeColumns.ApplicationName.CHANGED_AT,
                AttributeColumns.ApplicationName.METADATA);
    }

    @Override
    protected RowMapper<ApplicationNameAttribute> rowMapper() {
        return (rs, rowNum) -> new ApplicationNameAttribute(
                rs.getLong(AttributeColumns.ApplicationName.FK),
                rs.getString(AttributeColumns.ApplicationName.VALUE),
                rs.getObject(AttributeColumns.ApplicationName.CHANGED_AT, OffsetDateTime.class).toInstant(),
                rs.getLong(AttributeColumns.ApplicationName.METADATA));
    }
}
