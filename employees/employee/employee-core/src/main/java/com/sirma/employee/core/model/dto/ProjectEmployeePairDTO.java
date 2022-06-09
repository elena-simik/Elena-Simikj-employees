package com.sirma.employee.core.model.dto;

public class ProjectEmployeePairDTO {
    private PairDTO<Long, Long> employeePair;
    private Long projectId;
    private Long daysWorked;

    public ProjectEmployeePairDTO(PairDTO<Long, Long> employeePair, Long projectId, Long daysWorked) {
        this.employeePair = employeePair;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public PairDTO<Long, Long> getEmployeePair() {
        return employeePair;
    }

    public void setEmployeePair(PairDTO<Long, Long> employeePair) {
        this.employeePair = employeePair;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(Long daysWorked) {
        this.daysWorked = daysWorked;
    }

}
