package guru.spring5framework.sfgpetclinic.repositories;

import guru.spring5framework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
