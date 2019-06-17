package cpabe.utils;

@SuppressWarnings("serial")
public class CpabeException extends Exception {

	public CpabeException() {

	}

	public CpabeException(String errorMessage) {
		super(errorMessage);
	}
}
