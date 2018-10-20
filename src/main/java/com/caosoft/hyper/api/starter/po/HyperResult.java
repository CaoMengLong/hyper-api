package com.caosoft.hyper.api.starter.po;

import com.caosoft.hyper.api.starter.utils.HyperTools;

import java.util.Date;

/***
 * 标准的接口返回数据结构
 */

public class HyperResult {


    Integer status;
    Integer code;
    String message;
    String document;
    Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = HyperTools.getISO8601Timestamp(created_at);
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = HyperTools.getISO8601Timestamp(updated_at);
    }

    String created_at;
    String updated_at;


    /***
     * 快速的设置返回数据 全部数据
     * @param status
     * @param code
     * @param message
     * @param data
     * @param created_at
     * @param updated_at
     * @param document
     */
    public void setParam(Integer status,Integer code,String message,Object data,Date created_at,Date updated_at,String document){
        this.setStatus(status);
        this.setCode(code);
        this.setData(data);
        this.setMessage(message);
        if(created_at==null){
            created_at=new Date();
        }
        if(updated_at==null){
            updated_at=new Date();
        }
        this.setCreated_at(created_at);
        this.setUpdated_at(updated_at);
        this.setDocument(document);
    }

    /***
     * 快速的设置返回数据 简单数据
     * @param status
     * @param code
     * @param message
     * @param data
     */
    public void setParam(Integer status,Integer code,String message,Object data){
        this.setStatus(status);
        this.setCode(code);
        this.setData(data);
        this.setMessage(message);
        this.setCreated_at(new Date());
        this.setUpdated_at(new Date());
        this.setDocument(document);
    }

    /**
     * 获取对象
     */
    public static HyperResult getInstance(){
        return new HyperResult();
    }
}
