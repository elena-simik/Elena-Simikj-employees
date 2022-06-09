package com.sirma.employee.api;

import com.sirma.employee.web.base.mapper.EmployeeMapper;
import com.sirma.employee.web.base.response.DocumentResponse;
import com.sirma.employee.web.base.response.EmployeePairResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee/document")
public class EmployeeController {
    private final EmployeeMapper mapper;

    public EmployeeController(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping
    @CrossOrigin
    public List<DocumentResponse> findAll() {
        return mapper.findAll();
    }

    @PostMapping
    @CrossOrigin
    public List<EmployeePairResponse> processDocument(@RequestParam(required = false) MultipartFile file,
                                                      @RequestParam(required = false) String dateFormat,
                                                      @RequestParam(required = false) Long documentId) throws IOException {
        return mapper.processDocument(file, dateFormat, documentId);
    }

}
