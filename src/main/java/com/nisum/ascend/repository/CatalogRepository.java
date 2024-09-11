package com.nisum.ascend.repository;

import com.nisum.ascend.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
