package guru.spring5framework.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Owner Class
 */
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Owner{");
        sb.append("address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", pets=").append(pets);
        sb.append('}');
        return sb.toString();
    }
}
