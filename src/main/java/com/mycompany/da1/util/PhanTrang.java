package com.mycompany.da1.util;

import java.util.List;

public class PhanTrang<T> {

    private Integer pageSize;
    private Integer currentPage;
    private Integer page;
    private Boolean isNext;
    private Boolean isPrev;
    private List<T> lstData;
    private Integer tongTrang;

    // set default 10 bản ghi trên trang, currentPage = vị trí trang hiện tại
    public PhanTrang(List<T> lstData) {
        this.pageSize = 10;
        this.currentPage = 1;
        this.isNext = false;
        this.isPrev = false;
        this.page = 1;
        this.lstData = lstData;
        this.tongTrang = (lstData.size() / 10) + 1;
        setButtonStatus();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getIsNext() {
        return isNext;
    }

    public void setIsNext(Boolean isNext) {
        this.isNext = isNext;
    }

    public Boolean getIsPrev() {
        return isPrev;
    }

    public void setIsPrev(Boolean isPrev) {
        this.isPrev = isPrev;
    }

    public List<T> getLstData() {
        return lstData;
    }

    public void setLstData(List<T> lstData) {
        this.lstData = lstData;
    }

    @Override
    public String toString() {
        return "PhanTrang{"
                + "pageSize=" + pageSize
                + ", currentPage=" + currentPage
                + ", page=" + page
                + ", isNext=" + isNext
                + ", isPrev=" + isPrev
                + ", lstData=" + lstData.size()
                + ", tongTrang=" + tongTrang
                + '}';
    }

    // Function
    public void setButtonStatus() {
        if (this.tongTrang == 1) {
            this.isNext = false;
            this.isPrev = false;
            return;
        }

        if (this.page >= this.tongTrang) {
            this.isNext = false;
            this.isPrev = true;
        } else if (this.page > 1 && this.page < this.tongTrang) {
            this.isNext = true;
            this.isPrev = true;
        } else if (this.page <= 1) {
            this.isPrev = false;
            this.isNext = true;
        }
    }

    public void refreshList(List<T> lstData) {
        this.tongTrang = (lstData.size() / 10) + 1;
        setLstData(lstData);
    }

    public List<T> getListData(int page) {
        this.setPage(page);
        return phanTrang(this.getLstData(), this.getPageSize(), page);
    }

    ;
    
    public <T> List<T> phanTrang(List<T> data, int pageSize, int currentPage) {
        int totalRecords = data.size();
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);

        return data.subList(startIndex, endIndex);
    }
}
