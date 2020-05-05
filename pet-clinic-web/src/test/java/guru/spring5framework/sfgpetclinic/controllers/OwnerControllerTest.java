package guru.spring5framework.sfgpetclinic.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;

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

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.services.OwnerService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private Long OWNER_ID = 5l;
    private String FIRST_NAME = "James";
    private String LAST_NAME = "hopkins";
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerController controller;
    Owner returnedOwner;
    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    public void setup () {
        returnedOwner = Owner.builder().id(OWNER_ID).firstName(FIRST_NAME).lastName(LAST_NAME).build();
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).lastName("bulbul").build());
        owners.add(Owner.builder().id(2L).lastName("bulbul").build());
        owners.add(Owner.builder().id(3L).build());
        owners.add(Owner.builder().id(4L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    void testDisplayOwners () throws Exception {
        when(ownerService.findById(Mockito.any())).thenReturn(returnedOwner);

        mockMvc.perform((get("/owners/1")))
                .andExpect(status().isOk());
    }

    @Test
    void processFindFormReturnMany () throws Exception {
        List<Owner> ownerList = new ArrayList<>(owners);
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(ownerList);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));
    }

    @Test
    void processFindFormReturnOne () throws Exception {
        when(ownerService.findAllByLastNameLike(Mockito.anyString())).thenReturn(Arrays.asList(Owner.builder().id(5l).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/5"));
    }

    @Test
    void initUpdateOwnerForm () throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService,Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void processUpdateOwnerForm () throws Exception {
        when(ownerService.save(Mockito.any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService,times(1)).save(Mockito.any());
    }

    @Test
    void processNewFormForOwner () throws Exception {
        Owner newOwner = Owner.builder().id(1l).firstName("He").lastName("Man").build();
        when(ownerService.save(Mockito.any())).thenReturn(newOwner);

        mockMvc.perform(post("/owners/new").param("firstName", "He").param(
                "lastName", "Man")
                .param("address", "123 Caramel Street").param("city", "London").param("telephone", "01316761638"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService,times(1)).save(Mockito.any());

    }

    @Test
    void createNewOwner() throws Exception{
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
    }
}
