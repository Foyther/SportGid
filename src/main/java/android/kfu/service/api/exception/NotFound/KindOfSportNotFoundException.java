package android.kfu.service.api.exception.NotFound;

/**
 * Created by Nurislam on 19.12.2017.
 */
public class KindOfSportNotFoundException extends Error {
    public KindOfSportNotFoundException() {
    }

    public KindOfSportNotFoundException(String message) {
        super(message);
    }

    public KindOfSportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public KindOfSportNotFoundException(Throwable cause) {
        super(cause);
    }

}
