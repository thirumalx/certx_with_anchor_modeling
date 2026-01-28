package io.github.thirumalx.dao.anchor;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dao.AnchorDao;
import io.github.thirumalx.model.anchor.ClientAnchor;

/**
 * @author Thirumal M
 */
@Repository
public class ClientAnchorDao extends AnchorDao<ClientAnchor> {

    protected ClientAnchorDao(JdbcClient jdbc) {
        super(jdbc, "certx.cl_client", "cl_id", "metadata_cl");
    }

    @Override
    protected RowMapper<ClientAnchor> rowMapper() {
        return (rs, rowNum) -> new ClientAnchor(rs.getLong("cl_id"), rs.getLong("metadata_cl"));
    }

}
