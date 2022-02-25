package ru.vtb.study.premieres.interfaces;

import ru.vtb.study.premieres.interfaces.IPremiere;
import ru.vtb.study.premieres.model.Ticket;

public interface IPremiereService {
    String toString();

    void addPremiere(IPremiere premiere);

    void printNoSuchPremiereInList(String premiereName);

    void removePremiereByName(String premiereName);

    void printPremiereInfo(String premiereName);

    void printPremieres();

    IPremiere getPremiereByName(String name);

    Ticket bookSomeSeats(String premiereName, int requiredSeats);

    Ticket returnTicket(Ticket ticket);

    void changePremiereAgeCategory(String premiereName, int newAgeCategory);
}
