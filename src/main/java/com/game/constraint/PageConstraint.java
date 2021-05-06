package com.game.constraint;

import java.util.Objects;

public final class PageConstraint {

    private Integer pageNumber = 0;
    private Integer pageSize = 3;

    public PageConstraint() {
    }

    public PageConstraint(Integer pageNumber, Integer pageSize) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber != null) {
            this.pageNumber = pageNumber;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null) {
            this.pageSize = pageSize;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageConstraint that = (PageConstraint) o;
        return Objects.equals(pageNumber, that.pageNumber) &&
                Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNumber, pageSize);
    }

    @Override
    public String toString() {
        return "PageConstraint{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
