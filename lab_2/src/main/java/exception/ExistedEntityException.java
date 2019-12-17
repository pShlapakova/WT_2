package exception;

public class ExistedEntityException
        extends Exception {

    public ExistedEntityException(String message) {
        super(message);
    }

    public ExistedEntityException(){
        super();
    }

    public ExistedEntityException(Exception e){
        super(e);
    }

    public ExistedEntityException(String message, Exception e){
        super(message, e);
    }
}
