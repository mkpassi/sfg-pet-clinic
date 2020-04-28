package guru.spring5framework.sfgpetclinic.controllers;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.services.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  private Long OWNER_ID = 5l;
  private String FIRST_NAME = "James";
  private String LAST_NAME = "hopkins";
  @Mock OwnerService ownerService;

  @InjectMocks OwnerController controller;

  Owner returnedOwner;

  Set<Owner> owners;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    returnedOwner = Owner.builder().id(OWNER_ID).firstName(FIRST_NAME).lastName(LAST_NAME).build();
    owners = new HashSet<>();
    owners.add(Owner.builder().id(1L).build());
    owners.add(Owner.builder().id(2L).build());
    owners.add(Owner.builder().id(3L).build());
    owners.add(Owner.builder().id(4L).build());
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void testIndexPage() throws Exception {
    Mockito.when(ownerService.findAll()).thenReturn(owners);

    mockMvc.perform(MockMvcRequestBuilders.get("/owners")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("owners/index"))
        .andExpect(MockMvcResultMatchers.model().attribute("owners", Matchers.hasSize(4)));
  }

  @Test
  public void testFindINdex() throws Exception{
    mockMvc
        .perform(MockMvcRequestBuilders.get("/owners/find"))
        .andExpect(MockMvcResultMatchers.view().name("notImplmented"));

    Mockito.verifyNoInteractions(ownerService);
  }
}
