package persistence.items;

public class NotEnoughElementsException extends Exception{

    public NotEnoughElementsException(String msg){
        super(msg);
    }
}
