package havis.transport;

import java.util.Collection;
import java.util.Map;

public interface SubscriptorManager {

	String add(Subscriptor subscriptor) throws ValidationException, TransportException;

	void update(Subscriptor subscriptor) throws ValidationException, TransportException;

	void remove(String id) throws ValidationException, TransportException;

	void remove(Subscriptor subscriptor) throws ValidationException, TransportException;

	Map<Subscriptor, FutureSendTask> send(Object message);
	
	Collection<Subscriptor> getSubscriptors();
	
	void dispose();
	
	boolean hasEnabledSubscriptors();
}