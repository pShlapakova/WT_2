package exception;

public class InvalidException
        extends Exception {

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(){
        super();
    }

    public InvalidException(Exception e){
        super(e);
    }

    public InvalidException(String message, Exception e){
        super(message, e);
    }
}
