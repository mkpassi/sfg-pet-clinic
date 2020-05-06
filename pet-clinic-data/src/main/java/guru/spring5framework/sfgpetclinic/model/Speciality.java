package guru.spring5framework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="specialties")
public class Speciality extends BaseEntity {

    @Builder
    public Speciality (Long id, String description) {
        super(id);
        this.description = description;
    }

    @Column(name = "description")
    private String description;



}
