package sapc.sapcbackend.db.entities.empresa.exceptions;

public class EmpresaNotFoundException extends RuntimeException{

    public EmpresaNotFoundException(String message) {
        super(message);
    }
}
