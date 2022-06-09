package com.sirma.employee.web.base.response;

public class PairResponse {

    public Long first;

    public Long second;

    private PairResponse(Long first, Long second) {
        this.first = first;
        this.second = second;
    }

    public static PairResponse of(Long first, Long second) {
        return new PairResponse(first, second);
    }

}
