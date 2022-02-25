package ru.vtb.study.premieres;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        premiereService.printPremieres();
        premiereService.printPremiereInfo(Matrix);

        Ticket matrix40 = premiereService.bookSomeSeats(Matrix, 40);
        log.info(matrix40.toString());
        Ticket matrix20 = premiereService.bookSomeSeats(Matrix, 20);
        premiereService.returnTicket(matrix40);
        matrix20 = premiereService.bookSomeSeats(Matrix, 20);
        log.info(matrix20.toString());
        premiereService.returnTicket(matrix40);
        Ticket natjb1000 = premiereService.bookSomeSeats("NatJB", 1000);
        premiereService.returnTicket(natjb1000);

        Ticket jb10 = premiereService.bookSomeSeats(JB007, 10);
        premiereService.removePremiereByName(JB007);
        premiereService.removePremiereByName(JB007);
        premiereService.returnTicket(jb10);
        premiereService.printPremieres();

        premiereService.changePremiereAgeCategory(Matrix, 18);
        premiereService.printPremiereInfo(Matrix);

        premiereService.changePremiereAgeCategory(Matrix, -1);
        premiereService.printPremiereInfo(Matrix);
    }

}
