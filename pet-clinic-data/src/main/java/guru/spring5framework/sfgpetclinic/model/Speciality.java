package guru.spring5framework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="specialties")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speciality extends BaseEntity {

    @Column(name = "description")
    private String description;



}
