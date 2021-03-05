package havis.transport;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Subscriber class for management
 */
public class Subscriber implements Subscription {

	private String id;
	private boolean enable;
	private String uri;
	private Map<String, String> properties;

	public Subscriber() {
	}

	public Subscriber(String id) {
		this.id = id;
	}

	/**
	 * Creates a new subscriber without properties
	 * 
	 * @param enable
	 *            whether the subscriber is enabled
	 * @param uri
	 *            the URI of the subscriber
	 */
	public Subscriber(boolean enable, String uri) {
		this.enable = enable;
		this.uri = uri;
		this.properties = new LinkedHashMap<String, String>();
	}

	/**
	 * Creates a new subscriber
	 * 
	 * @param enable
	 *            whether the subscriber is enabled
	 * @param uri
	 *            the URI of the subscriber
	 * @param properties
	 *            the properties of the subscriber
	 */
	public Subscriber(boolean enable, String uri, Map<String, String> properties) {
		this.enable = enable;
		this.uri = uri;
		this.properties = properties;
	}

	/**
	 * Creates a new subscriber from the specified subscriber (deep copy)
	 * 
	 * @param subscriber
	 *            the subscriber to copy
	 */
	public Subscriber(Subscriber subscriber) {
		set(subscriber);
	}

	/**
	 * Sets all properties to the values of the specified subscriber
	 * 
	 * @param subscriber
	 *            the subscriber to copy
	 */
	public void set(Subscriber subscriber) {
		this.id = subscriber.id;
		this.enable = subscriber.enable;
		this.uri = subscriber.uri;
		if (subscriber.properties != null)
			this.properties = new LinkedHashMap<>(subscriber.properties);
	}

	/**
	 * @return the subscriber ID
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the subscriber ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return whether the subscriber is enabled
	 */
	public boolean isEnable() {
		return this.enable;
	}

	/**
	 * @param enable
	 *            whether the subscriber is enabled
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the subscriber URI
	 */
	public String getUri() {
		return this.uri;
	}

	/**
	 * @param uri
	 *            the subscriber URI
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the subscriber properties
	 */
	@Override
	public Map<String, String> getProperties() {
		return this.properties;
	}

	/**
	 * @param properties
	 *            the subscriber properties
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
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
		if (!(obj instanceof Subscriber))
			return false;
		Subscriber other = (Subscriber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", enable=" + enable + ", uri=" + uri + ", properties=" + properties + "]";
	}
}
