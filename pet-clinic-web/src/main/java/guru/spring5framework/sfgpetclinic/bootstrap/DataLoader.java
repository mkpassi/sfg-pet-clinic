package guru.spring5framework.sfgpetclinic.bootstrap;

import guru.spring5framework.sfgpetclinic.model.Owner;
import guru.spring5framework.sfgpetclinic.model.Person;
import guru.spring5framework.sfgpetclinic.model.Vet;
import guru.spring5framework.sfgpetclinic.services.OwnerService;
import guru.spring5framework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService<Person, Number> vetService;

  @Autowired
  public DataLoader(OwnerService ownerService, VetService vetService) {
    this.vetService = vetService;
    this.ownerService = ownerService;
  }

  @Override
  public void run(String... args) throws Exception {
    Owner owner1 = new Owner();
    owner1.setFirstName("Michael");
    owner1.setLastName("Mundu");
    owner1.setId(1L);
    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Flora");
    owner2.setLastName("Kumari");
    owner2.setId(2L);
    ownerService.save(owner2);

    System.out.println("Loaded Owners..");

    Vet vet1 = new Vet();
    vet1.setId(1L);
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");

    Vet vet2 = new Vet();
    vet2.setId(2L);
    vet2.setFirstName("Jessic");
    vet2.setLastName("Porter");

    vetService.save(vet1);
    vetService.save(vet2);

    System.out.println("Loaded Vet");
  }
}
