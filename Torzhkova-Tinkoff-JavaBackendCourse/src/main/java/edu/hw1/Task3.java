package edu.hw1;

public class Task3 {

    public boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            throw new IllegalArgumentException("Входные массивы не могут быть null");
        }
        MinMaxArray minMax1 = findMinAndMAxInArray(a1);
        MinMaxArray minMax2 = findMinAndMAxInArray(a2);
        return minMax1.min > minMax2.min && minMax1.max < minMax2.max;
    }

    private MinMaxArray findMinAndMAxInArray(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }
        return new MinMaxArray(min, max);
    }

    record MinMaxArray(int min, int max) { }
}
