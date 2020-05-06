package guru.spring5framework.sfgpetclinic.controllers;

import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.Visit;
import guru.spring5framework.sfgpetclinic.services.PetService;
import guru.spring5framework.sfgpetclinic.services.VisitService;
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

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;


    public VisitController (VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadVisitsForPet(@PathVariable Long petId, Model model){
        log.debug("PetId for Visited Pet is :{}" + petId);
        Pet pet = petService.findById(petId);
        log.debug("Pet is {}", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        model.addAttribute("pet", pet);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        log.debug("Displaying Visit Form");
        return "pets/createOrUpdateVisitForm";
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        }
        else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

}
