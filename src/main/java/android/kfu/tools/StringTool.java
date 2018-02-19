/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.tools;

/**
 *
 * @author rtmss
 */
public class StringTool {
    
    public static boolean isInBounds(String string, int minLength, int maxLength) {
        if (string == null) {
            return minLength == 0;
        } else {
            if (string.length() < minLength) {
                return false;
            } else {
                if (string.length() > maxLength) {
                    return false;
                }
            }
        }
        return true;
    }
}
