package havis.transport;

public class TransportConnectionException extends TransportException {

	private static final long serialVersionUID = 1L;

	public TransportConnectionException() {
		super();
	}

	public TransportConnectionException(String message) {
		super(message);
	}

	public TransportConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}