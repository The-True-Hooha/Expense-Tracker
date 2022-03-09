package com.TheTrueHooha.ExpenseTracker.Exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(String message) {
        super(message);
    }

}
