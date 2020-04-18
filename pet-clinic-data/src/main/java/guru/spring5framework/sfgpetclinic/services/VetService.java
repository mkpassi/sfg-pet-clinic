package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Person;
import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService<Vet extends Person,Long extends Number> extends CrudService<Vet,Long> {
}
