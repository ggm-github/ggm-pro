// Copyright 2016 Baidu Inc. All rights reserved.

package com.uway.sports.web.entity.dbentity;

import com.google.common.base.Objects;

/**
 * The base paged request.
 *
 * @author Alex Lu (lufei@baidu.com)
 */
public abstract class BasePagedRequest {

    protected Integer pageNo = 1;
    protected Integer pageSize = 10;
    protected String orderBy = "sort";
    protected String order = "desc";
    protected Integer offset=0;

    public BasePagedRequest(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{BasePagedRequest, pageNo=");
        builder.append(pageNo);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(", orderBy=");
        builder.append(orderBy);
        builder.append(", order=");
        builder.append(order);
        builder.append("}");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasePagedRequest)) {
            return false;
        }
        BasePagedRequest that = (BasePagedRequest) o;
        return pageNo == that.pageNo
                && pageSize == that.pageSize
                && Objects.equal(orderBy, that.orderBy)
                && order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pageNo, pageSize, orderBy, order);
    }
}
