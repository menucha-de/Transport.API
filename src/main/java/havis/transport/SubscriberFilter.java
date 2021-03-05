package havis.transport;

/**
 * Filter for subscriptions
 */
public interface SubscriberFilter {

	boolean accept(Subscription subscription);

}
