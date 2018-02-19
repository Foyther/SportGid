package android.kfu.service.api.exception.NotFound;

/**
 * Created by Nurislam on 19.12.2017.
 */
public class PlaceNotFoundException extends Exception{
    public PlaceNotFoundException() {
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

    public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceNotFoundException(Throwable cause) {
        super(cause);
    }

}
