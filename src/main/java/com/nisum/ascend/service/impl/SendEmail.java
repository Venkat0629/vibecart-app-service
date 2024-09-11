package com.nisum.ascend.service.impl;


import com.nisum.ascend.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.format.DateTimeFormatter;

@Service
public class SendEmail {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ISpringTemplateEngine templateEngine;

    public void sendOrderConfirmationEmail(String to, OrderDTO orderDTO) {
        Context context = new Context();
        context.setVariable("orderId", orderDTO.getOrderId());
        context.setVariable("customerName", orderDTO.getCustomer().getCustomerName());
        context.setVariable("shippingZipCode", orderDTO.getShippingAddress().getZipcode());
        context.setVariable("totalQuantity", orderDTO.getTotalQuantity());
        context.setVariable("orderDate", orderDTO.getOrderDate());
        context.setVariable("payment", orderDTO.getPaymentMethod());
        context.setVariable("subTotal", orderDTO.getSubTotal());
        context.setVariable("discountPrice", orderDTO.getDiscountPrice());
        context.setVariable("totalAmount", orderDTO.getTotalAmount());

        context.setVariable("orderStatus", orderDTO.getOrderStatus());
        context.setVariable("estimatedDeliveryDate", orderDTO.getEstimated_delivery_date().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));


        String body = templateEngine.process("orderConfirmation", context);
        String subject = String.format("Order Confirmation - Order ID: %s", orderDTO.getOrderId());

        // sendEmail(to, subject, body);

        try {
            sendEmail(to, subject, body);
        } catch (Exception e) {
            // Log error and continue
            // Use a logging framework like SLF4J for logging
            System.err.println("Failed to send order confirmation email: " + e.getMessage());
        }
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
