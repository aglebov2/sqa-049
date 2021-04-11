package ru.luxoft.courses.lab6;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        String[] masOfProducts = new String[5];
        Integer [] masOfCosts = new Integer [5];
        Integer[] masOfCounts = new Integer[5];
        System.out.println("Hello, The program Pokupki starts working");

        Scanner sc = new Scanner (System.in);

        int count = 0;
        System.out.println("Enter Products, Costs and Counts or exit");
        while(sc.hasNext() && count < 5) {
            String s = sc.nextLine();
            if("exit".equals(s)) {
                break;
            }

            String [] parts  = s.split(" ");
            if(parts.length != 3){
                System.out.println("Wrong number of arguments! Retry!");
                continue;
            }

            String productName = parts[0];
            Integer productCost;
            Integer productCount;

            try {
                productCost = Integer.parseInt(parts[1]);
                productCount = Integer.parseInt(parts[2]);
            } catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
                System.out.println("Retry!");
                continue;
            }

            boolean productAlreadyExists = false;
            for (int i = 0; i < count; i++) {
                if(productName.equals(masOfProducts[i])){
                    masOfCosts[i] = productCost;
                    masOfCounts[i] += productCount;
                    productAlreadyExists = true;
                }
            }

            if(!productAlreadyExists) {
                masOfProducts[count] = productName;
                masOfCosts[count] = productCost;
                masOfCounts[count] = productCount;
                count++;
            }
        }

        String[] sortedProducts = new String[count];
        System.arraycopy(masOfProducts, 0, sortedProducts, 0, count);

        Arrays.sort(sortedProducts, (o1, o2) -> {
            int res = String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
            if (res == 0) {
                res = o1.compareTo(o2);
            }
            return res;
        });

        System.out.println("The following products are introduced:");
        System.out.println(Arrays.toString(sortedProducts));

        long sum = 0;
        for (int i = 0; i < count; i++) {
            sum += masOfCosts[i] * masOfCounts[i];
        }
        System.out.println("Summa stoimosti pokupok = " + sum);

        for (int j = 0; j < 3; j++) {
            int indexOfMostPopularProduct = 0;
            for (int i = 0; i < count; i++) {
                if (masOfCounts[i] > masOfCounts[indexOfMostPopularProduct]) {
                    indexOfMostPopularProduct = i;
                }
            }

            System.out.println(Arrays.stream(masOfCounts).filter(Objects::nonNull).
                    max(Integer::compareTo).orElse(0));

            System.out.println("Most popular product is " + masOfProducts[indexOfMostPopularProduct]);
            masOfProducts[indexOfMostPopularProduct] = null;
            masOfCounts[indexOfMostPopularProduct] = 0;
            masOfCosts[indexOfMostPopularProduct] = 0;
        }

    }

}
/*Разница между методами отлова ошибки в том, что в первом случае мы отлавливаем участок своего кода, а во втором отлавливаем ошибку функции Integer.parseInt() которая была написана не нами.
В фунционале массиов не хватает автоматического расширения, в случае если необходимо более 5 элементов необходимо переписывать код.
Если не обходимо динамическое число элементов массива я бы использовал TreeMap (для автоматической сортировки), проверка map.containsKey
        в случае true брал бы по ключю значение и добавлял бы новое количество.
Для реализации каждому пользователю своя корзина надо создать обьект Покупатель(имя, продукт) и через композицию добавить Продукт (название продукта, количество).
        Сортировку по пользователям нужно сделать через компаратор сравнивая по именам(String).
 Для реализации одним массивом необходимо сделать массив обьектов Product(name, price, count).*/
