package se.lexicon.g40_jpa_booking.model.dto.form;

import org.springframework.validation.annotation.Validated;
import se.lexicon.g40_jpa_booking.validation.OnPost;
import se.lexicon.g40_jpa_booking.validation.OnPut;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static se.lexicon.g40_jpa_booking.validation.messages.ValidationMessages.MANDATORY_FIELD;
import static se.lexicon.g40_jpa_booking.validation.messages.ValidationMessages.MANDATORY_FORM;

@Validated
public class PremisesForm {

    @NotBlank(message = MANDATORY_FIELD, groups = OnPut.class)
    private String id;
    @NotBlank(message = MANDATORY_FIELD, groups = {OnPut.class, OnPost.class})
    private String name;
    @NotNull(message = MANDATORY_FORM, groups = OnPost.class)
    @Valid private AddressForm address;

    public PremisesForm() {
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

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address;
    }
}
