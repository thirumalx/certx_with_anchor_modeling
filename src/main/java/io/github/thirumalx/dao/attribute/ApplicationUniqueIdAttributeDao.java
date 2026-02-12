package io.github.thirumalx.dao.attribute;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AttributeDao;
import io.github.thirumalx.dao.columns.AttributeColumns;
import io.github.thirumalx.model.attribute.ApplicationUniqueIdAttribute;

/**
 * @author Thirumal
 */
@Repository
public class ApplicationUniqueIdAttributeDao extends AttributeDao<ApplicationUniqueIdAttribute> {

    protected ApplicationUniqueIdAttributeDao(JdbcClient jdbc) {
        super(jdbc, AttributeColumns.ApplicationUniqueId.TABLE, AttributeColumns.ApplicationUniqueId.FK, AttributeColumns.ApplicationUniqueId.VALUE,
                AttributeColumns.ApplicationUniqueId.CHANGED_AT, AttributeColumns.ApplicationUniqueId.METADATA);
    }

    @Override
    protected RowMapper<ApplicationUniqueIdAttribute> rowMapper() {
        return (rs, rowNum) -> new ApplicationUniqueIdAttribute(
                rs.getLong(AttributeColumns.ApplicationUniqueId.FK),
                rs.getString(AttributeColumns.ApplicationUniqueId.VALUE),
                rs.getLong(AttributeColumns.ApplicationUniqueId.METADATA),
                rs.getTimestamp(AttributeColumns.ApplicationUniqueId.CHANGED_AT).toInstant());
    }

}
