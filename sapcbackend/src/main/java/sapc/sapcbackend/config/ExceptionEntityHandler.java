package sapc.sapcbackend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaNotFoundException;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<Void> handlerEmpresaNotFound(EmpresaNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
