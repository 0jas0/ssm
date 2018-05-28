package com.jas.web.model;

public class ExcelModel {
    private String CusomerId;

    private String Comment;

    private String CreatedOnUtc;

    private String IPAddress;

    private String CompanyCode;

    private String provinceName;

    private String cityName;

    public String getCusomerId() {
        return CusomerId;
    }

    public void setCusomerId(String cusomerId) {
        CusomerId = cusomerId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getCreatedOnUtc() {
        return CreatedOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        CreatedOnUtc = createdOnUtc;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        CompanyCode = companyCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
