package com.angularecommerce.Controller;

public class InvalidException extends Throwable {
    private final String invalid;

    public InvalidException(String invalid) {
        this.invalid= invalid;
    }
}
