package guru.spring5framework.sfgpetclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base Entity
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id // this is the id Value
    //Generation Type strategy TABLE|SEQUENCE|IDENTITY|AUTO
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
