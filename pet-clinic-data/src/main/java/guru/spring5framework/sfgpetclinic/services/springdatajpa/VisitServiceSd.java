package guru.spring5framework.sfgpetclinic.services.springdatajpa;

import guru.spring5framework.sfgpetclinic.model.Visit;
import guru.spring5framework.sfgpetclinic.repositories.VisitRepository;
import guru.spring5framework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitServiceSd implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceSd(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }


    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit fndById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitRepository.deleteById(aLong);
    }
}
