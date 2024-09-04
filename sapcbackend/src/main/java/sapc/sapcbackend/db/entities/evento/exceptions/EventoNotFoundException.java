package sapc.sapcbackend.db.entities.evento.exceptions;

public class EventoNotFoundException extends RuntimeException{
    public EventoNotFoundException(String message) {
        super(message);
    }
}
