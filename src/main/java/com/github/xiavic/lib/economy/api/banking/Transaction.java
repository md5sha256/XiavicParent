package com.github.xiavic.lib.economy.api.banking;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a transaction between two {@link CreditHolder}s.
 */
public class Transaction {

    public final UUID invoker;

    private final CreditHolder invokingCreditHolder, targetCreditHolder;

    private double sum;

    public Transaction(UUID invoker, CreditHolder invokingCreditHolder,
        CreditHolder targetCreditHolder) {
        this.invoker = Objects.requireNonNull(invoker);
        this.invokingCreditHolder = Objects.requireNonNull(invokingCreditHolder);
        this.targetCreditHolder = Objects.requireNonNull(targetCreditHolder);
    }

    public Transaction setSum(double sum) {
        this.sum = sum;
        return this;
    }

    public UUID getInvoker() {
        return invoker;
    }

    public CreditHolder getTargetCreditHolder() {
        return targetCreditHolder;
    }

    public CreditHolder getInvokingCreditHolder() {
        return invokingCreditHolder;
    }

    public double getSum() {
        return sum;
    }

    /**
     * Executes this transaction by the order of,
     * invoker first, receiver next.
     *
     * @return Returns whether the transaction was successfully processed.
     * @throws IllegalStateException Thrown if an account was frozen whilst a transaction was in progress.
     */
    public boolean execute() throws IllegalStateException {
        if (!invokingCreditHolder.getBackingExecutor().handleTransaction(this)) {
            return false;
        }
        if (!targetCreditHolder.getBackingExecutor().handleTransaction(this)) {
            invokingCreditHolder.getBackingExecutor().callBack(this);
            return false;
        }
        return true;
    }

    /*
     * Execute and create an EconomyResponse.
     *
     * @return Returns an economy response representing the state of the transaction.
     /*
    public EconomyResponse executeVault() {
        boolean success = execute(); //Try to execute the transaction.
        return new EconomyResponse(sum, invokingCreditHolder.getCurrentBalance(), success ? EconomyResponse.ResponseType.SUCCESS : EconomyResponse.ResponseType.FAILURE,
                "&c&l (!) Transaction Failed."); //TODO Make config toggleable.
    }
    */
}
