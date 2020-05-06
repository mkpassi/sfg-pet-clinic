package guru.spring5framework.sfgpetclinic.controllers;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.PetType;
import guru.spring5framework.sfgpetclinic.services.OwnerService;
import guru.spring5framework.sfgpetclinic.services.PetService;
import guru.spring5framework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;


    MockMvc mockMvc;

    Owner owner ;
    List<PetType> petTypes;

    @BeforeEach
    void setUp () {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        owner = Owner.builder().id(1l).build();
        petTypes = new ArrayList<>();
        petTypes.add(PetType.builder().id(1l).name("Dog").build());
        petTypes.add(PetType.builder().id(2l).name("Cat").build());
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(new HashSet<>(petTypes));
      //  when(petService.findById(anyLong())).thenReturn(Pet.builder().id
        //  (2l).build());

    }

    @Test
    public void testInitCreationForm() throws Exception {

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    public void processCreationForm() throws Exception{
        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    public void testInitUpdatePetForm() throws Exception{
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id
        (5l).owner(owner).build());
        mockMvc.perform(get("/owners/1/pets/5/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    public void processUpDatePetForm() throws Exception{
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id
         (5l).build());
        when(petService.save(Mockito.any())).thenReturn(Pet.builder().id(5l).owner(owner).build());
        mockMvc.perform(post("/owners/1/pets/5/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }
}
