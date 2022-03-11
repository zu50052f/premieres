package ru.vtb.study.premieres.exceptions;

public class OnReturnNoBookedSeatsException extends RuntimeException {
    public OnReturnNoBookedSeatsException(String message) {
        super(message);
    }
}
