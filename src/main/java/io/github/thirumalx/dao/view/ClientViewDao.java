package io.github.thirumalx.dao.view;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dto.Client;

/**
 * @author Thirumal
 */
@Repository
public class ClientViewDao extends ViewDao<Client> {

    protected ClientViewDao(JdbcClient jdbc) {
        super(jdbc, Client.class);
    }

    public java.util.Optional<Client> findNowById(Long id) {
        return findById("certx.nCL_Client", "CL_ID", id);
    }

    public java.util.List<Client> listNow(Long status, int page, int size) {
        return jdbc.sql(
                "SELECT * FROM certx.nCL_Client WHERE cl_sta_sta_id = :status ORDER BY CL_ID LIMIT :limit OFFSET :offset")
                .param("status", status)
                .param("limit", size)
                .param("offset", page * size)
                .query(rowMapper())
                .list();
    }

    public long countNow(Long status) {
        return jdbc.sql("SELECT count(*) FROM certx.nCL_Client WHERE cl_sta_sta_id = :status")
                .param("status", status)
                .query(Long.class)
                .single();
    }

    @Override
    protected RowMapper<Client> rowMapper() {
        return (rs, rowNum) -> Client.builder()
                .id(rs.getLong("CL_ID"))
                .name(rs.getString("CL_NAM_Client_Name"))
                .email(rs.getString("CL_EID_Client_Email"))
                .mobileNumber(rs.getString("CL_MNO_Client_MobileNumber"))
                .status(rs.getString("CL_STA_STA_Status"))
                .build();
    }

}
