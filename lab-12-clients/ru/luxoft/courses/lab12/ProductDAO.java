package ru.luxoft.courses.lab12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection getConnection() throws Exception {
        // Подгрузка драйвера БД Derby
        Class.forName("org.postgresql.Driver").newInstance();
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user ="postgres";
        String password = "postgres";
        // Получение соединения с БД
        return DriverManager.getConnection(url, user, password);
    }

    public List<Product> getProductById(int id) throws Exception {
        List<Product> products = new ArrayList<Product>();
        // Получение соединения с БД
        Connection con = getConnection();

        // Подготовка SQL-запроса
        PreparedStatement st = con.prepareStatement(
                "Select description, rate, quantity " +
                        "From products " +
                        "Where id = ?");
        // Указание значений параметров запроса
        st.setInt(1, id);

        // Выполнение запроса
        ResultSet rs = st.executeQuery();

        Product product = null;
        // Перечисляем результаты выборки
        while (rs.next()) {
            // Из каждой строки выборки выбираем результаты,
            // формируем новый объект Product
            // и помещаем его в коллекцию
            product = new Product(
                    id,
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getInt(3));
            products.add(product);
        }
        // Закрываем выборку и соединение с БД
        rs.close();
        con.close();
        return products;

    }

    public void addProduct(Product product) throws Exception {
        System.out.println("Begin: addProduct");
        // Получение соединения с БД
        Connection con = getConnection();

        // Подготовка SQL-запроса
        PreparedStatement st = con.prepareStatement(
                "Insert into products " +
                        "(id, description, rate, quantity) " +
                        "values (?, ?, ?, ?)");
        // Указание значений параметров запроса
        st.setInt(1, product.getId());
        st.setString(2, product.getDescription());
        st.setFloat(3, product.getRate());
        st.setInt(4, product.getQuantity());

        // Выполнение запроса
        System.out.println("Begin: SQL query");
        st.executeUpdate();

        con.close();
        System.out.println("Closing an SQL query");
           }

    public void setProduct(Product product) throws Exception {
        // Получение соединения с БД
        Connection con = getConnection();

        // Подготовка SQL-запроса
        PreparedStatement st = con.prepareStatement(
                "Update products " +
                        "Set description=?, rate=?, quantity=? " +
                        "Where id=?");
        // Указание значений параметров запроса
        st.setString(1, product.getDescription());
        st.setFloat(2, product.getRate());
        st.setInt(3, product.getQuantity());
        st.setInt(4, product.getId());

        // Выполнение запроса
        st.executeUpdate();
        con.close();
    }

    public void removeProduct(int id) throws Exception {
        // Получение соединения с БД
        Connection con = getConnection();

        // Подготовка SQL-запроса
        PreparedStatement st = con.prepareStatement(
                "Delete from products " +
                        "Where id = ?");
        // Указание значений параметров запроса
        st.setInt(1, id);

        // Выполнение запроса
        st.executeUpdate();
        con.close();
    }
}