package havis.transport;

/**
 * Completion handler
 */
public interface CompletionHandler {

	/**
	 * Called on normal completion
	 */
	void onSuccess();

	/**
	 * Called on erroneous completion
	 * 
	 * @param errorMessage
	 *            the error message
	 */
	void onError(String errorMessage);

	/**
	 * Called on erroneous completion
	 * 
	 * @param error
	 *            the error
	 */
	void onError(Throwable error);
}
