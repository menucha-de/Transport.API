package havis.transport;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SubscriberManager {

	/**
	 * @return the available subscriber types or URI schemes
	 */
	public List<String> getSubscriberTypes();

	/**
	 * Add a subscriber, ID will be generated
	 * 
	 * @param subscriber
	 *            the subscriber without and ID (will be generated)
	 * @return the generated ID
	 * @throws ValidationException
	 *             if validation of subscriber failed
	 */
	public String add(Subscriber subscriber) throws ValidationException;

	/**
	 * Add a subscriber listener. The listener will be removed automatically
	 * after a single message was received.
	 * 
	 * @param listener
	 *            the subscriber listener to add
	 * @throws ValidationException
	 *             if validation of subscriber failed
	 */
	public void add(SubscriberListener listener) throws ValidationException;

	/**
	 * Update a subscriber, ID must be specified
	 * 
	 * @param subscriber
	 *            the subscriber to update
	 * @throws ValidationException
	 *             if validation of subscriber failed
	 */
	public void update(Subscriber subscriber) throws ValidationException;

	/**
	 * Remove a subscriber by ID
	 * 
	 * @param id
	 *            the ID of the subscriber to remove
	 * @throws ValidationException
	 *             if removing of subscriber failed
	 */
	public void remove(String id) throws ValidationException;

	/**
	 * Remove a subscriber, ID must be specified
	 * 
	 * @param subscriber
	 *            the subscriber to remove
	 * @throws ValidationException
	 *             if removing of subscriber failed
	 */
	public void remove(Subscriber subscriber) throws ValidationException;

	/**
	 * Get a subscriber by ID
	 * 
	 * @param id
	 *            the ID of the subscriber
	 * @return the subscriber or null if no subscriber was found by the
	 *         specified ID
	 */
	public Subscriber get(String id);

	/**
	 * @return the reference to the list of subscribers
	 */
	public Collection<Subscriber> getSubscribers();

	/**
	 * @return true if at least one subscriber is enabled, false otherwise.
	 */
	public boolean hasEnabledSubscribers();

	/**
	 * @return true if at least one listener is registered, false otherwise
	 */
	public boolean hasListeners();

	/**
	 * Asynchronously send a message to all enabled subscribers and listeners
	 * 
	 * @param message
	 *            the message to send
	 * @return map of {@link Subscription} and {@link FutureSendTask} to wait
	 *         for sending to finish and handle exceptions of each send request.
	 *         You can safely ignore the result, exceptions will be logged.
	 */
	public Map<Subscription, FutureSendTask> send(Object message);

	/**
	 * Asynchronously send a message to all enabled subscribers and listeners or
	 * only the listeners
	 * 
	 * @param message
	 *            the message to send
	 * @param listenersOnly
	 *            whether to send to listeners only
	 * @return map of {@link Subscription} and {@link FutureSendTask} to wait
	 *         for sending to finish and handle exceptions of each send request.
	 *         You can safely ignore the result, exceptions will be logged.
	 */
	public Map<Subscription, FutureSendTask> send(Object message, boolean listenersOnly);

	/**
	 * Asynchronously send a message to all enabled subscribers and listeners,
	 * which are accepted by the specified filter
	 * 
	 * @param message
	 *            the message to send
	 * @param filter
	 *            the filter to use to accept subscribers and listeners
	 * 
	 * @return map of {@link Subscription} and {@link FutureSendTask} to wait
	 *         for sending to finish and handle exceptions of each send request.
	 *         You can safely ignore the result, exceptions will be logged.
	 */
	public Map<Subscription, FutureSendTask> send(Object message, SubscriberFilter filter);

	/**
	 * Dispose all active messengers and cancel listeners which have not
	 * received a message yet
	 */
	public void dispose();

	/**
	 * Dispose all active messengers
	 * 
	 * @param whether
	 *            to also cancel listeners which have not received a message yet
	 */
	public void dispose(boolean cancelListeners);

}