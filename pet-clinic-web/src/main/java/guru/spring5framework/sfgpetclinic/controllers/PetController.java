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
import org.springframework.util.StringUtils;
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
public class PetController {

    private final OwnerService ownerService;

    private final PetService petService;

    private final PetTypeService petTypeService;

    private final static String PETS_CREATE_OR_UPDATE_FORM =
            "pets/createOrUpdatePetForm";

    public PetController (OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
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
    public String addNewPetFormInit(Owner owner, Model model ) {
        log.debug("Showing form for creating New Pet");
        log.debug("Add new Pet with OwnerId :{}", owner.getId());
        Pet pet = Pet.builder().build();
        pet.setOwner(owner);
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/pets/{petId}/edit")
    public String editPetFormInit(Owner owner,
                                  @PathVariable Long petId, Model model ) {
        log.debug("showing Form for updating Pet");
        log.debug("Add new Pet with OwnerId :{}", owner.getId());
        Pet pet = petService.findById(petId);
        pet.setOwner(owner);
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String addNewPetFormInit(Owner owner,
                                    @ModelAttribute Pet pet ,
                                    BindingResult bindingResult, Model model) {
        log.debug("Adding New Pet");
        log.debug("#########################################");
        log.debug("Add new Pet with OwnerId :{}",owner.getId());
        log.debug("Pet variable : {} ", pet );
        log.debug("Binding Resule :{}",bindingResult);

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(),true) != null) {
            bindingResult.rejectValue("name" ,"duplicate", "already exists");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_FORM;
        }else{
            if(pet.isNew()){
                log.debug("Adding new Pet for Owner : {} ");
                owner.getPets().add(pet);
                pet.setOwner(owner);
            }
            petService.save(pet);
            return "redirect:/owners/"+owner.getId();
        }
    }

    @PostMapping("/pets/{petId}/edit")
    public String editUpdatePet(Owner owner,
                                    @ModelAttribute Pet pet ,
                                @PathVariable Long petId,
                                    BindingResult bindingResult, Model model) {
        log.debug("Trying to save Updated Pet");
        log.debug("#########################################");
        log.debug("Add new Pet with OwnerId {} and Pet Id :{}", owner.getId(),
                petId);
        log.debug("Pet variable : {} ", pet);
        log.debug("Binding Resule :{}", bindingResult);
        if (bindingResult.hasErrors()) {
            pet.setOwner(owner);
            pet.setId(petId);
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_FORM;
        }else if (petId != null) {
            Pet petById = petService.findById(petId);
            log.debug("pet By Id is : {}", petById);
            if (!StringUtils.isEmpty(pet.getName())) {
                petById.setName(pet.getName());
            }
            if (pet.getBirthDate() != null) {
                petById.setBirthDate(pet.getBirthDate());
            }
            if (pet.getPetType() != null) {
                petById.setPetType(pet.getPetType());
            }
            petById.setOwner(owner);
            log.debug("Pet Owner is : {}", petById.getOwner());
            Pet savedPet = petService.save(petById);
            return "redirect:/owners/" + savedPet.getOwner().getId();
        }
        log.debug("Some Issue while updating Pet, redirecting to home page");
        return PETS_CREATE_OR_UPDATE_FORM;
    }
}
