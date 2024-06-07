package sapc.sapcbackend.db.entities.produto.exceptions;

public class ProdutoQtdeInsuficienteException extends RuntimeException{
    public ProdutoQtdeInsuficienteException(String message) {
        super(message);
    }
}
