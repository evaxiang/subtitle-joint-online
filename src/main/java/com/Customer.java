package com;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by Andrew on 2015/12/28.
 */
public class Customer implements Serializable{

    private Long id;

    private String lastName;

    private String firstName;

    private Double totalPrice;

    private String age;

    private Set orders = new HashSet<Object>();

//    private Address homeAddress;
//
//    private Address comAddress;

    public Customer(String name,String age,Set orders){
        setName(name);
        this.age = age;
        this.orders = orders;
    }

    public Customer(Long id,String name){
        this.id = id;
        setName(name);
    }

    public Customer(){}


    public Long getId() {
        return id;
    }


//    public Address getHomeAddress() {
//        return homeAddress;
//    }
//
//    public void setHomeAddress(Address homeAddress) {
//        this.homeAddress = homeAddress;
//    }
//
//    public Address getComAddress() {
//        return comAddress;
//    }
//
//    public void setComAddress(Address comAddress) {
//        this.comAddress = comAddress;
//    }


    public Double getTotalPrice() {
        Iterator itr = orders.iterator();
        double totalPrice = 0;
        while(itr.hasNext()){
            Order order = (Order)itr.next();
            totalPrice += Double.valueOf(order.getPrice());
        }
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return firstName+" "+lastName;
    }

    public void setName(String name) {
        StringTokenizer st = new StringTokenizer(name);
        this.firstName = st.nextToken();
        this.lastName = st.nextToken();
    }

    public String getAge() {
        return age;
    }

    private void setAge(String age) {
        this.age = age;
    }

    public Set getOrders() {
        return orders;
    }

    private void setOrders(Set orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return getName()+" "+getAge()+" "+getTotalPrice();
    }
}
