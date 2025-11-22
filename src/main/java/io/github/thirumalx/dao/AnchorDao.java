/**
 * 
 */
package io.github.thirumalx.dao;

import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import io.github.thirumalx.model.Anchor;

/**
 * @author Thirumal M
 * Anchor tables usually contain only the anchor ID (auto-generated PK) and optionally other metadata.
 */
public abstract class AnchorDao<T extends Anchor> {

	private final JdbcClient jdbc;
	private final String tableName;
	private final String idColumn;
	private final String metadataColumn;

	protected AnchorDao(JdbcClient jdbc, String tableName, String idColumn, String metadataColumn) {
		this.jdbc = jdbc;
		this.tableName = tableName;
		this.idColumn = idColumn;
		this.metadataColumn = metadataColumn;
	}

	public Long insert(Long metadata) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.sql("INSERT INTO " + tableName + " (" + metadataColumn + ") VALUES (:metadata) RETURNING " + idColumn)
			.param("metadata", metadata)
			.update(keyHolder);

		Number key = keyHolder.getKey();
		return key != null ? key.longValue() : null;
	}

	public Optional<T> findById(Long id) {
		return jdbc.sql("SELECT * FROM " + tableName + " WHERE " + idColumn + " = :id").param("id", id).query(rowMapper())
				.optional();
	}

	protected abstract RowMapper<T> rowMapper();

}
