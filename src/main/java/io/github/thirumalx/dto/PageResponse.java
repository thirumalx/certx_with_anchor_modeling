/**
 * 
 */
package io.github.thirumalx.dto;

import java.util.List;

/**
 * @author ThirumalM
 */
public record PageResponse<T>(int page, int size, List<T> content, long totalElements, int totalPages) implements Paging {

}
