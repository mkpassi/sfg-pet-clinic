package guru.spring5framework.sfgpetclinic.services.map;

import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet pet) {
        super.delete(pet);
    }

    @Override
    public Pet fndById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet);
    }
}
