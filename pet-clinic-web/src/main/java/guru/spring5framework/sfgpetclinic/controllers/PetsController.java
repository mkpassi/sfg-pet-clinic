package guru.spring5framework.sfgpetclinic.controllers;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.PetType;
import guru.spring5framework.sfgpetclinic.services.OwnerService;
import guru.spring5framework.sfgpetclinic.services.PetService;
import guru.spring5framework.sfgpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Set;

@RequestMapping("/owners/{ownerId}")
@Controller
@Slf4j
public class PetsController {

    private final OwnerService ownerService;

    private final PetService petService;

    private final PetTypeService petTypeService;

    private final static String PETS_CREATE_OR_UPDATE_FORM =
            "pets/createOrUpdatePetForm";

    public PetsController (OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }


    //Disallowing any Id coming from Web .
    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        log.debug("########################################");
        log.debug("Trying to get Pet Types");
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") int ownerId) {
        log.debug("OwnerID accessed for Object : {} ", ownerId);
        return ownerService.findById(Long.valueOf(ownerId));
    }

    @GetMapping("/pets/new")
    public String addNewPetFormInit(@PathVariable("ownerId") int ownerId, Model model ) {
        log.debug("Add new Pet with OwnerId :{}", ownerId);
        Owner ownerById = ownerService.findById(Long.valueOf(ownerId));
        Set<PetType> allPetTypes = petTypeService.findAll();
        Pet pet = Pet.builder().owner(ownerById).build();
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String addNewPetFormInit(@PathVariable("ownerId") int ownerId,
                                    @ModelAttribute Pet pet ,
                                    BindingResult bindingResult) {
        log.debug("#########################################");
        log.debug("Add new Pet with OwnerId :{}", ownerId);
        log.debug("Pet variable : {} ", pet );
        log.debug("Binding Resule :{}",bindingResult);
        return PETS_CREATE_OR_UPDATE_FORM;
    }
}
