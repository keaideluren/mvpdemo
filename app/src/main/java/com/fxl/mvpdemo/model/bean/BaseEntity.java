package com.fxl.mvpdemo.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/15.
 * 网络请求返回的基本数据结构
 */

public class BaseEntity<E> implements Serializable {
    private int State;
    private String Message;
    private E Data;

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public E getData() {
        return Data;
    }

    public void setData(E Data) {
        this.Data = Data;
    }
}
