package com.sirma.employee.core.model.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeDTO {
    private Long employeeId;
    private Long projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public EmployeeDTO(Long employeeId, Long projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public static EmployeeDTO fromString(String line, String dateFormat) {
        final String[] lineParts = line.startsWith("\uFEFF") ?
                line.substring(1).split(",") : line.split(",");

        if (lineParts.length != 4) {
            throw new RuntimeException("Please upload a .csv file with the correct data");
        }

        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        final Long employeeIdFirst = Long.parseLong(lineParts[0]);
        final Long employeeIdSecond = Long.parseLong(lineParts[1]);
        final LocalDate dateFrom = lineParts[2].equalsIgnoreCase("NULL")
                ? LocalDate.now() : LocalDate.parse(lineParts[2], dateFormatter);
        final LocalDate dateTo = lineParts[3].equalsIgnoreCase("NULL")
                ? LocalDate.now() : LocalDate.parse(lineParts[3], dateFormatter);
        return new EmployeeDTO(employeeIdFirst, employeeIdSecond, dateFrom, dateTo);
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

}
