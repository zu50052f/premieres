package ru.vtb.study.premieres.exceptions;

public class PremiereDoesNotExistException extends RuntimeException {
    public PremiereDoesNotExistException(String premiereName) {
        super(String.format("No such premiere %s in list ", premiereName));
    }
}
