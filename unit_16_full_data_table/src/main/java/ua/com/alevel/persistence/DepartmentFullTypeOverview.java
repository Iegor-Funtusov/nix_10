package ua.com.alevel.persistence;

public record DepartmentFullTypeOverview(long JS, long JAVA, long KOTLIN, long DEV_OPS) {

    public long getJS() {
        return JS;
    }

    public long getJAVA() {
        return JAVA;
    }

    public long getKOTLIN() {
        return KOTLIN;
    }

    public long getDEV_OPS() {
        return DEV_OPS;
    }

    @Override
    public String toString() {
        return "DepartmentFullTypeOverview{" +
                "JS=" + JS +
                ", JAVA=" + JAVA +
                ", KOTLIN=" + KOTLIN +
                ", DEV_OPS=" + DEV_OPS +
                '}';
    }
}
