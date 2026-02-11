package io.github.thirumalx.dao;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;

import io.github.thirumalx.model.Tie;

/**
 * @author Thirumal M
 * Tie tables usually contain only foreign keys referencing two anchor tables to establish a many-to-many relationship. 
 */
public abstract class TieDao<T extends Tie> {
    private final JdbcClient jdbc;
    private final String tableName;
    private final String[] columns;

    // Commonly used column names
    private final String anchor1Column;
    private final String anchor2Column;
    private final String metadataColumn; // may be null for plain ties
    private final String changedAtColumn; // may be null for simple ties

    protected TieDao(JdbcClient jdbc, String tableName, String... columns) {
        this.jdbc = jdbc;
        this.tableName = tableName;
        this.columns = columns;

        if (columns == null || columns.length < 2) {
            throw new IllegalArgumentException("Tie must have at least two columns for anchors");
        }

        this.anchor1Column = columns[0];
        this.anchor2Column = columns[1];
        this.metadataColumn = columns.length > 2 ? columns[2] : null;
        this.changedAtColumn = columns.length > 3 ? columns[3] : null;
    }

    public boolean insert(Object... values) {

        if (values.length != columns.length) {
            throw new IllegalArgumentException(
                "Expected " + columns.length + " values, but got " + values.length
            );
        }

        String cols = String.join(",", columns);
        String params = Arrays.stream(columns)
                              .map(c -> ":" + c)
                              .collect(Collectors.joining(","));

        JdbcClient.StatementSpec query = jdbc.sql(
            "INSERT INTO " + tableName + " (" + cols + ") VALUES (" + params + ")"
        );

        for (int i = 0; i < columns.length; i++) {
            query = query.param(columns[i], values[i]);
        }

        return query.update() > 0;
    }

    public List<T> findByColumn(String column, Object value) {
        return jdbc.sql("SELECT * FROM " + tableName +
                        " WHERE " + column + " = :val")
                .param("val", value)
                .query(rowMapper())
                .list();
    }

    /**
     * Convenience: find by first anchor id (columns[0]).
     */
    public List<T> findByAnchor1Id(Long id) {
        return findByColumn(anchor1Column, id);
    }

    /**
     * Convenience: find by second anchor id (columns[1]).
     */
    public List<T> findByAnchor2Id(Long id) {
        return findByColumn(anchor2Column, id);
    }

    public boolean insertSimple(Long anchor1, Long anchor2) {
        Objects.requireNonNull(anchor1);
        Objects.requireNonNull(anchor2);
        if (metadataColumn == null && changedAtColumn == null) {
            return insert(anchor1, anchor2);
        }
        // If metadata column exists but caller wants simple insert, pass null for metadata
        if (metadataColumn != null && changedAtColumn == null) {
            return insert(anchor1, anchor2, null);
        }
        // If historized, cannot use simple insert
        if (changedAtColumn != null) {
            throw new IllegalStateException("This tie is historized; use insertHistorized()");
        }
        return false;
    }

    public boolean insertWithMetadata(Long anchor1, Long anchor2, Long metadataId) {
        Objects.requireNonNull(anchor1);
        Objects.requireNonNull(anchor2);
        if (metadataColumn == null) {
            throw new IllegalStateException("This tie has no metadata column");
        }
        if (changedAtColumn == null) {
            return insert(anchor1, anchor2, metadataId);
        }
        // historized but caller didn't provide timestamp
        return insert(anchor1, anchor2, metadataId, null);
    }

    public boolean insertHistorized(Long anchor1, Long anchor2, Long metadataId, Instant changedAt) {
        Objects.requireNonNull(anchor1);
        Objects.requireNonNull(anchor2);
        if (changedAtColumn == null) {
            throw new IllegalStateException("This tie is not historized");
        }
        Timestamp ts = changedAt != null ? Timestamp.from(changedAt) : null;
        if (metadataColumn == null) {
            return insert(anchor1, anchor2, ts);
        }
        return insert(anchor1, anchor2, metadataId, ts);
    }

    protected abstract RowMapper<T> rowMapper();
}
