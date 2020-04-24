package guru.spring5framework.sfgpetclinic.repositories;

import guru.spring5framework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
