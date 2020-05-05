package guru.spring5framework.sfgpetclinic.services.springdatajpa;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.repositories.OwnerRepository;
import guru.spring5framework.sfgpetclinic.repositories.PetRepository;
import guru.spring5framework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
public class OwnerServiceSdTest {

  private final Long OWNER_ID = 4l;
  private final String LAST_NAME = "Smith";
  private final String FIRST_NAME = "John";


  @Mock
  OwnerRepository ownerRepository;

  @Mock
  PetRepository petRepository;

  @Mock
  PetTypeRepository petTypeRepository;

  @InjectMocks
  OwnerServiceSd ownerServiceSd;

  Owner returnOwner;

  @BeforeEach
  void setUp() {
    Long id =8l;
    returnOwner = Owner.builder().id(id).firstName("John").lastName("hopkins").build();
  }

  @Test
  void findByLastName() {
    Owner owner=Owner.builder().id(OWNER_ID).lastName(LAST_NAME).firstName(FIRST_NAME).build();
    Mockito.when(ownerRepository.findByLastName(Mockito.anyString())).thenReturn(owner);
    Owner smith = ownerServiceSd.findByLastName(LAST_NAME);
    Assertions.assertEquals(LAST_NAME, smith.getLastName());
    Mockito.verify(ownerRepository,Mockito.times(1)).findByLastName(Mockito.eq(LAST_NAME));
  }

  @Test
  void findAll() {
   Set<Owner> ownerSet = new HashSet<>();
   ownerSet.add(Owner.builder().id(1l).build());
   ownerSet.add(Owner.builder().id(2l).build());
    Mockito.when(ownerRepository.findAll()).thenReturn(ownerSet);
   Set<Owner> returnedSet = ownerServiceSd.findAll();
   Assertions.assertNotNull(returnedSet);
   Assertions.assertEquals(2, returnedSet.size());
  }

  @Test
  void fndById() {
    Owner owner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).firstName(FIRST_NAME).build();
    Optional<Owner> optionalOwner = Optional.ofNullable(owner);
    Mockito.when(ownerRepository.findById(OWNER_ID)).thenReturn(optionalOwner);
    Owner ownerById = ownerServiceSd.findById(OWNER_ID);
    Assertions.assertEquals(OWNER_ID, ownerById.getId());
  }

  @Test
  void save() {
    Owner owner = Owner.builder().id(12L).firstName("ABC").lastName("DEF").build();
    Mockito.when(ownerServiceSd.save(Mockito.any())).thenReturn(owner);
    Owner savedOwner = ownerServiceSd.save(owner);
    Assertions.assertNotNull(savedOwner);
  }

  @Test
  void delete() {
    ownerServiceSd.delete(returnOwner);
    Mockito.verify(ownerRepository, Mockito.times(1)).delete(Mockito.any());
  }

  @Test
  void deleteById() {
    ownerServiceSd.deleteById(returnOwner.getId());
    Mockito.verify(ownerRepository).deleteById(Mockito.any());
  }
}
