package onlineshop.merchandise;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private static long nextId = 1;
    private Long id;
    private String firstname;
    private String lastname;
    private String companyname;
    private String address1;
    private String address2;
    private String city;
    private String zip;
    private String phone;
    private String email;
    private String notes;
    private String paymentMethod;
    private List<CartItem> cartItems;
    private LocalDateTime creationDate;

    public Order() {
        this.id = nextId++;
        this.creationDate = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<CartItem> getCartItemsOrder() {
        System.out.println("getCartitems " + cartItems.size());
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        System.out.println("setCartItems " + cartItems.size() );
        this.cartItems = cartItems;
    }
}
