package havis.transport;

import havis.transform.Transformer;

import java.net.URI;
import java.util.Map;

import javax.net.SocketFactory;

/**
 * Messenger handling the processing of messages
 * 
 * @param <T>
 *            type of message to transport
 */
public interface Messenger<T> {

	/**
	 * Transformer property, set to choose a transformer, e.g.
	 * {@link Messenger#JS_TRANSFORMER}
	 */
	final static String TRANSFORMER_PROPERTY = "Transformer";

	/**
	 * JavaScript transformer, set with {@link Messenger#TRANSFORMER_PROPERTY}
	 * to transform using a script
	 */
	final static String JS_TRANSFORMER = "javascript";

	/**
	 * MIME type property, set to choose the MIME type to use to transport data,
	 * e.g. 'application/json' or 'text/plain'
	 */
	final static String MIMETYPE_PROPERTY = "MimeType";

	/**
	 * Default MIME type, used when no MIME type was set with
	 * {@link Messenger#MIMETYPE_PROPERTY}
	 */
	final static String DEFAULT_MIMETYPE = "text/xml";

	/**
	 * MIME type for binary data
	 */
	final static String BINARY_MIMETYPE = "application/octet-stream";

	/**
	 * Sets the message delivering error resend repeat period
	 */
	final static String RESEND_REPEAT_PERIOD_PROPERTY = Transporter.PREFIX + "ResendRepeatPeriod";

	/**
	 * Sets the message delivering error resend queue size
	 */
	final static String RESEND_QUEUE_SIZE_PROPERTY = Transporter.PREFIX + "ResendQueueSize";

	/**
	 * TCP timeout property, set to choose the timeout in milliseconds to
	 * establish a TCP connection
	 */
	final static String TCP_TIMEOUT_PROPERTY = Transporter.PREFIX + "TCP.Timeout";

	/**
	 * MQTT timeout property, set to choose the timeout in milliseconds to
	 * establish a MQTT connection
	 */
	final static String MQTT_TIMEOUT_PROPERTY = Transporter.PREFIX + "MQTT.Timeout";

	/**
	 * Microsoft Azure timeout property, set to choose the timeout in
	 * milliseconds to establish a connection to the cloud
	 */
	final static String AZURE_TIMEOUT_PROPERTY = Transporter.PREFIX + "Azure.Timeout";
	
	/**
	 * Microsoft Azure onDemand property, set to choose, if connection should be established on send
	 */
	final static String AZURE_ONDEMAND_PROPERTY = Transporter.PREFIX + "Azure.OnDemand";

	/**
	 * HTTP timeout property, set to choose the timeout in milliseconds to
	 * establish a HTTP connection
	 */
	final static String HTTP_TIMEOUT_PROPERTY = Transporter.PREFIX + "HTTP.Timeout";

	/**
	 * HTTPS verification bypass property, set to bypass SSL verification when
	 * transporting data over HTTPS (true/false)
	 */
	final static String HTTPS_BYPASS_SSL_VERIFICATION_PROPERTY = Transporter.PREFIX + "HTTPS.BypassSSLVerification";

	/**
	 * HTTP method property, set to define which HTTP method to use, e.g. GET or
	 * POST
	 */
	final static String HTTP_METHOD_PROPERTY = Transporter.PREFIX + "HTTP.Method";

	/**
	 * JDBC table name property, set to define which database table to insert
	 * into.
	 */
	final static String JDBC_TABLE_NAME_PROPERTY = Transporter.PREFIX + "JDBC.Table";

	/**
	 * JDBC keep connection property, set to keep the database connection open
	 * between inserts (true/false)
	 */
	final static String JDBC_KEEP_CONNECTION_PROPERTY = Transporter.PREFIX + "JDBC.KeepConnection";

	/**
	 * JDBC initial statement property, set to i.e. create the database table
	 */
	final static String JDBC_INIT_STATEMENT = Transporter.PREFIX + "JDBC.InitStatement";

	/**
	 * JDBC drop property, set to drop table on dispose
	 */
	final static String JDBC_DROP = Transporter.PREFIX + "JDBC.Drop";

	/**
	 * JDBC storage property, set to publish the database table as a REST
	 * resource with given name
	 */
	final static String JDBC_STORAGE = Transporter.PREFIX + "JDBC.Storage";

	/**
	 * JDBC clear property, set to allow remote data clearing
	 */
	final static String JDBC_CLEAR = Transporter.PREFIX + "JDBC.Clear";

	/**
	 * JavaScript property, set to define the script to run in a JavaScript
	 * transformation
	 */
	final static String JS_SCRIPT_PROPERTY = Transformer.PREFIX + JS_TRANSFORMER + ".Script";

	/**
	 * Data converter property, set to define the expression to use when
	 * converting complex objects to flat data
	 */
	final static String DATA_CONVERTER_EXPRESSION_PROPERTY = Transporter.PREFIX + "DataConverter.Expression";

	/**
	 * Data converter property, set to avoid duplicate entries in a single
	 * conversion pass (true/false)
	 */
	final static String DATA_CONVERTER_AVOID_DUPLICATES_PROPERTY = Transporter.PREFIX + "DataConverter.AvoidDuplicates";

	/**
	 * Initialize the messenger to send messages over the wire
	 * 
	 * @param clazz
	 *            the {@link Class} of the messages to send
	 * @param uri
	 *            the URI to send to
	 * @param properties
	 *            The properties containing settings for transport and
	 *            transformation. All properties of default transporters and
	 *            transformers are available as constants in the
	 *            {@link Messenger} interface. To use a transformation, set the
	 *            {@link Messenger#TRANSFORMER_PROPERTY} accordingly. When using
	 *            script based transformations (e.g. JavaScript), the message
	 *            will be available as a variable with the name 'object', when
	 *            the script is run (see {@link Transformer#VARIABLE}).
	 * @throws ValidationException
	 *             if arguments are not valid
	 */
	void init(Class<T> clazz, URI uri, Map<String, String> properties) throws ValidationException;

	/**
	 * Initialize the messenger as a listener which sends a single message to a
	 * callback and not over the wire. The messenger will be disposed
	 * automatically after the message has been send.
	 * 
	 * @param clazz
	 *            the {@link Class} of the messages to send
	 * @param callback
	 *            the callback to send one message to
	 * @param properties
	 *            The properties containing settings for transport and
	 *            transformation. All properties of default transporters and
	 *            transformers are available as constants in the
	 *            {@link Messenger} interface. To use a transformation, set the
	 *            {@link Messenger#TRANSFORMER_PROPERTY} accordingly. When using
	 *            script based transformations (e.g. JavaScript), the message
	 *            will be available as a variable with the name 'object', when
	 *            the script is run (see {@link Transformer#VARIABLE}).
	 * @throws ValidationException
	 *             if arguments are not valid
	 */
	void init(Class<T> clazz, MessageReceiver callback, Map<String, String> properties) throws ValidationException;

	/**
	 * @return true if changing the {@link SocketFactory} is supported by this
	 *         {@link Messenger}, false otherwise
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
	 * Asynchronously send a message. If this is a listener messenger, the
	 * dispose method will be called automatically.
	 * 
	 * @param message
	 *            The message to send
	 * @return the {@link FutureSendTask} to wait for sending to finish and
	 *         handle exceptions.
	 */
	FutureSendTask send(T message);

	/**
	 * Retrieves the URI
	 * 
	 * @return The URI
	 */
	URI getUri();

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
	 * @return true if the messenger is currently unable to transport messenger,
	 *         false otherwise
	 */
	boolean isErrorState();

	/**
	 * Disposes the messenger
	 */
	void dispose();
}
