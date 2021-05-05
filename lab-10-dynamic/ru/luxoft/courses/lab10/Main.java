package ru.luxoft.courses.lab10;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{2, 3, 1, 32, 31};
        System.out.println("Программа Lab10: Пример обобщений ");

        GenericListAggregator<Integer> listAggregator = new GenericListAggregator<>(arr);

        System.out.println("Среднее значение:      " + listAggregator.getAvgValue());
        System.out.println("Максимальное значение: " + listAggregator.getMaxValue());
        System.out.println("Минимальное значение:  " + listAggregator.getMinValue());
    }
}
