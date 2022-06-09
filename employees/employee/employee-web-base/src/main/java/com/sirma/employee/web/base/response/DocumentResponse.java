package com.sirma.employee.web.base.response;

public class DocumentResponse {
    public Long id;

    public String name;

    private DocumentResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DocumentResponse of(Long id, String name) {
        return new DocumentResponse(id, name);
    }

}
