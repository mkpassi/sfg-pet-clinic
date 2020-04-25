package guru.spring5framework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



/**
 * Owner Class
 */
@Entity
@Table(name = "owners")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner extends Person {

    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
}
