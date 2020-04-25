package guru.spring5framework.sfgpetclinic.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base Entity
 */
@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

    @Id // this is the id Value
    //Generation Type strategy TABLE|SEQUENCE|IDENTITY|AUTO
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

}
