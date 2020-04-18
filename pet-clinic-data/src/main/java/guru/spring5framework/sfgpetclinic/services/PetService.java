package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.BaseEntity;
import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService<P extends BaseEntity, L extends Number> extends CrudService<Pet, Long> {
}
