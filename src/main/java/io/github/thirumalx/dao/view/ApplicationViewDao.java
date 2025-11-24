package io.github.thirumalx.dao.view;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import io.github.thirumalx.dto.Application;

/**
 * @author Thirumal
 */
@Repository
public class ApplicationViewDao extends ViewDao<Application> {

    protected ApplicationViewDao(JdbcClient jdbc) {
        super(jdbc, "certx.lAP_Application", Application.class);
    }

    @Override
    protected RowMapper<Application> rowMapper() {
        return (rs, rowNum) -> Application.builder()
                .id(rs.getLong("AP_ID"))
                .applicationName(rs.getString("AP_NAM_Application_Name"))
                .build();
    }

}
