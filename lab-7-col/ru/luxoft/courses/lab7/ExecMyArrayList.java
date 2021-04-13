package ru.luxoft.courses.lab7;


public class ExecMyArrayList {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        System.out.println("The program MyArrayList is running");
        System.out.println("Size of ArrayList is = " + list.size());

        System.out.println("Adding 8 records");
        list.add("first element");
        list.add("second element");
        list.add("4 element");
        list.add("5 element");
        list.add("6 element");
        list.add("7 element");
        list.add("8 element");
        list.add("9 element");
        System.out.println("Size after added 8 records and resize is = " + list.size());

        System.out.println("Adding 9 records");
        list.add("10 element");
        list.add("11 element");
        list.add("12 element");
        list.add("13 element");
        list.add("14 element");
        list.add("15 element");
        list.add("16 element");
        list.add("17 element");
        list.add("18 element");
        System.out.println("Size after added 9 records and resize is = " + list.size());

        System.out.println("Removing 8 records");
        list.remove(3);
        list.remove(0);
        list.remove(3);
        list.remove(3);
        list.remove(3);
        list.remove(3);
        list.remove(3);
        list.remove(3);
        System.out.println("Size after removed 8 records and resize is = " + list.size());

        System.out.println("Record 1 is " + list.get(1));
        System.out.println("Record 0 is " + list.get(0));
        System.out.println("Size of ArrayList is = " + list.size());
    }
}
