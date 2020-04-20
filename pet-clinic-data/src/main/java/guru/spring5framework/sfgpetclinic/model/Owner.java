package guru.spring5framework.sfgpetclinic.model;

import java.util.Set;

/**
 * Owner Class
 */
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;

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
