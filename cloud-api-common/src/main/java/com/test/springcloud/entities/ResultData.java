package com.test.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {

    private int code;
    private String msg;
    private T data;

    public ResultData(int code,String msg){
        this(code,msg,null);
    }
}
