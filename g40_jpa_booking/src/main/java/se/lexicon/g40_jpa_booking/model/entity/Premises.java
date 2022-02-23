package se.lexicon.g40_jpa_booking.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static se.lexicon.g40_jpa_booking.model.constants.EntityConstants.GENERATOR;
import static se.lexicon.g40_jpa_booking.model.constants.EntityConstants.UUID_GENERATOR;

@Entity
public class Premises {

    @Id
    @GeneratedValue(generator = GENERATOR)
    @GenericGenerator(name = GENERATOR, strategy = UUID_GENERATOR)
    @Column(updatable = false)
    private String id;

    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "premises"
    )
    private List<Booking> bookings;

    public Premises(String id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public Premises() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Booking> getBookings() {
        if(this.bookings == null) this.bookings = new ArrayList<>();
        return bookings;
    }

    // Handle Setter in a bidirectional way.
    // Make sure to set relationships of both ends
    // Empty we need to remove relationships
    // Or set the relationships.
    //TODO Test what happens with bookings that is made but was not included in the param list? - Removed?
    public void setBookings(List<Booking> bookings) {
        if (bookings == null) bookings = new ArrayList<>();

        if (bookings.isEmpty()){
            if (this.bookings != null){
                this.bookings.forEach(booking -> booking.setPremises(null));
            }
        }else{
            bookings.forEach(booking -> booking.setPremises(this));
        }
        this.bookings = bookings;
    }


    //Convenience Method
    public void addBooking(Booking booking){
        if (booking == null) throw new IllegalArgumentException("Booking was null");
        if (this.bookings == null) this.bookings = new ArrayList<>();

        if (!this.bookings.contains(booking)){
            this.bookings.add(booking);
            booking.setPremises(this);
        }
    }

    //Convenience Method
    public void removeBooking(Booking booking){
        if (booking == null) throw new IllegalArgumentException("Booking was null");
        if (this.bookings == null) this.bookings = new ArrayList<>();

        if (this.bookings.contains(booking)){
            this.bookings.remove(booking);
            booking.setPremises(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Premises premises = (Premises) o;
        return Objects.equals(id, premises.id) && Objects.equals(name, premises.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Premises{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
