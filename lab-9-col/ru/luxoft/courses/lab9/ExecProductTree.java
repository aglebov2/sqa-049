package ru.luxoft.courses.lab9;

import java.util.Scanner;
import java.util.TreeMap;

public class ExecProductTree {

    public static void main(String[] args) {

        TreeMap<String, TreeMap<String, Integer>> clients = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, this is sample of TreeMap");

        System.out.println("Enter Name, ProductName and Count or exit");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("exit")) {
                break;
            }

            String[] parts = s.split(" ");
            String name = parts[0];
            String productName = parts[1];
            Integer count = Integer.parseInt(parts[2]);

            clients.putIfAbsent(name, new TreeMap<>());

            TreeMap<String, Integer> temp = clients.get(name);
            temp.putIfAbsent(productName, 0);
            temp.computeIfPresent(productName, (k, v) -> v + count);
        }

        clients.forEach((key, value) -> {
            System.out.println("buyer's name :" + key + ":");
            value.forEach((k, v) -> System.out.printf("Product's name: %s Value = %d%n", k, v));
        });
        System.out.println("Size of treemap = " + clients.size());

    }
}

