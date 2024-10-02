package com.nisum.vibe.cart.app.repository;

import com.nisum.vibe.cart.app.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
