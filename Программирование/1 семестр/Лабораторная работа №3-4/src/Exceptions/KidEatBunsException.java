package Exceptions;

public class KidEatBunsException extends Exception {
    public KidEatBunsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Есть мучное вредно только детям!";
    }
}
