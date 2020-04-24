package guru.spring5framework.sfgpetclinic.repositories;

import guru.spring5framework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
