package com.bankapi.service.impl;

import com.bankapi.dao.DatabaseHandler;
import com.bankapi.model.Account;
import com.bankapi.model.Response;
import com.bankapi.service.AccountService;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AccountServiceImpl implements AccountService {

    @Inject
    private DatabaseHandler handler;

    @Override
    public Account save(Account entity) {
        return handler.getACCOUNTS().put(entity.getAccountIdentifier(), entity);
    }

    @Override
    public Account update(Account entity) {
       return handler.getACCOUNTS().put(entity.getAccountIdentifier(), entity);
    }

    @Override
    public void delete(String accountIdentifier) {
        handler.getACCOUNTS().remove(accountIdentifier);
    }

    @Override
    public Account find(String accountIdentifier) {
        return handler.getACCOUNTS().get(accountIdentifier);
    }

    @Override
    public Account[] findAll() {
        Set<String> allAccounts = handler.getACCOUNTS().keySet();
        Account[] p = new Account[allAccounts.size()];
        int i = 0;
        for (String accountIdentifier : allAccounts) {
            p[i] = handler.getACCOUNTS().get(accountIdentifier);
            i++;
        }
        return p;
    }

}
