package guru.spring5framework.sfgpetclinic.services.map;

import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.Vet;
import guru.spring5framework.sfgpetclinic.services.CrudService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet,Long> implements CrudService<Vet,Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet fndById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return save(vet.getId(),vet);
    }
}
