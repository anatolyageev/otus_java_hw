package otus.java.ageeev.exeption;

public class OutOfMaxCapacityInCassette extends RuntimeException{
    public OutOfMaxCapacityInCassette(String message) {
        super(message);
    }
}
