package com;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * Created by Andrew on 2015/12/29.
 */
public class BusinessService {
    public static SessionFactory sessionFactory;
    public static Session session;

    static {
        try {
            Configuration config = new Configuration().configure();
            sessionFactory = config.buildSessionFactory();
            session = sessionFactory.openSession();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    public void test(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = new Customer("michael","12",new HashSet<Object>());
        session.save(customer);
        session.getTransaction().commit();
        session.close();

    }

    public void load(){
        Session session = sessionFactory.openSession();
        session.get(Customer.class, "1");

    }

    public void loadOrder(){
        Session session = sessionFactory.openSession();
        Query query =  session.createQuery("from Order o where o.customer.id=:customer_id ");
        query.setParameter("customer_id",1L);
        List list =  query.list();
        System.out.println(list);
    }

    public void saveTransientCustomer(){

        session.beginTransaction();
        Customer c = new Customer("Andrew A","30",new HashSet<Object>());
//        session.save(c);

        Order order = new Order();
        order.setId(4L);
        session.save(order);

        session.getTransaction().commit();
        System.out.println("保存完成");
    }

    public void practiseOne(){
        Customer c = new Customer();
        c.setName("Simon Zhang");
        session.beginTransaction();
        session.save(c);
        System.out.println(c.getId());
        session.getTransaction().commit();
    }

    public void dirtyCheck(){
        session.beginTransaction();
        Customer customer = (Customer)session.get(Customer.class,new Long(1));
        customer.setName("Michael Jackson");
        session.flush();
        customer.setName("Justin Bibber");
        session.getTransaction().commit();

    }

    public void findAll(){
        Session session = sessionFactory.openSession();
        Query query =  session.createQuery("from Customer as a order by a.name asc ");
        List list =  query.list();
        System.out.println(Arrays.toString(list.toArray()));
    }

    public void cascadeSave(){
        session.beginTransaction();
        Customer customer = new Customer();

        Order o1 = new Order("1", "2", customer);
        session.save(o1);
        session.getTransaction().commit();
    }

    public void cascadeUpdate(){
        session.beginTransaction();
        Order o1=(Order)session.get(Order.class,5L);
        o1.getCustomer().setName("Order5 customer");
        session.save(o1);
        session.getTransaction().commit();
    }

    public void cascadeDelete(){
        session.beginTransaction();
        Customer c = (Customer)session.get(Customer.class, 10L);
        session.delete(c);
        session.getTransaction().commit();
    }

    public void AddWithInserve(){
        session.beginTransaction();
        Customer c = (Customer)session.get(Customer.class, 5L);
        Order o = (Order)session.get(Order.class,3L);

        c.getOrders().add(o);
        o.setCustomer(c);

        session.getTransaction().commit();
    }

    public void AllDeleteOrphan(){
        session.beginTransaction();
        Customer c = (Customer)session.get(Customer.class, 5L);
        Order order = (Order)c.getOrders().iterator().next();
        order.setCustomer(null);
        c.getOrders().remove(order);

        session.getTransaction().commit();
    }

    public void deleteTest(){
        session.beginTransaction();
        Customer c = new Customer();
        session.delete(c);
        System.out.println("ID:" + c.getId());
        System.out.println("contain? " + session.contains(c));
        System.out.println("customer:" + c.toString());
        session.getTransaction().commit();
        System.out.println("after commit:");
        System.out.println("ID:" + c.getId());
        System.out.println("contain? " + session.contains(c));
        System.out.println("customer:" + c.toString());
    }


    public void testCategoryCascadeGet(){
        List list = session.createQuery("from Category c where c.id=:id").setParameter("id",1L).list();
        System.out.println();
    }

    public Customer getCustomerById(Long id){
        return (Customer)session.createQuery("from Customer c where c.id=:id").setParameter("id",id).uniqueResult();
    }

    public Order getOrderById(Long id){
        return (Order)session.createQuery("from Order o where o.id=:id").setParameter("id",id).uniqueResult();
    }

    public void saveCustomer(Customer customer){
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
    }

    public void deleteCustomer(Customer customer){
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
    }

    public void updateCustomer(Customer customer){
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
    }

    public void saveOrder(Order order){
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }



    public static void main(String[] args) {
        new BusinessService().testCategoryCascadeGet();
    }

}
