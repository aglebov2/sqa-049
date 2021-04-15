package ru.luxoft.courses.lab10;

public class Main {

    public static void main(String[] args) {
        Integer arr [] = new Integer[] {2, 3, 1, 32, 31};
        System.out.println("Программа Lab10: Пример обобщений ");

        GenericListAgregator<Integer> listAgregator = new GenericListAgregator<>(arr);

        System.out.println("Среднее значение:      " + listAgregator.getAvgValue());
        System.out.println("Максимальное значение: " + listAgregator.getMaxValue());
        System.out.println("Минимальное значение:  " + listAgregator.getMinValue());
    }
}
