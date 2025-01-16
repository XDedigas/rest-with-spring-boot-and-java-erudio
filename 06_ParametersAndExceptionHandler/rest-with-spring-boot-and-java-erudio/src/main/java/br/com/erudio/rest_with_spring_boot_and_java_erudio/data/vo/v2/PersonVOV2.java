package br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVOV2 extends RepresentationModel<PersonVOV2> implements Serializable
{
    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date birthDay;

    public PersonVOV2() {

    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() { return birthDay; }

    public void setBirthDay(Date birthDay) { this.birthDay = birthDay; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonVOV2 that = (PersonVOV2) o;
        return Objects.equals(key, that.key) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender) && Objects.equals(birthDay, that.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstName, lastName, address, gender, birthDay);
    }
}
