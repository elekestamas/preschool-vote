package hu.elekest.preschoolvote.exception;

public class VoteServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9105461180414056245L;

	private final ErrorCode errorCode;
	private Throwable source;
	
	public VoteServiceException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public VoteServiceException(ErrorCode errorCode, Throwable source) {
		this.errorCode = errorCode;
		this.setSource(source);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Throwable getSource() {
		return source;
	}

	public void setSource(Throwable source) {
		this.source = source;
	}
}
