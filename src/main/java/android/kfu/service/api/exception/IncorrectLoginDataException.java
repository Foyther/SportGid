/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.exception;

/**
 *
 * @author rtmss
 */
public class IncorrectLoginDataException extends Exception {

    public IncorrectLoginDataException() {
    }

    public IncorrectLoginDataException(String message) {
        super(message);
    }

    public IncorrectLoginDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectLoginDataException(Throwable cause) {
        super(cause);
    }
    
}
