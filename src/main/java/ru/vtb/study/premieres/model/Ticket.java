package ru.vtb.study.premieres.model;

public class Ticket {
    private String premiereName;
    private int bookedSeats;

    public Ticket(String premiereName, int bookedSeats) {
        this.premiereName = premiereName;
        this.bookedSeats = bookedSeats;
    }

    public Ticket() {
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "premiereName='" + premiereName + '\'' +
                ", bookedSeats=" + bookedSeats +
                '}';
    }

    public String getPremiereName() {
        return premiereName;
    }

    public void setPremiereName(String premiereName) {
        this.premiereName = premiereName;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}

