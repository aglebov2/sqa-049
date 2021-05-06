package ru.luxoft.courses.lab12;

public record Product(int id, String description, float rate, int quantity) {

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", quantity=" + quantity +
                '}';
    }
}
