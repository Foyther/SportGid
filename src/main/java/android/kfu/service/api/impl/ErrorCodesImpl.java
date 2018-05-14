/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.impl;

import android.kfu.service.api.ErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class ErrorCodesImpl implements ErrorCodes {

    @Override
    public int getSuccess() {
        return 0;
    }

    @Override
    public int getInvalidOrOldAccessToken() {
        return 1;
    }

    @Override
    public int getPermissionDenied() {
        return 2;
    }

    @Override
    public int getNotFound() {
        return 3;
    }

    @Override
    public int getInvalidRequest() {
        return 4;
    }

    @Override
    public int getEmptyText() {
        return 5;
    }

    @Override
    public int getBigText() {
        return 6;
    }

    @Override
    public int getBigPhoto() {
        return 7;
    }

    @Override
    public int getTimeIsBooked() {
        return 8;
    }

    @Override
    public int getInvalidLoginOrPassword() {
        return 10;
    }

    @Override
    public int getInvalidLogin() {
        return 11;
    }

    @Override
    public int getLongPassword() {
        return 12;
    }

    @Override
    public int getShortPassword() {
        return 13;
    }

    @Override
    public int getUserWithSameLoginAlreadyExists() {
        return 14;
    }

    @Override
    public int getShortName() {
        return 15;
    }

    @Override
    public int getLongName() {
        return 16;
    }

    @Override
    public int getLongAbout() {
        return 17;
    }

    @Override
    public int getInvalidPassword() {
        return 18;
    }

    @Override
    public int getInvalidForm() {
        return 19;
    }

    @Override
    public int getShortTitle() {
        return 20;
    }

    @Override
    public int getLongTitle() {
        return 21;
    }

    @Override
    public int getLongDescription() {
        return 22;
    }

}
