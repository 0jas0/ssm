package com.jas.web.helper;

import com.fasterxml.jackson.annotation.JsonInclude;

public class FileResponseData {

    /**
     * 返回状态编码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    /**
     * 返回信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * 成功标识
     */
    private boolean success = true;

    /**
     * 文件路径
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filePath;

    /**
     * 文件名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fileName;

    /**
     * 文件类型
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fileType;

    /**
     * Http URL
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String httpUrl;

    /**
     * Http Token
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;


    public FileResponseData(){}

    public FileResponseData(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}