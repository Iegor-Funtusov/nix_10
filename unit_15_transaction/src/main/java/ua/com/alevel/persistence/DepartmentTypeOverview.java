package ua.com.alevel.persistence;

import ua.com.alevel.persistence.type.DepartmentType;

public record DepartmentTypeOverview(DepartmentType departmentType, long count) {

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "DepartmentTypeOverview{" +
                "departmentType=" + departmentType +
                ", count=" + count +
                '}';
    }
}
