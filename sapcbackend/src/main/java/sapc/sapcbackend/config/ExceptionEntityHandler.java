package sapc.sapcbackend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaAlreadyExistsException;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaNotFoundException;
import sapc.sapcbackend.db.entities.empresa.exceptions.InvalidCnpjException;
import sapc.sapcbackend.dto.general.ErrorResponseDTO;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<Void> handlerEmpresaNotFound(EmpresaNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmpresaAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handlerEmpresaAlreadyExists(EmpresaAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(InvalidCnpjException.class)
    public ResponseEntity<ErrorResponseDTO> handlerInvalidCnpj(InvalidCnpjException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }
}
