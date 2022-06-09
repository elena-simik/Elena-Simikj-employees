package com.sirma.employee.core.model.dto;

import java.util.Objects;

public class PairDTO<S, T> {
    private S first;
    private T second;

    public PairDTO(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return first;
    }

    public void setFirst(S first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairDTO that = (PairDTO) o;
        return (Objects.equals(getFirst(), that.getFirst()) ||
                Objects.equals(getFirst(), that.getSecond()))
                && (Objects.equals(getSecond(), that.getFirst()) ||
                Objects.equals(getSecond(), that.getSecond()));
    }

    @Override
    public int hashCode() {
        int hashFirst = getFirst().hashCode();
        int hashSecond = getSecond().hashCode();
        int maxHash = Math.max(hashFirst, hashSecond);
        int minHash = Math.min(hashFirst, hashSecond);
        return Objects.hash(minHash, maxHash);
    }

}
