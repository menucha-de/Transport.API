package havis.transport;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Subscriptor class to extend and reuse an existing subscriber
 */
public class Subscriptor implements Subscription {

	private String id;
	private boolean enable;
	private String name;
	private String path;
	private String subscriberId;
	private Map<String, String> properties;

	public Subscriptor() {
	}

	/**
	 * Creates a new subscriptor without properties
	 * 
	 * @param enable
	 *            whether the subscriptor is enabled
	 * @param name
	 *            the name of the subscriptor
	 * @param path
	 *            the path of the subscriptor
	 * @param subscriberId
	 *            the ID of the subscriber to reuse
	 */
	public Subscriptor(boolean enable, String name, String path, String subscriberId) {
		this.enable = enable;
		this.name = name;
		this.path = path;
		this.subscriberId = subscriberId;
		this.properties = new LinkedHashMap<String, String>();
	}

	/**
	 * Creates a new subscriptor
	 * 
	 * @param enable
	 *            whether the subscriptor is enabled
	 * @param name
	 *            the name of the subscriptor
	 * @param path
	 *            the path of the subscriptor
	 * @param subscriberId
	 *            the ID of the subscriber to reuse
	 * @param properties
	 *            the properties of the subscriptor
	 */
	public Subscriptor(boolean enable, String name, String path, String subscriberId, Map<String, String> properties) {
		this.enable = enable;
		this.name = name;
		this.path = path;
		this.subscriberId = subscriberId;
		this.properties = properties;
	}

	/**
	 * Creates a new subscriptor from the specified subscriptor (deep copy)
	 * 
	 * @param subscriptor
	 *            the subscriptor to copy
	 */
	public Subscriptor(Subscriptor subscriptor) {
		set(subscriptor);
	}

	/**
	 * Sets all properties to the values of the specified subscriptor
	 * 
	 * @param subscriptor
	 *            the subscriptor to copy
	 */
	public void set(Subscriptor subscriptor) {
		this.id = subscriptor.id;
		this.enable = subscriptor.enable;
		this.name = subscriptor.name;
		this.path = subscriptor.path;
		this.subscriberId = subscriptor.subscriberId;
		if (subscriptor.properties != null)
			this.properties = new LinkedHashMap<>(subscriptor.properties);
	}

	/**
	 * @return the subscriptor ID
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the subscriptor ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return whether the subscriptor is enabled
	 */
	@Override
	public boolean isEnable() {
		return this.enable;
	}

	/**
	 * @param enable
	 *            whether the subscriptor is enabled
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the name of the subscriptor
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name of the subscriptor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the subscriptor path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @param path
	 *            the subscriptor path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the ID of the subscriber to extend and reuse
	 */
	public String getSubscriberId() {
		return subscriberId;
	}

	/**
	 * @param subscriberId
	 *            the ID of the subscriber to extend and reuse
	 */
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	/**
	 * @return the subscriptor properties
	 */
	@Override
	public Map<String, String> getProperties() {
		return this.properties;
	}

	/**
	 * @param properties
	 *            the subscriptor properties
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
		if (!(obj instanceof Subscriptor))
			return false;
		Subscriptor other = (Subscriptor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscriptor [id=" + id + ", enable=" + enable + ", name=" + name + ", path=" + path + ", subscriberId=" + subscriberId + ", properties="
				+ properties + "]";
	}
}
