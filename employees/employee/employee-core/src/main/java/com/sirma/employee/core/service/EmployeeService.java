package com.sirma.employee.core.service;

import com.sirma.employee.core.model.dto.EmployeeDTO;
import com.sirma.employee.core.model.dto.EmployeePairDTO;
import com.sirma.employee.core.model.dto.PairDTO;
import com.sirma.employee.core.model.dto.ProjectEmployeePairDTO;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class EmployeeService {
    public List<EmployeePairDTO> getEmployeePairs(Collection<EmployeeDTO> employeesDTO) {
        final Map<Long, List<EmployeeDTO>> employeesByProjectId = employeesDTO.stream()
                .collect(Collectors.groupingBy(EmployeeDTO::getProjectId));

        final Map<PairDTO<Long, Long>, List<ProjectEmployeePairDTO>> employeePairMap = employeesByProjectId
                .keySet()
                .stream()
                .map(key -> mapToEmployeePairs(employeesByProjectId.get(key)))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(ProjectEmployeePairDTO::getEmployeePair));

        final Map<Long, List<PairDTO<Long, Long>>> pairByWorkDays = employeePairMap.keySet().stream()
                .collect(Collectors.groupingBy(key ->
                        employeePairMap.get(key).stream().mapToLong(ProjectEmployeePairDTO::getDaysWorked).sum()));

        final Long maxDaysWorked = pairByWorkDays.keySet().stream().mapToLong(it -> it).max().orElse(0);
        return pairByWorkDays.get(maxDaysWorked).stream()
                .map(key -> new EmployeePairDTO(maxDaysWorked, employeePairMap.get(key)))
                .collect(Collectors.toList());
    }

    private List<ProjectEmployeePairDTO> mapToEmployeePairs(List<EmployeeDTO> projectEmployees) {
        return IntStream.range(0, projectEmployees.size())
                .mapToObj(index -> {
                    final EmployeeDTO firstEmployee = projectEmployees.get(index);
                    return mapToProjectEmployeePair(firstEmployee, projectEmployees.subList(index + 1, projectEmployees.size()));
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ProjectEmployeePairDTO> mapToProjectEmployeePair(EmployeeDTO firstEmployee, List<EmployeeDTO> coworkers) {
        return coworkers.stream()
                .filter(areWorkingTogether(firstEmployee))
                .map(secondEmployee -> {
                    final LocalDate dateFrom = firstEmployee.getDateFrom().isAfter(secondEmployee.getDateFrom())
                            ? firstEmployee.getDateFrom()
                            : secondEmployee.getDateFrom();
                    final LocalDate dateTo = firstEmployee.getDateFrom().isBefore(secondEmployee.getDateTo())
                            ? firstEmployee.getDateTo()
                            : secondEmployee.getDateTo();
                    return new ProjectEmployeePairDTO(new PairDTO<Long, Long>(firstEmployee.getEmployeeId(), secondEmployee.getEmployeeId()),
                            firstEmployee.getProjectId(), DAYS.between(dateFrom, dateTo));
                })
                .collect(Collectors.toList());
    }

    private Predicate<EmployeeDTO> areWorkingTogether(EmployeeDTO firstEmployee) {
        return secondEmployee -> (firstEmployee.getDateFrom().isBefore(secondEmployee.getDateTo()) ||
                firstEmployee.getDateFrom().isEqual(secondEmployee.getDateTo()))
                && (secondEmployee.getDateFrom().isBefore(firstEmployee.getDateTo())
                || secondEmployee.getDateFrom().isEqual(firstEmployee.getDateTo()));
    }

}
