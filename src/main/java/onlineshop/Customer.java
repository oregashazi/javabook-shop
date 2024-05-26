package onlineshop;

import onlineshop.enums.Gender;
import onlineshop.merchandise.Cart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Customer {
    /**
     * erzeugt für jeden Kunden eine neue Kundennummer
     */
    private static Integer customerCounter = 1;
    /**
     * wandelt den Date-String in ein {@link Date} um
     */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");

    protected int customerNo;
    protected String firstname;
    protected String surname;
    protected Gender gender;
    protected LocalDate birthDate;
    protected Cart cart;

    public Customer() {
        this.customerNo = customerCounter++;
    }

    public Customer(String firstname, String surname, Gender gender, String birthDate, Cart cart) {
        this();
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.cart = cart;
    }

}
