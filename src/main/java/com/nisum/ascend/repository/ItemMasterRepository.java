package com.nisum.ascend.repository;

import com.nisum.ascend.entities.ItemMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long> {

    List<ItemMaster> findByCategory_CategoryNameOrderByPriceAsc(String categoryName);

    List<ItemMaster> findByCategory_CategoryNameOrderByPriceDesc(String categoryName);

    List<ItemMaster> findAllByOrderByPriceAsc();

    List<ItemMaster> findAllByOrderByPriceDesc();

    @Query(value = "SELECT im.*\n" +
            "FROM item_master im\n" +
            "JOIN catalog_items ci ON im.item_id = ci.item_id\n" +
            "JOIN catalog c ON ci.catalog_id = c.catalog_id\n" +
            "WHERE c.catalog_name = :catalogName",
    nativeQuery = true)
    List<ItemMaster> findItemsByCatalogName(@Param("catalogName") String catalogName);

    @Query(value = "SELECT im.*\n" +
            "FROM item_master im\n" +
            "JOIN catalog_items ci ON im.item_id = ci.item_id\n" +
            "JOIN catalog c ON ci.catalog_id = c.catalog_id\n" +
            "WHERE c.catalog_name = :catalogName ORDER BY im.price ASC",
            nativeQuery = true)
    List<ItemMaster> findItemsByCatalogNameOrderByPriceAsc(@Param("catalogName") String catalogName);

    @Query(value = "SELECT im.*\n" +
            "FROM item_master im\n" +
            "JOIN catalog_items ci ON im.item_id = ci.item_id\n" +
            "JOIN catalog c ON ci.catalog_id = c.catalog_id\n" +
            "WHERE c.catalog_name = :catalogName ORDER BY im.price DESC",
            nativeQuery = true)
    List<ItemMaster> findItemsByCatalogNameOrderByPriceDesc(@Param("catalogName") String catalogName);

    List<ItemMaster> findAllByOrderByPriceDesc(Pageable pageable);

    @Query(value = "WITH RECURSIVE Subcategories AS (\n" +
            "            SELECT category_id\n" +
            "    FROM category\n" +
            "            WHERE category_name = :categoryName\n" +
            "\n" +
            "            UNION ALL\n" +
            "\n" +
            "            SELECT c.category_id\n" +
            "            FROM category c\n" +
            "            INNER JOIN Subcategories sc ON c.parent_category_id = sc.category_id\n" +
            "    )\n" +
            "    SELECT im.*\n" +
            "    FROM item_master im\n" +
            "    JOIN category cat ON im.category_id = cat.category_id\n" +
            "    WHERE cat.category_id IN (SELECT category_id FROM Subcategories);" , nativeQuery = true)
    List<ItemMaster> findItemsByCategoryName(@Param("categoryName") String categoryName);

}
