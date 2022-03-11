package ru.vtb.study.premieres.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import ru.vtb.study.premieres.exceptions.OnReturnNoTicketException;
import ru.vtb.study.premieres.interfaces.IPremiere;
import ru.vtb.study.premieres.model.Ticket;

@Aspect
@Slf4j
public class EmailNotifier {
    @Pointcut("execution(* ru.vtb.study.premieres.interfaces.IPremiereService.addPremiere(..))")
    public void addPremierePointcut(){}

    @After("addPremierePointcut()")
    public void afterAddPremierePointcut(JoinPoint joinPoint) {
        IPremiere premiere = (IPremiere) joinPoint.getArgs()[0];
        log.warn("Behold! New premiere \"{}\" that tells a story \"{}\". Hurry up! Just {} seats available! Note! Only for childrens older than {}!", premiere.getName(), premiere.getDescription(), premiere.getAvailableSeats(), premiere.getAgeCategory());
    }



    @Pointcut("execution(* ru.vtb.study.premieres.interfaces.IPremiereService.returnTicket(..))")
    public void returnTicketPointcut(){}

    @AfterReturning("returnTicketPointcut()")
    public void afterReturnTicketPointcut(JoinPoint joinPoint) {
        Ticket ticket = (Ticket) joinPoint.getArgs()[0];
        log.info("Successful return of ticket on premier {}", ticket.getPremiereName());
    }

    @AfterThrowing(pointcut = "returnTicketPointcut()", throwing = "ex")
    public void afterThrowingReturnTicketPointcut(JoinPoint joinPoint, Throwable ex){
        if (ex instanceof OnReturnNoTicketException) {
            log.error("Hey! You don't have a ticket!");
        } else {
            Ticket ticket = (Ticket) joinPoint.getArgs()[0];
            log.error("We can't return your funds! Ticket on premiere {} with {} booked seats cause \"{}\"", ticket.getPremiereName(), ticket.getBookedSeats(), ex.getMessage());
        }
    }


    @Pointcut("execution(* ru.vtb.study.premieres.interfaces.IPremiereService.bookSomeSeats(..))")
    public void bookSomeSeatsPointcut(){}

    @AfterReturning("bookSomeSeatsPointcut()")
    public void afterBookPointcut(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String premiereName = (String) args[0];
        int requiredSeats = (int) args[1];
        log.info("Successful book of {} seats on premiere {}", requiredSeats, premiereName);
    }


    @Pointcut("execution(* ru.vtb.study.premieres.interfaces.IPremiere.setAgeCategory(..))")
    public void setAgeCategoryPointcut(){}

    @AfterReturning("setAgeCategoryPointcut()")
    public void afterSetAgeCategory(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        int newAgeCategory = (int) args[0];
        log.info("New age category {}+ has been set!", newAgeCategory);
    }
}
