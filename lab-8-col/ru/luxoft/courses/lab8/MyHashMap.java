package ru.luxoft.courses.lab8;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class MyHashMap<K, V> {

    private float loadFactor = 0.75f;
    private int capacity = 128;
    private int size = 0;

    @SuppressWarnings({"unchecked"})
    private EntryMyMap<K, V>[] table = new EntryMyMap[capacity];

    private int location(K key) {
        int location = key == null ? 0 : Hashing(key.hashCode());
        System.out.println("location: " + location);
        return location;
    }

    private int Hashing(int hashCode) {
        return hashCode % capacity;
    }

    private boolean isEquals(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) || Objects.equals(obj1, obj2);
    }

    private void resize(int newLength) {
        @SuppressWarnings({"unchecked"})
        EntryMyMap<K, V>[] newArray = new EntryMyMap[newLength];
        EntryMyMap<K, V>[] oldArray = table;
        table = newArray;
        capacity = newLength;
        size = 0;

        Arrays.stream(oldArray)
                .filter(Objects::nonNull)
                .forEach(e -> put(e.getKey(), e.getVal()));
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsKey(K key) {
        return findEntryByKey(key) != null;
    }

    public boolean containsValue(V value) {
        for (EntryMyMap<K, V> entry : table) {
            while (entry != null) {
                if (isEquals(entry.getVal(), value)) {
                    return true;
                }
                entry = entry.getNext();
            }
        }
        return false;
    }

    public V get(K key) {
        return Optional.ofNullable(findEntryByKey(key))
                .map(EntryMyMap::getVal)
                .orElse(null);
    }

    public V put(K key, V val) {
        if (size > loadFactor * capacity) {
            resize(capacity * 2);
        }

        int location = location(key);
        EntryMyMap<K, V> entry = table[location];
        while (entry != null) {
            if (isEquals(entry.getKey(), key)) {
                V previousValue = entry.getVal();
                entry.setVal(val);
                return previousValue;
            }
            entry = entry.getNext();
        }

        EntryMyMap<K, V> newEntry = new EntryMyMap<>(key, val);
        newEntry.setNext(table[location]);
        table[location] = newEntry;
        size++;
        return null;
    }

    public V remove(K key) {
        int location = location(key);
        EntryMyMap<K, V> previousEntry = null;
        EntryMyMap<K, V> entry = table[location];
        while (entry != null) {
            if (isEquals(entry.getKey(), key)) {
                V previousValue = entry.getVal();
                if (previousEntry == null) {
                    table[location] = entry.getNext();
                } else {
                    previousEntry.setNext(entry.getNext());
                }
                size--;
                return previousValue;
            }
            previousEntry = entry;
            entry = entry.getNext();
        }
        return null;
    }

    private EntryMyMap<K, V> findEntryByKey(K key) {
        EntryMyMap<K, V> entry = table[location(key)];
        while (entry != null) {
            if (isEquals(entry.getKey(), key)) {
                return entry;
            }
            entry = entry.getNext();
        }
        return null;
    }
}
