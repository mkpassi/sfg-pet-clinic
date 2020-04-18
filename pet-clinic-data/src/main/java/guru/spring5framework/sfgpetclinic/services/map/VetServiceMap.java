package guru.spring5framework.sfgpetclinic.services.map;

import guru.spring5framework.sfgpetclinic.model.Person;
import guru.spring5framework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap<Vet extends Person,Id extends Number> extends AbstractMapService<Vet,Long> implements VetService<Vet,Long> {

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
