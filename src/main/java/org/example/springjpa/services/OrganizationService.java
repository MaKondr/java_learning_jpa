package org.example.springjpa.services;

import org.example.springjpa.models.Organisation;
import org.example.springjpa.repositories.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrganizationService {
    private final OrganisationRepository organisationRepository;

    @Autowired
    public OrganizationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public List<Organisation> findAll() {
        List<Organisation> organisations = organisationRepository.findAll();
        organisations.forEach(System.out::println);
        return organisationRepository.findAll();
    }
    public Organisation findById(int id) {
        Optional<Organisation> organisation = organisationRepository.findById(id);
        organisation.ifPresent(System.out::println);
        return organisationRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Organisation organisation) {
        organisationRepository.save(organisation);
    }
    @Transactional
    public void delete(int id) {
        organisationRepository.deleteById(id);
    }
}
