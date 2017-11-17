package services;

import entities.PersonEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonDAO {

    @PersistenceContext(name = "NewPersistenceUnit")
    private EntityManager manager;

    public PersonEntity getPersonByID(int id) {
        return manager.find(PersonEntity.class, id);
    }

    public List<PersonEntity> getAllPersons() {
        return manager.createQuery("SELECT p FROM PersonEntity p", PersonEntity.class).getResultList();
    }

    public PersonEntity addPerson(PersonEntity person) {
        manager.persist(person);
        return person;
    }

    public PersonEntity updatePerson(PersonEntity person) {
        // update OR create - if you want just update check if element already exists
        return manager.merge(person);
    }

    public Boolean deletePerson(int id) {
        PersonEntity person = getPersonByID(id);

        if (person != null) {
            manager.remove(person);
            return true;
        }
        return false;
    }
}
