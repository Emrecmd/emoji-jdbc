package controller;

import entity.Person;
import service.PersonService;

import java.util.List;

public class PersonController {
    private PersonService personService = new PersonService();

    public void addPerson(String isim, int age, String emoji) {
        personService.addPerson(new Person(isim, emoji, age));
    }

    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    public void updatePersons(int id, String isim, int yas, String emoji) {
        personService.updatePersons(new Person(isim, emoji, yas), id);
    }

    public void deletePerson(int id) {
        personService.deletePerson(id);
    }
}
