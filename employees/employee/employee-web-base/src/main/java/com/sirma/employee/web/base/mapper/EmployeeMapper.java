package com.sirma.employee.web.base.mapper;

import com.sirma.employee.web.base.response.DocumentResponse;
import com.sirma.employee.web.base.response.EmployeePairResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeMapper {

    public List<EmployeePairResponse> processDocument(MultipartFile file, String dateFormat, Long documentId) throws IOException;

    public List<DocumentResponse> findAll();
}
