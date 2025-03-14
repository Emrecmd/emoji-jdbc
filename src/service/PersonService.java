package service;

import dao.PersonDAO;
import entity.Person;
import java.util.List;

public class PersonService {
    private PersonDAO personDAO = new PersonDAO();

    public void addPerson(Person person) {
        if(person.getName().isEmpty() || person.getAge() < 0) {
            throw new IllegalArgumentException("geçersiz kişi verisi");
        }
        personDAO.createPerson(person);
    }

    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    public void updatePersons(Person person, int id) {
        personDAO.updatePerson(person, id);
    }

    public void deletePerson(int id) {
        personDAO.deletePerson(id);
    }
}
