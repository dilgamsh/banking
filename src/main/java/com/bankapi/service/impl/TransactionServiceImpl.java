package com.bankapi.service.impl;

import com.bankapi.dao.DatabaseHandler;
import com.bankapi.model.Transaction;
import com.bankapi.service.TransactionService;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TransactionServiceImpl implements TransactionService {

    @Inject
    private DatabaseHandler handler;

    @Override
    public Transaction save(Transaction entity) {
        return handler.getTRANSACTIONS().put(entity.getTransactionId(), entity);

    }

    @Override
    public Transaction update(Transaction entity) {
        return handler.getTRANSACTIONS().put(entity.getTransactionId(), entity);

    }

    @Override
    public void delete(String transactionId) {
        handler.getTRANSACTIONS().remove(transactionId);
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
