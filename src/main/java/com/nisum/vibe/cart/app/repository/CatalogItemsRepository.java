package com.nisum.vibe.cart.app.repository;

import com.nisum.vibe.cart.app.entities.CatalogItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogItemsRepository extends JpaRepository<CatalogItems, Long> {

}
