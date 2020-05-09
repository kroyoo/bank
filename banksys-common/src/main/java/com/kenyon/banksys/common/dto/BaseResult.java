package com.kenyon.banksys.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Kenyon
 * @title: BaseResult
 * @projectName bank
 * @description: TODO
 * @date 9/8/20199:35 PM
 */

@Data
public class BaseResult implements Serializable{
    private static final String RESULT_OK = "ok";
    private static final String RESULT_NOT_OK = "not_ok";
    private String result;
    private Object data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult Ok() {
        return createResult(RESULT_OK, null, "操作成功", null, null);
    }

    public static BaseResult Ok(String success) {
        return createResult(RESULT_OK, null, success, null, null);
    }

    public static BaseResult Ok(Object data) {
        return createResult(RESULT_OK, data, "操作成功", null, null);
    }

    public static BaseResult Ok(Object data, Cursor cursor) {
        return createResult(RESULT_OK, data, "操作成功", cursor, null);
    }

    public static BaseResult notOk() {
        return createResult(RESULT_NOT_OK, null, "", null, null);
    }

    public static BaseResult notOk(List<Error> errors) {
        return createResult(RESULT_NOT_OK, null, "", null, errors);
    }


    private  static BaseResult createResult(String result, Object data, String success, Cursor cursor, List<Error> errors) {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);
        return baseResult;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Cursor {
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private String field;
        private String massage;
    }
}
