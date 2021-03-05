package havis.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface to marshal into a target
 */
public interface Marshaller<T> {

	/**
	 * unmarshal the from the specified {@link OutputStream}
	 * 
	 * @param source
	 *            the {@link InputStream} to unmarshal from
	 * @return The message
	 * @throws TransportException
	 *             if unmarshalling fails
	 */
	T unmarshal(InputStream source) throws TransportException;

	/**
	 * marshal the to the specified {@link OutputStream}
	 * 
	 * @param message
	 *            The message
	 * @param target
	 *            the {@link OutputStream} to marshal to
	 * @throws TransportException
	 *             if marshalling fails
	 */
	void marshal(T message, OutputStream target) throws TransportException;
}