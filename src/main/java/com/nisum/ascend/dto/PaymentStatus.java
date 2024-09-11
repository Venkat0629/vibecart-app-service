package com.nisum.ascend.dto;


public enum PaymentStatus {
    PENDING,            // Payment has been initiated but not yet completed or processed.
    COMPLETED,          // Payment has been successfully completed and processed.
    FAILED,             // Payment has failed due to an error or issue during processing.
    CANCELLED           // Payment has been canceled and will not be processed.
}
