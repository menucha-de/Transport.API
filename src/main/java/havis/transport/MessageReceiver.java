package havis.transport;

/**
 * Callback interface for subscriber listeners
 */
public interface MessageReceiver {
	/**
	 * Receives the message
	 * 
	 * @param message
	 *            the message
	 */
	void receive(Object message);

	/**
	 * Cancel receiving a message, will be called if no message will be received
	 * because sending has been canceled.
	 */
	void cancel();
}
