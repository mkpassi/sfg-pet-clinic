package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Person;

public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
}
