package guru.spring5framework.sfgpetclinic.bootstrap;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Pet;
import guru.spring5framework.sfgpetclinic.model.PetType;
import guru.spring5framework.sfgpetclinic.model.Vet;
import guru.spring5framework.sfgpetclinic.services.OwnerService;
import guru.spring5framework.sfgpetclinic.services.PetTypeService;
import guru.spring5framework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;

  @Autowired
  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
    this.vetService = vetService;
    this.ownerService = ownerService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(String... args) throws Exception {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType saveDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType saveCatPetType = petTypeService.save(cat);

    System.out.println("Loaded Pet Types");


    Owner owner1 = new Owner();
    owner1.setFirstName("Michael");
    owner1.setLastName("Mundu");
    owner1.setAddress("123 Ghar ka Address");
    owner1.setCity("Delhi");
    owner1.setTelephone("1231231231");

    Pet mikesPet = new Pet();
    mikesPet.setPetType(saveDogPetType);
    mikesPet.setBirthDate(LocalDate.now());
    mikesPet.setName("Shiru");
    mikesPet.setOwner(owner1);

    Pet mikesPet2 = new Pet();
    mikesPet2.setPetType(saveCatPetType);
    mikesPet2.setBirthDate(LocalDate.now());
    mikesPet2.setName("KalliBilli");
    mikesPet2.setOwner(owner1);

    owner1.getPets().add(mikesPet);
    owner1.getPets().add(mikesPet2);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Flora");
    owner2.setLastName("Kumari");
    owner2.setAddress("456 Ghar ka Address Naya Wala");
    owner2.setCity("Delhi123");
    owner2.setTelephone("1212121212");

    owner2.getPets().add(mikesPet);
    owner2.getPets().add(mikesPet2);
    ownerService.save(owner2);

    System.out.println("Loaded Owners..");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jessic");
    vet2.setLastName("Porter");
    vetService.save(vet2);

    System.out.println("Loaded Vet");

  }
}
