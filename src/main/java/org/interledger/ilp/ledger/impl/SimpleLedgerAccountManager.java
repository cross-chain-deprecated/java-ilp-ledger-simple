package org.interledger.ilp.ledger.impl;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;
import org.interledger.ilp.core.InterledgerAddress;
import org.interledger.ilp.core.ledger.model.LedgerInfo;

import org.interledger.ilp.ledger.account.AccountNotFoundException;
import org.interledger.ilp.ledger.account.LedgerAccount;
import org.interledger.ilp.ledger.account.LedgerAccountManager;

/**
 * Simple in-memory {@code LedgerAccountManager}.
 *
 * @author mrmx
 */
public class SimpleLedgerAccountManager implements LedgerAccountManager {

    private LedgerInfo ledgerInfo;
    private Map<InterledgerAddress, LedgerAccount> accountMap;

    public SimpleLedgerAccountManager(LedgerInfo ledgerInfo) {
        this.ledgerInfo = ledgerInfo;
        accountMap = new HashMap<InterledgerAddress, LedgerAccount>();
    }

    @Override
    public LedgerInfo getLedgerInfo() {
        return ledgerInfo;
    }

    @Override
    public LedgerAccount create(String name) {
        return new SimpleLedgerAccount(name, ledgerInfo.getCurrencyUnit());
    }

    public void addAccounts(SimpleLedgerAccount... accounts) {
        for (SimpleLedgerAccount account : accounts) {
            addAccount(account);
        }
    }

    @Override
    public void addAccount(LedgerAccount account) {
        accountMap.put(account.getInterledgerAddress(), account);
    }

    @Override
    public LedgerAccount getAccountByILPAddress(InterledgerAddress name) throws AccountNotFoundException {
        if(!accountMap.containsKey(name)) {
            throw new AccountNotFoundException(name.toString());
        }
        return accountMap.get(name);
    }

    @Override
    public Collection<LedgerAccount> getAccounts(int page, int pageSize) {
        //TODO
        return accountMap.values();
    }
    
    

    @Override
    public int getTotalAccounts() {
        return accountMap.size();
    }

}
