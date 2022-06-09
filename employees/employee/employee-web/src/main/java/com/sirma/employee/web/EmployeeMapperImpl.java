package com.sirma.employee.web;

import com.sirma.employee.core.model.Document;
import com.sirma.employee.core.model.dto.EmployeeDTO;
import com.sirma.employee.core.model.dto.ProjectEmployeePairDTO;
import com.sirma.employee.core.service.DocumentService;
import com.sirma.employee.core.service.EmployeeService;
import com.sirma.employee.web.base.mapper.EmployeeMapper;
import com.sirma.employee.web.base.response.DocumentResponse;
import com.sirma.employee.web.base.response.EmployeePairResponse;
import com.sirma.employee.web.base.response.PairResponse;
import com.sirma.employee.web.base.response.ProjectEmployeePairResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class EmployeeMapperImpl implements EmployeeMapper {
    private final DocumentService documentService;
    private final EmployeeService employeeService;

    public EmployeeMapperImpl(DocumentService documentService, EmployeeService employeeService) {
        this.documentService = documentService;
        this.employeeService = employeeService;
    }

    @Override
    public List<EmployeePairResponse> processDocument(MultipartFile file, String dateFormat, Long documentId) throws IOException {
        if (file != null && !file.getContentType().equals("text/csv")) {
            throw new RuntimeException("This file type is not supported. Please select a .csv file");
        }
        final Document document = documentId != null ? documentService.findById(documentId) : documentService.save(file, dateFormat);
        final String formatOfDate = dateFormat != null ? dateFormat : document.getDateFormat();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(document.getBytes())));
        final List<EmployeeDTO> employeesDTO = bufferedReader
                .lines()
                .map(line -> EmployeeDTO.fromString(line, formatOfDate))
                .toList();
        return mapToResponse(employeesDTO);
    }

    @Override
    public List<DocumentResponse> findAll() {
        return documentService.findAll().stream()
                .map(document -> DocumentResponse.of(document.getId(), document.getName()))
                .toList();
    }

    private List<EmployeePairResponse> mapToResponse(List<EmployeeDTO> employeesDTO) {
        return employeeService.getEmployeePairs(employeesDTO).stream().map(employeePairDTO -> {
            final List<ProjectEmployeePairDTO> projectEmployeePairDTOS = employeePairDTO.getEmployeeProjectPairs();
            final List<ProjectEmployeePairResponse> projectEmployeePairResponses = projectEmployeePairDTOS.stream().map(projectEmployeePairDTO -> ProjectEmployeePairResponse
                    .of(PairResponse.of(projectEmployeePairDTO.getEmployeePair().getFirst(),
                                    projectEmployeePairDTO.getEmployeePair().getSecond()),
                            projectEmployeePairDTO.getProjectId(),
                            projectEmployeePairDTO.getDaysWorked())).toList();
            return EmployeePairResponse.of(employeePairDTO.getTotalDaysWorked(), projectEmployeePairResponses);
        }).toList();
    }

}
