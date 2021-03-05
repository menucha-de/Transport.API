package havis.transport;

import java.net.URI;
import java.util.Map;

import javax.net.SocketFactory;

/**
 * Transporter for messages
 * 
 * @param <T>
 *            type of message
 */
public interface Transporter<T> {

	/**
	 * Prefix used in properties
	 */
	final static String PREFIX = "Transporter.";

	/**
	 * Initialize the transporter
	 * 
	 * @param clazz
	 *            the {@link Class} of the messages to transform
	 * @param uri
	 *            the URI to send to
	 * @param properties
	 *            the properties containing settings for transport
	 * @throws ValidationException
	 *             if arguments are not valid
	 */
	void init(Class<T> clazz, URI uri, Map<String, String> properties) throws ValidationException;

	/**
	 * @return true if changing the {@link SocketFactory} is supported by this
	 *         {@link Transporter}, false otherwise
	 */
	boolean supportsSocketFactory();

	/**
	 * Sets the factory for socket creation
	 * 
	 * @param socketFactory
	 *            The socket factory
	 * @throws TransportException
	 *             If socket factory is not supported
	 */
	void setSocketFactory(SocketFactory socketFactory) throws TransportException;

	/**
	 * Send a message
	 * 
	 * @param message
	 *            the message to send
	 * @throws TransportException
	 *             if transport fails
	 */
	void send(Object message) throws TransportException;

	/**
	 * Send a message with additional information
	 * 
	 * @param message
	 *            the message to send
	 * @param name
	 *            the name
	 * @param path
	 *            the path which extends the URI
	 * @param properties
	 *            the properties containing additional settings for transport
	 * @throws TransportException
	 *             if transport fails
	 */
	void send(Object message, String name, String path, Map<String, String> properties) throws TransportException;

	/**
	 * Adds a callback for a specific path
	 * 
	 * @param path
	 *            The path
	 * @param callback
	 *            the callback
	 */
	void addPath(String path, Callback callback) throws TransportException;

	/**
	 * Removes a callback for a specific path
	 * 
	 * @param path
	 *            The path
	 * @param callback
	 *            the callback
	 */
	void removePath(String path, Callback callback) throws TransportException;

	/**
	 * Dispose the transporter
	 */
	void dispose();

}