package io.github.thirumalx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateKeyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3492131502519370028L;

	public DuplicateKeyException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DuplicateKeyException(final String message) {
		super(message);
	}

	public DuplicateKeyException(final Throwable cause) {
		super(cause);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
