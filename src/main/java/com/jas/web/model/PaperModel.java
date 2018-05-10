package com.jas.web.model;

import java.util.List;

public class PaperModel<T> {
    private Integer currentPage = 1;

    private int pageSize = 10;

    private int pageTotal;

    private int recordTotal;

    private List<T> data;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        pageTotal = (recordTotal + pageSize - 1) / pageSize;
        return pageTotal;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
