/**
 * 
 */
package com.aggregate;

/**
 * @author vipul
 * This class is used for customized exceptions 
 */
public class AggregationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AggregationException() {
	}

	/**
	 * @param message
	 */
	public AggregationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AggregationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AggregationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AggregationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
