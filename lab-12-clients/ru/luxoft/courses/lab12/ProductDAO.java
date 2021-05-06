package ru.luxoft.courses.lab12;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    @SuppressWarnings("all")
    private Connection getConnection() {
        try {
            // Подгрузка драйвера БД Derby

            // Class.forName(...)
            // Can be converted to JVM parameter, like -
            // java -Djdbc.drivers=org.postgresql.Driver your.package.ClassWithMainMethod

            Class.forName("org.postgresql.Driver");
            var url = "jdbc:postgresql://localhost:5432/postgres";
            var user = "postgres";
            var password = "postgres";
            // Получение соединения с БД
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new CanNotObtainConnection("Can't obtain connection");
        }

    }

    public List<Product> getProductById(int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        // Получение соединения с БД
        try (var con = getConnection();
             // Подготовка SQL-запроса
             var st = con.prepareStatement(
                     "Select description, rate, quantity " +
                             "From products " +
                             "Where id = ?")
        ) {
            // Указание значений параметров запроса
            st.setInt(1, id);

            // Выполнение запроса
            ResultSet rs = st.executeQuery();

            // Перечисляем результаты выборки
            while (rs.next()) {
                // Из каждой строки выборки выбираем результаты,
                // формируем новый объект Product
                // и помещаем его в коллекцию
                Product product = new Product(
                        id,
                        rs.getString(1),
                        rs.getFloat(2),
                        rs.getInt(3));
                products.add(product);
            }
            // Закрываем выборку и соединение с БД
            rs.close();
        }
        return products;
    }

    public void addProduct(Product product) throws SQLException {
        System.out.println("Begin: addProduct");
        // Получение соединения с БД
        try (var con = getConnection();
             // Подготовка SQL-запроса
             var st = con.prepareStatement(
                     "Insert into products " +
                             "(id, description, rate, quantity) " +
                             "values (?, ?, ?, ?)")
        ) {
            // Указание значений параметров запроса
            st.setInt(1, product.id());
            st.setString(2, product.description());
            st.setFloat(3, product.rate());
            st.setInt(4, product.quantity());

            // Выполнение запроса
            System.out.println("Begin: SQL query");
            st.executeUpdate();
        }
        System.out.println("Closing an SQL query");
    }

    public void setProduct(Product product) throws SQLException {
        // Получение соединения с БД
        try (var con = getConnection();
             // Подготовка SQL-запроса
             var st = con.prepareStatement(
                     "Update products " +
                             "Set description=?, rate=?, quantity=? " +
                             "Where id=?")
        ) {
            // Указание значений параметров запроса
            st.setString(1, product.description());
            st.setFloat(2, product.rate());
            st.setInt(3, product.quantity());
            st.setInt(4, product.id());

            // Выполнение запроса
            st.executeUpdate();
        }
    }

    public void removeProduct(int id) throws SQLException {
        // Получение соединения с БД
        try (var con = getConnection();
             // Подготовка SQL-запроса
             var st = con.prepareStatement(
                     "Delete from products " +
                             "Where id = ?")
        ) {
            // Указание значений параметров запроса
            st.setInt(1, id);

            // Выполнение запроса
            st.executeUpdate();
        }
    }

    private class CanNotObtainConnection extends RuntimeException {
        public CanNotObtainConnection(String message) {
            super(message);
        }
    }
}