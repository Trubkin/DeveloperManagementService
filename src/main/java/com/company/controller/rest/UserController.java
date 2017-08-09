package com.company.controller.rest;

import com.company.dao.AccountDAOImpl;
import com.company.domain.AccountDomain;
import com.company.domain.SecureAccountDomain;
import com.company.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    @Qualifier("accountDAO")
    AccountDAOImpl accountDAO;

    //@todo расписать варианты кодов ошибок
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCurrentUser() {
        //@todo реализовать, когда появится аутентификация
        return "get cur user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountDomain> getUser(@PathVariable int id) {
        Account account = accountDAO.read(id);
        return new ResponseEntity(new AccountDomain(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<AccountDomain>> getAllUsers() {
        List<AccountDomain> accountDomains = new ArrayList<>();
        for (Account account : accountDAO.readAll()) {
            accountDomains.add(new AccountDomain(account));
        }
        return new ResponseEntity(accountDomains, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody SecureAccountDomain accountDomain) {
        accountDAO.create(accountDomain);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable int id) {
        accountDAO.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
