package android.kfu.service.api.exception;

/**
 * Created by Nurislam on 14.05.2018.
 */
public class TimeIsBookedException extends Exception {

    public TimeIsBookedException() {
        super();
    }

    public TimeIsBookedException(String message) {
        super(message);
    }

    public TimeIsBookedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeIsBookedException(Throwable cause) {
        super(cause);
    }

    protected TimeIsBookedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
