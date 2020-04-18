package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.Person;

public interface OwnerService<Owner extends Person, Id extends Number> extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
}
