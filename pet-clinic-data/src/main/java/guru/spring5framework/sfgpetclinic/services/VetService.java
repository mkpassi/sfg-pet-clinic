package guru.spring5framework.sfgpetclinic.services;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
