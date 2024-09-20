package co.com.devco.talentpool.videogamestore.domain.exceptions;

public class NoCopiesAvailableException extends RuntimeException{

    public NoCopiesAvailableException(String message){
        super(message);
    }
}
