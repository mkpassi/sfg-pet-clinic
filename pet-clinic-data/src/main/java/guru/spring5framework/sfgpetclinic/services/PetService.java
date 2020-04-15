package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Owner save(Pet pet);
    Set<Pet> findAll();
}
