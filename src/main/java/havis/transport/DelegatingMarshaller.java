package havis.transport;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.validation.Schema;

import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

/**
 * Marshaller delegating all calls to the marshaller passed in the constructor
 */
public class DelegatingMarshaller implements Marshaller {

	protected final Marshaller delegate;

	/**
	 * @param delegate
	 *            the marshaller to delegate all calls to
	 */
	public DelegatingMarshaller(Marshaller delegate) {
		this.delegate = delegate;
	}

	@Override
	public void marshal(Object jaxbElement, Result result) throws JAXBException {
		this.delegate.marshal(jaxbElement, result);
	}

	@Override
	public void marshal(Object jaxbElement, OutputStream os) throws JAXBException {
		this.delegate.marshal(jaxbElement, os);
	}

	@Override
	public void marshal(Object jaxbElement, File output) throws JAXBException {
		this.delegate.marshal(jaxbElement, output);
	}

	@Override
	public void marshal(Object jaxbElement, Writer writer) throws JAXBException {
		this.delegate.marshal(jaxbElement, writer);
	}

	@Override
	public void marshal(Object jaxbElement, ContentHandler handler) throws JAXBException {
		this.delegate.marshal(jaxbElement, handler);
	}

	@Override
	public void marshal(Object jaxbElement, Node node) throws JAXBException {
		this.delegate.marshal(jaxbElement, node);
	}

	@Override
	public void marshal(Object jaxbElement, XMLStreamWriter writer) throws JAXBException {
		this.delegate.marshal(jaxbElement, writer);
	}

	@Override
	public void marshal(Object jaxbElement, XMLEventWriter writer) throws JAXBException {
		this.delegate.marshal(jaxbElement, writer);
	}

	@Override
	public Node getNode(Object contentTree) throws JAXBException {
		return this.delegate.getNode(contentTree);
	}

	@Override
	public void setProperty(String name, Object value) throws PropertyException {
		this.delegate.setProperty(name, value);
	}

	@Override
	public Object getProperty(String name) throws PropertyException {
		return this.delegate.getProperty(name);
	}

	@Override
	public void setEventHandler(ValidationEventHandler handler) throws JAXBException {
		this.delegate.setEventHandler(handler);
	}

	@Override
	public ValidationEventHandler getEventHandler() throws JAXBException {
		return this.delegate.getEventHandler();
	}

	@Override
	public void setAdapter(@SuppressWarnings("rawtypes") XmlAdapter adapter) {
		this.delegate.setAdapter(adapter);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <A extends XmlAdapter> void setAdapter(Class<A> type, A adapter) {
		this.delegate.setAdapter(type, adapter);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <A extends XmlAdapter> A getAdapter(Class<A> type) {
		return this.delegate.getAdapter(type);
	}

	@Override
	public void setAttachmentMarshaller(AttachmentMarshaller am) {
		this.delegate.setAttachmentMarshaller(am);
	}

	@Override
	public AttachmentMarshaller getAttachmentMarshaller() {
		return this.delegate.getAttachmentMarshaller();
	}

	@Override
	public void setSchema(Schema schema) {
		this.delegate.setSchema(schema);
	}

	@Override
	public Schema getSchema() {
		return this.delegate.getSchema();
	}

	@Override
	public void setListener(Listener listener) {
		this.delegate.setListener(listener);
	}

	@Override
	public Listener getListener() {
		return this.delegate.getListener();
	}

}
