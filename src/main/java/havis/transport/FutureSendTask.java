package havis.transport;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A <tt>Future</tt> that represents the result of an asynchronous message
 * sending attempt. Methods are provided to check if sending is complete and to
 * wait for its completion. To wait for completion, the
 * {@link FutureSendTask#get()} method can be used, which blocks until sending
 * is complete. Cancellation is performed by the
 * {@link FutureSendTask#cancel(boolean)} method. Additional methods are
 * provided to determine if the task completed normally or was cancelled. Once
 * sending has completed, the computation cannot be cancelled.
 * 
 * The first exception will always be logged by the messenger, even if
 * {@link FutureSendTask#get()} is never called.
 */
public class FutureSendTask extends FutureTask<Void> {

	private CompletionHandler completionHandler;

	public FutureSendTask(Callable<Void> callable, CompletionHandler completionHandler) {
		super(callable);
		this.completionHandler = completionHandler;
	}

	public FutureSendTask(Runnable runnable, Void result, CompletionHandler completionHandler) {
		super(runnable, result);
		this.completionHandler = completionHandler;
	}

	/**
	 * Waits if necessary for sending to complete.
	 * 
	 * @return null
	 * @throws CancellationException
	 *             if sending was cancelled
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @throws TransportException
	 *             if transport failed
	 */
	@Override
	public Void get() throws InterruptedException, TransportException {
		try {
			return super.get();
		} catch (ExecutionException e) {
			if (e.getCause() instanceof TransportException)
				throw (TransportException) e.getCause();
			else
				throw new TransportException("Unhandled error", e.getCause());
		}
	}

	/**
	 * Waits if necessary for at most the given time for sending to complete.
	 * 
	 * @param timeout
	 *            the maximum time to wait
	 * @param unit
	 *            the time unit of the timeout argument
	 * @return null
	 * @throws CancellationException
	 *             if sending was cancelled
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @throws TimeoutException
	 *             if the wait timed out
	 * @throws TransportException
	 *             if transport failed
	 */
	@Override
	public Void get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException, TransportException {
		try {
			return super.get(timeout, unit);
		} catch (ExecutionException e) {
			if (e.getCause() instanceof TransportException)
				throw (TransportException) e.getCause();
			else
				throw new TransportException("Unhandled error", e.getCause());
		}
	}

	@Override
	protected void set(Void v) {
		super.set(v);
		if (this.completionHandler != null)
			this.completionHandler.onSuccess();
	}

	@Override
	protected void setException(Throwable t) {
		super.setException(t);
		if (this.completionHandler != null)
			this.completionHandler.onError(t);
	}
}
