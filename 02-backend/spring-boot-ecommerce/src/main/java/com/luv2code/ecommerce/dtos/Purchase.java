package com.luv2code.ecommerce.dtos;

import com.luv2code.ecommerce.entities.Address;
import com.luv2code.ecommerce.entities.Customer;
import com.luv2code.ecommerce.entities.Order;
import com.luv2code.ecommerce.entities.OrderItem;
import lombok.Data;
import java.util.Set;

/*
 Clase que será la base para relacionarla con:
  - Customer.
  - Shipping Address.
  - Billing Address.
  - Order
  - OrderItem[]
 */

@Data // Getters y Setters.
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
