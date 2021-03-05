package havis.transport;

import java.util.List;

/**
 * Interface to write data
 */
public interface DataWriter {

	/**
	 * Prepare the data writer
	 * 
	 * @param fields
	 *            the field names
	 * @throws TransportException
	 *             if fields are invalid
	 */
	void prepare(List<String> fields) throws TransportException;

	/**
	 * Write the specified values
	 * 
	 * @param values
	 *            the values to write
	 * @throws TransportException
	 *             if writing fails
	 */
	void write(List<Object> values) throws TransportException;

	/**
	 * Commit the data
	 * 
	 * @throws TransportException
	 *             if commit fails
	 */
	void commit() throws TransportException;

}
