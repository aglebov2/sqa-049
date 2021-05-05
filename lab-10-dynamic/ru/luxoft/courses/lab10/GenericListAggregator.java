package ru.luxoft.courses.lab10;

import java.util.Comparator;

public class GenericListAggregator<T extends Number> {
    private final T[] arr;

    public GenericListAggregator(T[] arr) {
        if (arr == null) {
            throw new IllegalStateException("Массив не должен быть null");
        }
        this.arr = arr;
    }

    public double getAvgValue() {
        double sum = 0.0;
        for (T t : arr) {
            sum += t.doubleValue();
        }
        return sum / arr.length;
    }

    public double getMaxValue() {
        return getMaxBy(Comparator.naturalOrder());
    }

    public double getMinValue() {
        return getMaxBy(Comparator.reverseOrder());
    }

    private double getMaxBy(Comparator<Double> comparator) {
        double maxValue = arr[0].doubleValue();

        for (T t : arr) {
            if (comparator.compare(t.doubleValue(), maxValue) > 0) {
                maxValue = t.doubleValue();
            }
        }
        return maxValue;
    }
}
