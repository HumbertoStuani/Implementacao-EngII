package sapc.sapcbackend.db.entities.empresa.exceptions;

public class EmpresaAlreadyExistsException extends RuntimeException{
    public EmpresaAlreadyExistsException(String message) {
        super(message);
    }
}
