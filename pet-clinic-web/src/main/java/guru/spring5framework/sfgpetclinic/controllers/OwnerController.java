package guru.spring5framework.sfgpetclinic.controllers;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //Disallowing any Id coming from Web .
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/owners","/owners/index","/owners/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }


    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(Long.valueOf(ownerId)));
        return modelAndView;
    }

    @GetMapping({"/owners/find","/oups"})
    public String initFindOwnerForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(@ModelAttribute @Valid Owner owner,
                                  BindingResult result,
                                  Model model) throws Exception{

        if(owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> ownerList =
                ownerService.findAllByLastNameLike("%" + owner.getLastName()+
                        "%");

        if(ownerList.isEmpty()){
            result.rejectValue("lastName","not found","not found");
            return "owners/findOwners";
        }else if(ownerList.size() ==1){
            return "redirect:/owners/"+ownerList.iterator().next().getId();
        }else{
            model.addAttribute("selections", ownerList);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/new")
    public String initFormNew(Model model){
        Owner newOwner = Owner.builder().build();
        model.addAttribute("owner", newOwner);
        return "/owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/owners/new")
    public String createOwner(@ModelAttribute Owner owner,
                              BindingResult result){

        if ((result.hasErrors()) || ((owner.getLastName().equals("")) && (owner.getLastName().equals("")))) {
            if(owner.getLastName().equals("")){
                result.rejectValue("lastName","Something not correct",
                        "ErrorValue");
            }
            if(owner.getFirstName().equals("")){
                result.rejectValue("firstName","Something not correct",
                        "ErrorValue");
            }
            return "/owners/createOrUpdateOwnerForm";
        }

        Owner savedOwnerObject = ownerService.save(owner);
        return "redirect:/owners/"+savedOwnerObject.getId();
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable int ownerId, Model model) {
        Owner owner = ownerService.findById(Long.valueOf(ownerId));
        model.addAttribute("owner",owner);
        return  "/owners/createOrUpdateOwnerForm";
    }



    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
                                         @PathVariable int ownerId) {
        if (result.hasErrors()) {
            return  "/owners/createOrUpdateOwnerForm";
        }
        else {
            owner.setId(Long.valueOf(ownerId));
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

}
