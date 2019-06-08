package exception;

public class ServiceException extends Exception{
    public ServiceException(String mensagem){
        super( mensagem );
    }
}
