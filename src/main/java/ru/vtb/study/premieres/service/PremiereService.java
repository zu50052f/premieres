package ru.vtb.study.premieres.service;

import lombok.extern.slf4j.Slf4j;
import ru.vtb.study.premieres.interfaces.IPremiere;
import ru.vtb.study.premieres.interfaces.IPremiereService;
import ru.vtb.study.premieres.model.Ticket;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PremiereService implements IPremiereService {
    private final Map<String, IPremiere> premiereList = new HashMap<>();

    public void addPremiere(IPremiere premiere) {
        premiereList.put(premiere.getName(), premiere);
    }

    public void printNoSuchPremiereInList(String premiereName) {
        log.error("No such premiere {} in list", premiereName);
    }

    public void removePremiereByName(String premiereName) {
        IPremiere premiere = getPremiereByName(premiereName);
        if (premiere != null) {
            premiereList.remove(premiereName);
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
        log.info(premiereList.values().toString());
    }

    public IPremiere getPremiereByName(String name) {
        return premiereList.get(name);
    }

    public Ticket bookSomeSeats(String premiereName, int requiredSeats) {
        IPremiere premiere = getPremiereByName(premiereName);
        Ticket ticket = null;
        if (premiere == null) {
            printNoSuchPremiereInList(premiereName);
        } else {
            int availableSeats = premiere.getAvailableSeats();

            if (availableSeats >= requiredSeats) {
                premiere.setAvailableSeats(availableSeats - requiredSeats);
                log.info("Successful book of {} seats on premiere {}", requiredSeats, premiereName);
                ticket = new Ticket(premiereName, requiredSeats);
            } else {
                log.error("Sorry, there is just {} seats left", availableSeats);
            }
        }
        return ticket;
    }

    public Ticket returnTicket(Ticket ticket) {
        if (ticket == null) {
            log.error("You have no ticket!");
        } else if (ticket.getBookedSeats() <= 0) {
            log.error("Your ticket has no booked seats!");
        } else {
            IPremiere premiere = getPremiereByName(ticket.getPremiereName());
            if (premiere == null) {
                log.error("Oops! You can't return ticket on removed premiere \"{}\"", ticket.getPremiereName());
            } else {
                int bookedSeats = ticket.getBookedSeats();
                premiere.setAvailableSeats(premiere.getAvailableSeats() + bookedSeats);
                ticket.setBookedSeats(0);
                log.info("Successfull return of tiket on premier {} with {} booked seats.", ticket.getPremiereName(), bookedSeats);
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
            log.info("New age category {}+ for premiere {} has been set!", newAgeCategory, premiereName);
        }
    }
}
