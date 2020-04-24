package guru.spring5framework.sfgpetclinic.repositories;

import guru.spring5framework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
}
