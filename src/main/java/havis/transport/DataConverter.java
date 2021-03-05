package havis.transport;

import java.util.Map;

/**
 * Interface to convert messages to flat data
 */
public interface DataConverter {

	/**
	 * Initialize the data converter
	 * 
	 * @param properties
	 *            the properties containing settings for conversion
	 * @throws ValidationException
	 *             if arguments are not valid
	 */
	void init(Map<String, String> properties) throws ValidationException;

	/**
	 * Convert a message and write it to the specified writer
	 * 
	 * @param message
	 *            the message to convert
	 * @param writer
	 *            the writer to write to
	 * @throws TransportException
	 *             if conversion fails
	 */
	void convert(Object message, DataWriter writer) throws TransportException;

}
