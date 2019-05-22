package com.bankapi.service.impl;

import com.bankapi.dao.DatabaseHandler;
import com.bankapi.model.Transaction;
import com.bankapi.model.Response;
import com.bankapi.service.TransactionService;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private DatabaseHandler handler;

    Response response = new Response();

    @Override
    public Transaction save(Transaction entity) {
        if (handler.getTRANSACTIONS().get(entity.getTransactionId()) != null) {
            response.setStatus(false);
            response.setMessage("Transaction Already Exists");
            return null;
        }

        handler.getTRANSACTIONS().put(entity.getTransactionId(), entity);
        response.setStatus(true);
        response.setMessage("Transaction created successfully");
        return entity;
    }

    @Override
    public Transaction update(Transaction entity) {
        if (handler.getTRANSACTIONS().get(entity.getTransactionId()) == null) {
            response.setStatus(false);
            response.setMessage("Transaction Does Not Exist");
            return null;
        }

        handler.getTRANSACTIONS().put(entity.getTransactionId(), entity);
        response.setStatus(true);
        response.setMessage("Transaction update successfully");
        return entity;
    }

    @Override
    public void delete(String transactionId) {
        if (handler.getTRANSACTIONS().get(transactionId) == null) {
            response.setStatus(false);
            response.setMessage("Transaction Doesn't Exists");
            return;
        }
        handler.getTRANSACTIONS().remove(transactionId);
        response.setStatus(true);
        response.setMessage("Transaction deleted successfully");
    }

    @Override
    public Transaction find(String transactionId) {
        return handler.getTRANSACTIONS().get(transactionId);
    }

    @Override
    public Transaction[] findAll() {
        Set<String> allTransactions = handler.getTRANSACTIONS().keySet();
        Transaction[] p = new Transaction[allTransactions.size()];
        int i = 0;
        for (String transactionId : allTransactions) {
            p[i] = handler.getTRANSACTIONS().get(transactionId);
            i++;
        }
        return p;
    }

}
