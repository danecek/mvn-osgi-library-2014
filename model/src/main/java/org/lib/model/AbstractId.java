package org.lib.model;

public abstract class AbstractId<T extends AbstractId>
        implements Comparable<T> {

    private final int id;

    public AbstractId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        T bookId = (T) o;
        return compareTo(bookId) == 0;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public int compareTo(T t) {
        return id - t.getId();
    }

}