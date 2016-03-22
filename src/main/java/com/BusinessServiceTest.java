package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 2016/1/8.
 */
public class BusinessServiceTest {

    public static final long cId = 9L;

    public static SessionFactory sessionFactory;
    public static Session session;
    private BusinessService service;

    @Before
    public void setUp() throws Exception {
        service = new BusinessService();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("James A");
        service.saveCustomer(customer);
        assertNotNull(service.getCustomerById(customer.getId()));
        //clean the mess
        service.deleteCustomer(customer);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("James A");
        service.saveCustomer(customer);

        service.deleteCustomer(customer);
        assertNull(service.getCustomerById(customer.getId()));
    }


    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("James A");
        service.saveCustomer(customer);

        customer.setName("Updated name");
        service.updateCustomer(customer);
        assertEquals(service.getCustomerById(customer.getId()).getName(),"Updated name");

        service.deleteCustomer(customer);
    }


//    @Test
//    public void testCascadeUpdate() throws Exception {
//        Customer tempC = new Customer();
//        tempC.setName("Cascade Update");
//        Order tempO = new Order();
//        tempO.setCustomer(tempC);
//        service.saveOrder(tempO);
//
//
//        String NAME = "asd "+ new Random().nextInt(47);
//        Order order = service.getOrderById(6L);
//        order.getCustomer().setName(NAME);
//        service.saveOrder(order);
//        String newCustName = service.getCustomerById(order.getCustomer().getId()).getName();
//        assertEquals(NAME,newCustName);
//
//        service.deleteCustomer(tempC);
//
//    }


    @Test
    public void testCascadeDelete() {
        try {
            Customer c = service.getCustomerById(10L);
            Set<Order> set = c.getOrders();
            service.deleteCustomer(c);

            for (Order order : set) {
                assertNull(order);
            }
        }catch (Exception e){
            e.printStackTrace();
            Customer customer = new Customer();
            service.saveCustomer(customer);
        }

    }
}