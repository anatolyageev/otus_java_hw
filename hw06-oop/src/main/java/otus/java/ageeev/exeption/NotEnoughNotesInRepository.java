package otus.java.ageeev.exeption;

public class NotEnoughNotesInRepository extends RuntimeException{
    public NotEnoughNotesInRepository(String message) {
        super(message);
    }
}
