package guru.spring5framework.sfgpetclinic.services.map;

import guru.spring5framework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

  OwnerServiceMap ownerServiceMap;
  private final Long ownerId = 4L;

  @BeforeEach
  void setUp() {
    ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
    Owner owner = new Owner();
    owner.setId(ownerId);
    owner.setLastName("lastName");
    ownerServiceMap.save(owner);
  }

  @Test
  void findAll() {
    Set<Owner> owners = ownerServiceMap.findAll();
    assertEquals(1, owners.size());
  }

  @Test
  void deleteById() {}

  @Test
  void delete() {}

  @Test
  void fndById() {
    Owner owner = ownerServiceMap.findById(ownerId);
    assertEquals(ownerId,owner.getId());
  }

  @Test
  void save() {
    Long ownerId = 5L;
    Owner owner = new Owner();
    owner.setId(ownerId);
    Owner savedOwner = ownerServiceMap.save(owner);
    assertEquals(ownerId, savedOwner.getId());
  }

  @Test
  void findByLastName() {
    Long ownerId = 6L;
    Owner owner = new Owner();
    owner.setId(ownerId);
    owner.setLastName("lastName");
    Owner savedOwner = ownerServiceMap.save(owner);
    assertEquals(ownerId, savedOwner.getId());
    assertEquals("lastName", ownerServiceMap.findByLastName("lastName").getLastName());
  }
}
