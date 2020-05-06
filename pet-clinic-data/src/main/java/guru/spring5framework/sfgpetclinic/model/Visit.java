package guru.spring5framework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="visits")
public class Visit extends BaseEntity{

    @Builder
    public Visit (Long id, LocalDate date, String description) {
        super(id);
        this.date = date;
        this.description = description;
    }

    @Column(name="date")
    private LocalDate date;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;


}
