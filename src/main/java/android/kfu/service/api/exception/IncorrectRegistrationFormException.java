/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.exception;

public class IncorrectRegistrationFormException extends Exception {

    
    public static final int ERROR_CODE_INCORRECT_LOGIN_OR_PASSWORD = 1;
    
    public static final int ERROR_CODE_NAME_LONG = 2;
    
    public static final int ERROR_CODE_NAME_SHORT = 4;
    
    public static final int ERROR_CODE_PASSWORD_LONG = 8;
    
    public static final int ERROR_CODE_PASSWORD_SHORT = 16;
    
    private int errorCode;
    
    public IncorrectRegistrationFormException() {
    }

    public IncorrectRegistrationFormException(String message) {
        super(message);
    }

    public IncorrectRegistrationFormException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRegistrationFormException(Throwable cause) {
        super(cause);
    }

    /**
     * 
     * @return 0 if no errors, else sum of error codes (error codes is powers of 2)
     */
    public int getErrorCodeSum() {
        return errorCode;
    }

    public void setErrorCodeSum(int errorCode) {
        this.errorCode = errorCode;
    }
}
