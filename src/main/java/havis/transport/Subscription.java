package havis.transport;

import java.util.Map;

/**
 * Subscription interface
 */
public interface Subscription {

	/**
	 * @return the ID of the subscription
	 */
	public String getId();

	/**
	 * @return whether the subscriber is enabled
	 */
	public boolean isEnable();

	/**
	 * @return the properties of the subscription
	 */
	public Map<String, String> getProperties();

}
