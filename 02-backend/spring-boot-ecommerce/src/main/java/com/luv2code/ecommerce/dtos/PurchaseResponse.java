package com.luv2code.ecommerce.dtos;

import lombok.Data;

// Clase utilizada para enviar un objeto Java como JSON.
@Data // Getters y Setters
public class PurchaseResponse {

    private final String orderTrackingNumber;
}
