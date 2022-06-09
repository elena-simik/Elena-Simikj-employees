package com.sirma.employee.core.service;

import com.sirma.employee.core.model.Document;
import com.sirma.employee.core.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public Document save(MultipartFile file, String dateFormat) throws IOException {
        final Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setBytes(file.getBytes());
        document.setDateFormat(dateFormat);
        return repository.save(document);
    }

    public Document findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Document> findAll() {
        return repository.findAll();
    }

}
