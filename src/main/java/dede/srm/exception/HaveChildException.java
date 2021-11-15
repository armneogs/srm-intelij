package dede.srm.exception;

public class HaveChildException extends Exception {

	private static final Long serialVersionUID = -8661624956774363024L;
	public HaveChildException() {
        super();
    }
	
	public HaveChildException(String errorMessage) {
        super(errorMessage);
    }
}
