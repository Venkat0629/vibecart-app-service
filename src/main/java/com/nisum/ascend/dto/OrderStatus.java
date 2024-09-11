package com.nisum.ascend.dto;



public enum OrderStatus {
    //    PENDING,          // Order has been placed but not yet confirmed
    SHIPPED,
    CONFIRMED,        // Order has been confirmed
    DISPATCHED,       // Order has been dispatched
    PICKUP_COURIER,   // Order is with the courier for pickup
    ON_THE_WAY,       // Order is on its way
    OUT_FOR_DELIVERY, // Order is out for delivery
    DELIVERED,        // Order has been delivered
    CANCELLED,          // Order has been canceled
    COMPLETED         // Order is Completed
}
