package ru.vtb.study.premieres.exceptions;

public class OnBookNotEnoughSeatsException extends RuntimeException {
    public OnBookNotEnoughSeatsException(String message) {
        super(message);
    }
}
