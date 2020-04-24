package guru.spring5framework.sfgpetclinic.bootstrap;

import guru.spring5framework.sfgpetclinic.model.*;
import guru.spring5framework.sfgpetclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialityService specialityService;
  //private final PetService petService;
  private final VisitService visitService;

  @Autowired
  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService,  VisitService visitService) {
    this.vetService = vetService;
    this.ownerService = ownerService;
    this.petTypeService = petTypeService;
    this.specialityService = specialityService;
   // this.petService = petService;
    this.visitService = visitService;
  }

  @Override
  public void run(String... args) throws Exception {
    if(petTypeService.findAll().size() ==0){
      System.out.println("Loading Data in Maps");
      loadData();
    }
  }

  private void loadData() {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    System.out.println("Loaded Pet Types");

    Speciality radiology = new Speciality();
    radiology.setDescription("radiology");
    Speciality savedRadiologySpeciality = specialityService.save(radiology);

    Speciality surgery = new Speciality();
    surgery.setDescription("surgery");
    Speciality savedSurgerySpeciality = specialityService.save(surgery);

    Speciality dentistry = new Speciality();
    dentistry.setDescription("dentistry");
    Speciality savedDentistrySpeciality = specialityService.save(dentistry);

    System.out.println("Speciality Services Added");


    Owner owner1 = new Owner();
    owner1.setFirstName("Michael");
    owner1.setLastName("Weston");
    owner1.setAddress("123 Brickerel");
    owner1.setCity("Miami");
    owner1.setTelephone("1231231234");

    Pet mikesPet = new Pet();
    mikesPet.setPetType(savedDogPetType);
    mikesPet.setOwner(owner1);
    mikesPet.setBirthDate(LocalDate.now());
    mikesPet.setName("Rosco");
    owner1.getPets().add(mikesPet);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Flora");
    owner2.setLastName("Kumari");
    owner2.setAddress("456 Ghar ka Address Naya Wala");
    owner2.setCity("Delhi123");
    owner2.setTelephone("1212121212");

    Pet fionasCat = new Pet();
    fionasCat.setName("Just Cat");
    fionasCat.setOwner(owner2);
    fionasCat.setBirthDate(LocalDate.now());
    fionasCat.setPetType(savedCatPetType);
    owner2.getPets().add(fionasCat);

    ownerService.save(owner2);

    Visit catVisit = new Visit();
    catVisit.setPet(fionasCat);
    catVisit.setDate(LocalDate.now());
    catVisit.setDescription("Mikes Sneezy Cat");
    Visit savedCatVisit = visitService.save(catVisit);

    System.out.println("Loaded Owners..");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");



    vet1.getSpecialities().add(savedRadiologySpeciality);
    vet1.getSpecialities().add(savedDentistrySpeciality);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jessic");
    vet2.setLastName("Porter");

    vet2.getSpecialities().add(savedSurgerySpeciality);

    vetService.save(vet2);

    System.out.println("Loaded Vet");
  }
}
