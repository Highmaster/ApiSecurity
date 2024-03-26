package com.test.apisecurity.repository;

import com.test.apisecurity.entity.JdbcCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCustomerDangerRepository {
    //this class wil help us maintain connection
    // to database as well as execute SQL statements
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //method to find customer by email
    public JdbcCustomer findCustomerByEmail(String email) {
        var sql = "SELECT customer_id, full_name, email, birth_date, gender FROM jdbc_customer "
                + "WHERE email = '"+email+ "' AND email is not null";

        //the mapToCustomer method is required to map query result into Java Object
        return jdbcTemplate.queryForObject(sql, this::mapToCustomer);
    }

    //creating new jdbcCustomer object and setting fields by result from JDBC ResultSet.
    private JdbcCustomer mapToCustomer(ResultSet rs, long rowNum) throws SQLException {
        var customer = new JdbcCustomer();

        Optional.ofNullable(rs.getDate("birth_date")).ifPresent(b -> customer.setBirthdate(b.toLocalDate()));
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setEmail(rs.getString("email"));
        customer.setFullName(rs.getString("full_name"));
        customer.setGender(rs.getString("gender"));

        return customer;
    }

    public List<JdbcCustomer> findCustomerByGender(String genderCode) {
        var sql = "SELECT customer_id, full_nmae, email, birth_date, gender FROM jdbc_customer"
                + "WHERE gender = '"+genderCode+"' ";

        return jdbcTemplate.query(sql, this::mapToCustomer);
    }

    //vulnerable method to create new customer
    public void createCustomer(JdbcCustomer newCustomer) {
        var sql = "INSERT INTO jdbc_customer(full_name, email, birth_date, gender) VALUES ('"
                + newCustomer.getFullName() + "','" + newCustomer.getEmail()+"','" + newCustomer.getBirthdate()+"','"
                + newCustomer.getGender() + "')";

        jdbcTemplate.execute(sql);
    }
    public void updateCustomerFullName(int customerId, String newFullName) {
        var sql= "CALL update_jdbc_customer(" + customerId + ", '" + newFullName + "')";

        jdbcTemplate.execute(sql);
    }
}
