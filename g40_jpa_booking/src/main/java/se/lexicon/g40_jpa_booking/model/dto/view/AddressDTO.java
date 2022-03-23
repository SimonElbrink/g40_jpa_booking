package se.lexicon.g40_jpa_booking.model.dto.view;

public class AddressDTO {

    private String id;
    private String streetAddress;
    private String zipcode;
    private String city;

    public AddressDTO(String id, String streetAddress, String zipcode, String city) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.zipcode = zipcode;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }
}
