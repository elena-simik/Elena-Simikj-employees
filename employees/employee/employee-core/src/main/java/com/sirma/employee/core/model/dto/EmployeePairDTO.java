package com.sirma.employee.core.model.dto;

import java.util.List;

public class EmployeePairDTO {
    private Long totalDaysWorked;
    private List<ProjectEmployeePairDTO> employeeProjectPairs;
    public EmployeePairDTO(Long totalDaysWorked, List<ProjectEmployeePairDTO> employeeProjectPairs) {
        this.totalDaysWorked = totalDaysWorked;
        this.employeeProjectPairs = employeeProjectPairs;
    }
    public Long getTotalDaysWorked() {
        return totalDaysWorked;
    }

    public void setTotalDaysWorked(Long totalDaysWorked) {
        this.totalDaysWorked = totalDaysWorked;
    }
    public List<ProjectEmployeePairDTO> getEmployeeProjectPairs() {
        return employeeProjectPairs;
    }

    public void setEmployeeProjectPairs(List<ProjectEmployeePairDTO> employeeProjectPairs) {
        this.employeeProjectPairs = employeeProjectPairs;
    }

}
