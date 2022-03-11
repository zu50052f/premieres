package ru.vtb.study.premieres.service;

import lombok.extern.slf4j.Slf4j;
import ru.vtb.study.premieres.exceptions.*;
import ru.vtb.study.premieres.interfaces.IPremiere;
import ru.vtb.study.premieres.interfaces.IPremiereService;
import ru.vtb.study.premieres.model.Ticket;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PremiereService implements IPremiereService {
    private final Map<String, IPremiere> premiereMap = new HashMap<>();

    public void addPremiere(IPremiere premiere) {
        premiereMap.put(premiere.getName(), premiere);
    }

    public void printNoSuchPremiereInList(String premiereName) {
        log.error("No such premiere {} in list", premiereName);
    }

    public void removePremiereByName(String premiereName) {
        IPremiere premiere = getPremiereByName(premiereName);
        if (premiere != null) {
            premiereMap.remove(premiereName);
            log.info("Premiere {} has been removed from list", premiereName);
        } else {
            printNoSuchPremiereInList(premiereName);
        }
    }

    public void printPremiereInfo(String premiereName) {
        IPremiere premiere = getPremiereByName(premiereName);
        if (premiere == null) {
            printNoSuchPremiereInList(premiereName);
        } else {
            log.info(premiere.toString());
        }
    }

    public void printPremieres() {
        log.info(premiereMap.values().toString());
    }

    public IPremiere getPremiereByName(String name) {
        return premiereMap.get(name);
    }

    public Ticket bookSomeSeats(String premiereName, int requiredSeats) {
        IPremiere premiere = getPremiereByName(premiereName);
        Ticket ticket = null;
        if (premiere == null) {
            throw new PremiereDoesNotExistException(premiereName);
        } else {
            int availableSeats = premiere.getAvailableSeats();

            if (availableSeats >= requiredSeats) {
                premiere.setAvailableSeats(availableSeats - requiredSeats);
                ticket = new Ticket(premiereName, requiredSeats);
            } else {
                throw new OnBookNotEnoughSeatsException(String.format("Sorry, there is just %d seats left", availableSeats));
            }
        }
        return ticket;
    }

    public Ticket returnTicket(Ticket ticket) {
        if (ticket == null) {
            throw new OnReturnNoTicketException("You don't have the ticket to return!");
        } else if (ticket.getBookedSeats() <= 0) {
            throw new OnReturnNoBookedSeatsException("Your ticket has no booked seats!");
        } else {
            IPremiere premiere = getPremiereByName(ticket.getPremiereName());
            if (premiere == null) {
                throw new OnReturnNoSuchPremiereException(String.format("Oops! You can't return ticket on removed premiere \"%s\"", ticket.getPremiereName()));
            } else {
                int bookedSeats = ticket.getBookedSeats();
                premiere.setAvailableSeats(premiere.getAvailableSeats() + bookedSeats);
                ticket.setBookedSeats(0);
            }
        }
        return ticket;
    }

    public void changePremiereAgeCategory(String premiereName, int newAgeCategory) {
        IPremiere premiere = getPremiereByName(premiereName);
        if (premiere == null) {
            printNoSuchPremiereInList(premiereName);
        } else if (newAgeCategory < 0) {
            log.error("Illegal age! {}", newAgeCategory);
        } else {
            premiere.setAgeCategory(newAgeCategory);
        }
    }
}
