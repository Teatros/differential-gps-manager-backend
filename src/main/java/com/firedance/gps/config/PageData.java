package com.firedance.gps.config;

import java.util.List;

/**
 * @author chong.sun
 */
public class PageData<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer currentSize;

    private Long total;

    private List<T> data;

    private Object summaryData;

    private Object extraData;

    public PageData() {
    }

    public PageData(Integer pageNum, Integer pageSize, Integer currentSize, Long total, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.currentSize = currentSize;
        this.total = total;
        this.data = data;
    }

    public static <T> PageDataBuilder<T> builder() {
        return new PageDataBuilder<>();
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Integer currentSize) {
        this.currentSize = currentSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Object getSummaryData() {
        return summaryData;
    }

    public void setSummaryData(Object summaryData) {
        this.summaryData = summaryData;
    }

    public Object getExtraData() {
        return extraData;
    }

    public void setExtraData(Object extraData) {
        this.extraData = extraData;
    }

    public static final class PageDataBuilder<T> {

        private final PageData<T> pageData = new PageData<>();

        private PageDataBuilder() {
        }

        public PageDataBuilder<T> pageNum(Integer pageNum) {
            pageData.setPageNum(pageNum);
            return this;
        }

        public PageDataBuilder<T> pageSize(Integer pageSize) {
            pageData.setPageSize(pageSize);
            return this;
        }

        public PageDataBuilder<T> currentSize(Integer currentSize) {
            pageData.setCurrentSize(currentSize);
            return this;
        }

        public PageDataBuilder<T> total(Long total) {
            pageData.setTotal(total);
            return this;
        }

        public PageDataBuilder<T> data(List<T> data) {
            pageData.setData(data);
            return this;
        }

        public PageDataBuilder<T> summaryData(Object summaryData) {
            pageData.setSummaryData(summaryData);
            return this;
        }

        public PageDataBuilder<T> extraData(Object extraData) {
            pageData.setExtraData(extraData);
            return this;
        }

        public PageData<T> build() {return pageData;}
    }
}
