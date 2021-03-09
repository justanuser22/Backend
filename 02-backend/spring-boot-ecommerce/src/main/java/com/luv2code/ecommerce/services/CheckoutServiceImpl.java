package com.luv2code.ecommerce.services;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dtos.Purchase;
import com.luv2code.ecommerce.dtos.PurchaseResponse;
import com.luv2code.ecommerce.entities.Customer;
import com.luv2code.ecommerce.entities.Order;
import com.luv2code.ecommerce.entities.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Recuperar la información del pedido del DTO.
        Order order = purchase.getOrder();

        // Generar el número de tracking.
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // Popular el pedido con la colección de items.
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // Popular la orden con las direcciones de Shipping Address y Billing Address.
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // Popular al cliente con la orden.
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // Guardar en la base de datos.
        customerRepository.save(customer);

        // Retornar una respuesta.
        return new PurchaseResponse(orderTrackingNumber);
    }

    // Este método es para generar una ID única que sea difícil de adivinar y que sea aleatoria.
    private String generateOrderTrackingNumber() {

        // Generar una UUID aleatoria (UUID version-4)
        // UUID -> Identifiador universalmente único.
        return UUID.randomUUID().toString();
    }
}
