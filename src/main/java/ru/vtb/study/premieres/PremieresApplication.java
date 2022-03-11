package ru.vtb.study.premieres;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.vtb.study.premieres.interfaces.IPremiere;
import ru.vtb.study.premieres.interfaces.IPremiereService;
import ru.vtb.study.premieres.model.Ticket;

@SpringBootApplication(scanBasePackages = {"ru.vtb.study.premieres"})
@Slf4j
public class PremieresApplication {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PremieresConfig.class);
        IPremiereService premiereService = applicationContext.getBean(IPremiereService.class);

        final String JB007 = "JB007";
        final String Matrix = "Matrix";

        premiereService.addPremiere((IPremiere) applicationContext.getBean("premiere", JB007, "He is arriving again to save the Queen", 10, 100));
        premiereService.addPremiere((IPremiere) applicationContext.getBean("premiere", Matrix, "Neo has come back", 15, 50));
        //premiereService.printPremieres();
        //premiereService.printPremiereInfo(Matrix);

        Ticket matrix40 = bookSomeSeatsSilent(premiereService, Matrix, 40);
        //log.info(matrix40.toString());
        Ticket matrix20 = bookSomeSeatsSilent(premiereService, Matrix, 20);
        returnTicketSilent(premiereService, matrix40);
        matrix20 = bookSomeSeatsSilent(premiereService, Matrix, 20);
        //log.info(matrix20.toString());
        returnTicketSilent(premiereService, matrix40);
        Ticket natjb1000 = bookSomeSeatsSilent(premiereService, "NatJB", 1000);
        returnTicketSilent(premiereService, natjb1000);

        Ticket jb10 = bookSomeSeatsSilent(premiereService, JB007, 10);
        premiereService.removePremiereByName(JB007);
        premiereService.removePremiereByName(JB007);
        returnTicketSilent(premiereService, jb10);
        premiereService.printPremieres();

        premiereService.changePremiereAgeCategory(Matrix, 18);
        premiereService.printPremiereInfo(Matrix);

        premiereService.changePremiereAgeCategory(Matrix, -1);
        premiereService.printPremiereInfo(Matrix);
    }

    private static void returnTicketSilent(IPremiereService premiereService, Ticket ticket) {
        try {
            premiereService.returnTicket(ticket);
        } catch (Exception ignored) {}
    }

    private static Ticket bookSomeSeatsSilent(IPremiereService premiereService, String premiereName, int requiredSeats) {
        Ticket ticket = null;
        try {
            ticket = premiereService.bookSomeSeats(premiereName, requiredSeats);
        } catch (Exception ignored) {}
        return ticket;
    }
}
