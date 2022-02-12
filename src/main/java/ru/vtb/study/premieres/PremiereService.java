package ru.vtb.study.premieres;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vtb.study.premieres.model.Premiere;
import ru.vtb.study.premieres.model.Ticket;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PremiereService {
    private final Map<String, Premiere> premiereList = new HashMap<>();

    public void addPremiere(String name, String description, int ageCategory, int availableSeats) {
        Premiere premiere = new Premiere(name, description, ageCategory, availableSeats);
        premiereList.put(name, premiere);
    }

    public void printNoSuchPremiereInList(String premiereName) {
        log.error("No such premiere {} in list", premiereName);
    }

    public void removePremiereByName(String premiereName) {
        Premiere premiere = getPremiereByName(premiereName);
        if (premiere != null) {
            premiereList.remove(premiereName);
            log.info("Premiere {} has been removed from list", premiereName);
        } else {
            printNoSuchPremiereInList(premiereName);
        }
    }

    public void printPremiereInfo(String premiereName) {
        Premiere premiere = getPremiereByName(premiereName);
        if (premiere == null) {
            printNoSuchPremiereInList(premiereName);
        } else {
            log.info(premiere.toString());
        }
    }

    public void printPremieres() {
        log.info(premiereList.values().toString());
    }

    public Premiere getPremiereByName(String name) {
        return premiereList.get(name);
    }

    public Ticket bookSomeSeats(String premiereName, int requiredSeats) {
        Premiere premiere = getPremiereByName(premiereName);
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
            Premiere premiere = getPremiereByName(ticket.getPremiereName());
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
        Premiere premiere = getPremiereByName(premiereName);
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
