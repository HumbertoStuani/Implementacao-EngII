package sapc.sapcbackend.db.entities.empresa.exceptions;

public class ParametrizationAlreadyExistsException extends RuntimeException{
    public ParametrizationAlreadyExistsException(String message) {
        super(message);
    }
}
