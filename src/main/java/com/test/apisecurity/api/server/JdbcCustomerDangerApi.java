package com.test.apisecurity.api.server;

import com.test.apisecurity.api.request.sqlinjection.JdbcCustomerPatchRequest;
import com.test.apisecurity.entity.JdbcCustomer;
import com.test.apisecurity.repository.JdbcCustomerDangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sqlinjection/danger/v1")
public class JdbcCustomerDangerApi {

    @Autowired
    private JdbcCustomerDangerRepository repository;

    //get request to find customer by email with
    // email input parameter on path variable
    @GetMapping(value = "/customer/{email}")
    public JdbcCustomer findCustomerByEmail(@PathVariable(required = true, name = "email") String email) {
        return repository.findCustomerByEmail(email);
    }

    //endpoint to find customer list by gender
    //get request with gender code on request query string
    @GetMapping(value = "/customer")
    public List<JdbcCustomer> findCustomerByGender(@RequestParam(required = true, name = "genderCode") String genderCode) {
        return repository.findCustomerByGender(genderCode);
    }

    //endpoint to create customer(post request with customer data as request body)
    @PostMapping(value = "/customer")
    public void createCustomer(@RequestBody(required = true) JdbcCustomer newCustomer) {
        repository.createCustomer(newCustomer);
    }
    //endpoint to update customer name which calls stored procedure
    //patch request with new data as request body
    @PatchMapping(value = "/customer/{customerId")
    public void updateCustomerFullName(@PathVariable(required = true, name ="customerId") int customerId,
                                       @RequestBody(required = true) JdbcCustomerPatchRequest request) {
        repository.updateCustomerFullName(customerId, request.getNewFullName());
    }
    //class that represents request body for update customer full name

}
