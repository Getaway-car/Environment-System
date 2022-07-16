package com.haoea.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private Object data;
    private String msg;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
