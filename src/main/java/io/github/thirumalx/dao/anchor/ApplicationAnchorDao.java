package io.github.thirumalx.dao.anchor;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AnchorDao;
import io.github.thirumalx.dao.columns.AnchorColumns;
import io.github.thirumalx.model.anchor.ApplicationAnchor;
/**
 * @author Thirumal M
 */
@Repository
public class ApplicationAnchorDao extends AnchorDao<ApplicationAnchor> {

    protected ApplicationAnchorDao(JdbcClient jdbc) {
        super(jdbc, AnchorColumns.Application.TABLE, AnchorColumns.Application.ID, AnchorColumns.Application.METADATA);
    }

    @Override
    protected RowMapper<ApplicationAnchor> rowMapper() {
        return (rs, rowNum) -> new ApplicationAnchor(rs.getLong(AnchorColumns.Application.ID), rs.getLong(AnchorColumns.Application.METADATA));
    }
    
}
