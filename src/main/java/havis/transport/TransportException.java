package havis.transport;

import java.util.concurrent.ExecutionException;

public class TransportException extends ExecutionException {

	private static final long serialVersionUID = 1L;

	public TransportException() {
		super();
	}

	public TransportException(String message) {
		super(message);
	}

	public TransportException(String message, Throwable cause) {
		super(message, cause);
	}
}