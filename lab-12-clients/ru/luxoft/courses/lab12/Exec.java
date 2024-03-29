package ru.luxoft.courses.lab12;

import java.sql.SQLException;

public class Exec {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();
        Product product1 = new Product(1, "Bread", 1.0f, 100);
        Product product2 = new Product(2, "Milk", 2.5f, 200);
        Product product3 = new Product(3, "Meat", 15.8f, 350);
        Product product4 = new Product(4, "Soap", 100.0f, 1500);
        Product product5 = new Product(5, "Silk", 4058.3f, 10000);
        Product product6 = new Product(6, "Fruits", 2.0f, 200);
        System.out.println("The program Lab12 is running");

        try {
            dao.addProduct(product1);
            dao.addProduct(product2);
            dao.addProduct(product3);
            dao.addProduct(product4);
            dao.addProduct(product5);
            dao.addProduct(product6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            dao.setProduct(product1);
            dao.setProduct(product3);
            dao.setProduct(product5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(dao.getProductById(1));
            System.out.println(dao.getProductById(2));
            System.out.println(dao.getProductById(3));
            System.out.println(dao.getProductById(4));
            System.out.println(dao.getProductById(5));
            System.out.println(dao.getProductById(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dao.removeProduct(1);
            dao.removeProduct(2);
            dao.removeProduct(3);
            dao.removeProduct(4);
            dao.removeProduct(5);
            dao.removeProduct(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
