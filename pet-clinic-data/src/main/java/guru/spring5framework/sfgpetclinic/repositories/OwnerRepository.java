package guru.spring5framework.sfgpetclinic.repositories;

import guru.spring5framework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
