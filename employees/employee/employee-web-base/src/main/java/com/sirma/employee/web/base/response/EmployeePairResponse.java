package com.sirma.employee.web.base.response;

import java.util.List;

public class EmployeePairResponse {

    public Long totalDaysWorked;
    public List<ProjectEmployeePairResponse> employeeProjectPairs;

    private EmployeePairResponse(Long totalDaysWorked, List<ProjectEmployeePairResponse> employeeProjectPairs) {
        this.totalDaysWorked = totalDaysWorked;
        this.employeeProjectPairs = employeeProjectPairs;
    }

    public static EmployeePairResponse of(Long totalDaysWorked, List<ProjectEmployeePairResponse> employeeProjectPairs) {
        return new EmployeePairResponse(totalDaysWorked, employeeProjectPairs);
    }

}
