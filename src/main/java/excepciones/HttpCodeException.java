package excepciones;

public class HttpCodeException extends RuntimeException{
	private static final long serialVersionUID = 9L;
	private int codigoError;
	


	public HttpCodeException(int cod) {
		codigoError = cod;
	}



	@Override
	public String toString() {
		return "Error: HTTP error code : " + codigoError;
	}

}
