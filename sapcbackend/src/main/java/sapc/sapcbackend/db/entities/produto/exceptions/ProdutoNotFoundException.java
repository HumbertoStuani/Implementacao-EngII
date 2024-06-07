package sapc.sapcbackend.db.entities.produto.exceptions;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
