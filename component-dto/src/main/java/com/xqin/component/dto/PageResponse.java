package com.xqin.component.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Response with batch page record to return,
 * usually use in page query
 */
public class PageResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private Long totalCount = 0l;

    private int pageSize = 1;

    private int pageNo = 1;

    private Collection<T> data;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        if (pageSize < 1) {
            return 1;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
    }

    public int getPageNo() {
        if (pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
    }

    public List<T> getData() {
        if (null == data) {
            return Collections.emptyList();
        }
        if (data instanceof List) {
            return (List<T>) data;
        }
        return new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

//    public int getTotalPages() {
//        return this.totalCount % this.pageSize == 0 ? this.totalCount
//            / this.pageSize : (this.totalCount / this.pageSize) + 1;
//    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static PageResponse buildSuccess() {
        PageResponse response = new PageResponse();
        response.setSuccess(true);
        return response;
    }

    public static PageResponse buildFailure(Integer code, String msg) {
        PageResponse response = new PageResponse();
        response.setSuccess(false);
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static <T> PageResponse<T> buildSuccess(int pageSize, int pageNo) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(Collections.emptyList());
        response.setTotalCount(0l);
        response.setPageSize(pageSize);
        response.setPageNo(pageNo);
        return response;
    }
    public static <T> PageResponse<T> buildSuccess(Collection<T> data, Long totalCount) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        return response;
    }
    public static <T> PageResponse<T> buildSuccess(Collection<T> data, Long totalCount, int pageSize, int pageNo) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageNo(pageNo);
        return response;
    }

}
