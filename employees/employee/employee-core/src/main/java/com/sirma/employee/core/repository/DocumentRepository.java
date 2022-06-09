package com.sirma.employee.core.repository;

import com.sirma.employee.core.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
