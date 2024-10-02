package com.nisum.vibe.cart.app.dto;



import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing an order.
 * <p>
 * This class is used to transfer order data between different layers of the application. It contains details about the
 * order including customer information, order status, payment status, and the list of order items.
 * </p>
 */

public class OrderDTO {

    private String orderId;
    private CustomerDTO customer;
    private List<OrderItemDTO> orderItems;
    private Instant orderDate;
    private Instant createdDate;
    private Instant updatedDate;
    private double totalAmount;
    private double subTotal;
    private BigDecimal discountPrice;
    private Long offerId;
    private int totalQuantity;
    private LocalDateTime estimated_delivery_date;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private Long shippingzipCode;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, CustomerDTO customerDTO, List<OrderItemDTO> orderItems, Instant orderDate, Instant createdDate, Instant updatedDate, double totalAmount, double subTotal, BigDecimal discountPrice, Long offerId, int totalQuantity, LocalDateTime estimated_delivery_date, AddressDTO shippingAddress, AddressDTO billingAddress, Long shippingzipCode, OrderStatus orderStatus, PaymentStatus paymentStatus, PaymentMethod paymentMethod) {
        this.orderId = orderId;
        this.customer = customerDTO;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.totalAmount = totalAmount;
        this.subTotal = subTotal;
        this.discountPrice = discountPrice;
        this.offerId = offerId;
        this.totalQuantity = totalQuantity;
        this.estimated_delivery_date = estimated_delivery_date;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.shippingzipCode = shippingzipCode;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        if (orderDate == null) {
            throw new NullPointerException("OrderDate Cannot be Null");
        }
        this.orderDate = orderDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount == 0 || totalAmount <= 0) {
            throw new IllegalArgumentException("Total Amount Can't be Zero");
        }
        this.totalAmount = totalAmount;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        if (totalQuantity == 0 || totalQuantity <= 0) {
            throw new IllegalArgumentException("The Minimum Quantity should be 1");
        }
        this.totalQuantity = totalQuantity;
    }

    public LocalDateTime getEstimated_delivery_date() {
        return estimated_delivery_date;
    }

    public void setEstimated_delivery_date(LocalDateTime estimated_delivery_date) {
        if (estimated_delivery_date == null) {
            throw new NullPointerException("Estimated_delivery_date cannot be Null");
        }
        this.estimated_delivery_date = estimated_delivery_date;
    }

    public AddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress) {
        if (shippingAddress.getZipcode() == "" || shippingAddress.getState() == "" || shippingAddress.getAddress() == "" || shippingAddress.getCity() == "") {
            throw new NullPointerException("Address field's Can't be Null");
        }
        this.shippingAddress = shippingAddress;
    }

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
        if (billingAddress.getAddress() == "" || billingAddress.getCity() == "" || billingAddress.getState() == "" || billingAddress.getZipcode() == "") {
            throw new NullPointerException("Billing Address Can't be Null");
        }
        this.billingAddress = billingAddress;
    }

    public Long getShippingzipCode() {
        return shippingzipCode;
    }

    public void setShippingzipCode(Long shippingzipCode) {
        if (shippingzipCode == 0 || shippingzipCode <= 0) {
            throw new IllegalArgumentException("Shipping Zipcode should be valid it can't be zero or Negative");
        }
        this.shippingzipCode = shippingzipCode;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
