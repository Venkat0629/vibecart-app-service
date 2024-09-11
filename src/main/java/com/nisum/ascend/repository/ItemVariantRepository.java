package com.nisum.ascend.repository;

import com.nisum.ascend.dto.Color;
import com.nisum.ascend.dto.Size;
import com.nisum.ascend.entities.ItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant,Long> {

    @Query(value = "SELECT iv.*\n" +
            "FROM item_variant iv\n" +
            "JOIN item_master im ON iv.item_id = im.item_id\n" +
            "JOIN (\n" +
            "    SELECT iv.item_id, MIN(iv.sku_id) as min_sku_id\n" +
            "    FROM item_variant iv\n" +
            "    GROUP BY iv.item_id\n" +
            ") as iv_min ON iv.sku_id = iv_min.min_sku_id limit :limited",
            nativeQuery = true)
    List<ItemVariant> findLimitedSkuItems(@Param("limited") Integer limited);
    List<ItemVariant> findByItemMaster_itemID(Long id);
    List<ItemVariant> findByItemMaster_ItemIDAndColorAndSize(Long itemID, Color color, Size size);
}
