package gov.pcc.pwc.db;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public abstract class Criteria<T> implements Specification<T> {

    protected static final char WILD_CARD = '%';

    protected int perPage = 20;

    protected int currentPage = 0;

    protected String[] sortBy = new String[] {};

    protected Sort.Direction sortDirection = Sort.Direction.ASC;

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String[] getSortBy() {
        return sortBy;
    }

    public void setSortBy(String[] sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Pageable toPageable() {
        return PageRequest.of(getCurrentPage(), getPerPage(), getSortDirection(), getSortBy());
    }

    public abstract String getOrderBy();
}
