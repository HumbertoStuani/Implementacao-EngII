package sapc.sapcbackend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaAlreadyExistsException;
import sapc.sapcbackend.db.entities.empresa.exceptions.EmpresaNotFoundException;
import sapc.sapcbackend.db.entities.empresa.exceptions.InvalidCnpjException;
import sapc.sapcbackend.db.entities.empresa.exceptions.ParametrizationAlreadyExistsException;
import sapc.sapcbackend.db.entities.evento.exceptions.EventoNotFoundException;
import sapc.sapcbackend.db.entities.evento.exceptions.EventoSaidaJaRealizadaException;
import sapc.sapcbackend.db.entities.produto.exceptions.ProdutoNotFoundException;
import sapc.sapcbackend.db.entities.produto.exceptions.ProdutoQtdeInsuficienteException;
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

    @ExceptionHandler(ParametrizationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handlerParametrizationAlreadyExists(ParametrizationAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(EventoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerEventoNotFound(EventoNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoSaidaJaRealizadaException.class)
    public ResponseEntity<ErrorResponseDTO> handlerEventoSaidaJaRealizada(EventoSaidaJaRealizadaException e) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerProdutoNotFound(ProdutoNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProdutoQtdeInsuficienteException.class)
    public ResponseEntity<ErrorResponseDTO> handlerProdutoQtdeInsuficiente(ProdutoQtdeInsuficienteException e) {
        return new ResponseEntity<>(new ErrorResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
