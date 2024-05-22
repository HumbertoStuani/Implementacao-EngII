package sapc.sapcbackend.db.entities.empresa.exceptions;

public class InvalidCnpjException extends RuntimeException{
    public InvalidCnpjException(String message) {
        super(message);
    }
}
