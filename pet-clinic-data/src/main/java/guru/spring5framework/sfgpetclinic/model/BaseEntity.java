package guru.spring5framework.sfgpetclinic.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Base Entity
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
