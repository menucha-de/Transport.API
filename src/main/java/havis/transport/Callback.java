package havis.transport;

/**
 * This callback is used for message receiving. If the callback is set, a
 * subscription was made on the paths of each subscriptor
 */
public interface Callback {
	/**
	 * Receive message
	 * 
	 * @param path
	 *            The path where the message arrived
	 * @param message
	 *            The message
	 */
	void arrived(String path, Object message);
}