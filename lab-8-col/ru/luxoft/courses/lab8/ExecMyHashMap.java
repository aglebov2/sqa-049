package ru.luxoft.courses.lab8;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecMyHashMap {
    public static void main(String[] args) {

        MyHashMap <String, Integer> map = new MyHashMap<>();
        System.out.println("This is The program ExecMyHashMap");

        map.put("Ball", 2);
        map.put("Cube", 3);
        map.put("Dome", 4);
        map.put("Ball", 3);
        System.out.println("Dome: " + map.get("Dome"));
        System.out.println("Ball: " + map.get("Ball"));
        System.out.println("1_size: " + map.size());

        System.out.println("contains Cube: " + map.containsKey("Cube"));
        System.out.println("2_size: " + map.size());

        System.out.println("isEmpty: " + map.isEmpty());
        System.out.println("contains 1: " + map.containsValue(1));

        System.out.println("Удалить Cube: " + map.remove("Cube"));
        System.out.println("contains Cube: " + map.containsKey("Cube"));
        System.out.println("Dome: " + map.get("Dome"));
        System.out.println("Dome == Dome: " + map.get("Dome").equals(map.get("Dome")));

        System.out.println("0:1:200");
        MyHashMap <Integer, Integer> mapInt = new MyHashMap<>();
        for (int j = 0; j <202; j++){
            mapInt.put(j, j);
        }
        System.out.println("3_size: " + mapInt.size());
        for (int a = 0; a < 202; a++){
            System.out.println("value for key " + a + ": " + mapInt.get(a));
        }
        System.out.println("4_size: " + mapInt.size());

        System.out.println("0:16:200");
        mapInt = new MyHashMap<>();
        for (int j = 0; j <202; j += 16){
            mapInt.put(j, j);
        }
        System.out.println("5_size: " + mapInt.size());
        for (int a = 0; a < 202; a += 16){
            System.out.println("value for key " + a + ": " + mapInt.get(a));
        }
        System.out.println("6_size: " + mapInt.size());
    }
}
