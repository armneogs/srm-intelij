package dede.srm.exception;

public class PkIsEmptyException extends Exception {
	
	private static final Long serialVersionUID = 1160352348116199803L;

	public PkIsEmptyException() {
        super();
    }
	
	public PkIsEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
