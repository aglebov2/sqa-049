package ru.luxoft.courses.lab8;

public class EntryMyMap<K, V> {
    private K key;
    private V val;
    private EntryMyMap<K, V> next;

    public EntryMyMap(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public EntryMyMap<K, V> getNext() {
        return next;
    }

    public void setNext(EntryMyMap<K, V> next) {
        this.next = next;
    }
}
