/**
 * 
 */
package io.github.thirumalx.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * @author ThirumalM
 */
public record PageRequest(@Min(0) int page, @Min(0) @Max(100)int size) implements Paging {
	
	public PageRequest {
		if (size == 0) {
			size = 10;
		}
	}

}
