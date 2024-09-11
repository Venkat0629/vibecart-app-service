package com.nisum.ascend.repository;

import com.nisum.ascend.entities.CatalogItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogItemsRepository extends JpaRepository<CatalogItems, Long> {

}
