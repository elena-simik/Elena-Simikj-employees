package com.sirma.employee.web.base.response;

public class ProjectEmployeePairResponse {
    public PairResponse employeePair;
    public Long projectId;
    public Long daysWorked;

    private ProjectEmployeePairResponse(PairResponse employeePair, Long projectId, Long daysWorked) {
        this.employeePair = employeePair;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public static ProjectEmployeePairResponse of(PairResponse employeePair, Long projectId, Long daysWorked) {
        return new ProjectEmployeePairResponse(employeePair, projectId, daysWorked);
    }

}
