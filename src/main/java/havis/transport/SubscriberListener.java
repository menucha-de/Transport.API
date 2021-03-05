package havis.transport;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Subscriber listener which can be added to a {@link SubscriberManager} to
 * receive a single message. After the message has been received, the listener
 * will be removed automatically.
 */
public class SubscriberListener implements Subscription, MessageReceiver {

	private String id;

	private Map<String, String> properties;

	private MessageReceiver callback;

	/**
	 * Creates a subscriber listener
	 * 
	 * @param callback
	 *            the callback to receive the message
	 */
	public SubscriberListener(MessageReceiver callback) {
		this.id = UUID.randomUUID().toString();
		this.callback = Objects.requireNonNull(callback, "callback must not be null");
		this.properties = new LinkedHashMap<String, String>();
	}

	/**
	 * Creates a subscriber listener
	 * 
	 * @param callback
	 *            the callback to receive the message
	 * 
	 * @param properties
	 *            the properties of the subscriber
	 */
	public SubscriberListener(MessageReceiver callback, Map<String, String> properties) {
		this.id = UUID.randomUUID().toString();
		this.callback = Objects.requireNonNull(callback, "callback must not be null");
		this.properties = properties;
	}

	/**
	 * @return the subscriber listener ID
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @return whether the subscriber is enabled
	 */
	@Override
	public boolean isEnable() {
		return true;
	}

	/**
	 * @return the subscriber listener properties
	 */
	@Override
	public Map<String, String> getProperties() {
		return this.properties;
	}

	/**
	 * @param properties
	 *            the subscriber listener properties
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * Receives a message and sends it to the callback
	 * 
	 * @param message
	 *            the message
	 */
	@Override
	public void receive(Object message) {
		this.callback.receive(message);
	}

	/**
	 * Sends the cancel signal to the callback
	 */
	@Override
	public void cancel() {
		this.callback.cancel();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SubscriberListener))
			return false;
		SubscriberListener other = (SubscriberListener) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriberListener [id=" + id + "]";
	}
}
