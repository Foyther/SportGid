/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.kfu.service.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResult {
    
    private int code;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object body;

    public ApiResult(int code, Object body) {
        this.code = code;
        this.body = body;
    }

    public ApiResult(int code) {
        this.code = code;
    }

    public ApiResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
    
    
    
    
}
