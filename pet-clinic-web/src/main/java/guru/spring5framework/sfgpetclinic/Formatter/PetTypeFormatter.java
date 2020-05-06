package guru.spring5framework.sfgpetclinic.Formatter;

import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.PetType;
import guru.spring5framework.sfgpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;


@Component
@Slf4j
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;


    public PetTypeFormatter (PetTypeService petService) {
        this.petTypeService = petService;
    }


    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String petType, Locale locale) throws ParseException {
        Set<PetType> pets = petTypeService.findAll();
        for (PetType type : pets) {
            if (type.getName().equals(petType)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + petType, 0);
    }

}
