package p02_ExtendedDatabase;

import javax.naming.OperationNotSupportedException;
import java.util.HashSet;
import java.util.Set;

public class Database {

    private Person[] elements;
    private int elementsCount;
    private int index;
    private Set<String> peopleNameSet;
    private Set<Integer> peopleIdSet;


    public Database(Person... people) throws OperationNotSupportedException {
        this.elementsCount = people.length;
        this.peopleNameSet = new HashSet<>();
        this.peopleIdSet = new HashSet<>();
        this.setElements(people);
        this.index = elementsCount - 1;
    }

    public void add(Person person) throws OperationNotSupportedException {
        if (person == null) {
            throw new OperationNotSupportedException();
        }

        if (this.peopleNameSet.contains(person.getUsername())){
            throw new OperationNotSupportedException("Unique username required!");
        }
        if (this.peopleIdSet.contains(person.getId())){
            throw new OperationNotSupportedException("Unique ID required!");
        }

        if (person.getId() < 0){
            throw new OperationNotSupportedException("ID must not be negative!");
        }

        this.elements[++index] = person;
        this.elementsCount++;
        this.peopleNameSet.add(person.getUsername());
        this.peopleIdSet.add(person.getId());
    }

    public void remove() throws OperationNotSupportedException {
        try {
			this.elements[index--] = null;
			this.elementsCount--;			
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new OperationNotSupportedException();
		}
    }

    public Person[] getElements() {
        Person[] buffer = new Person[elementsCount];
        int bufferIndex = 0;

        for (Person person : elements) {
            if (person != null) {
                buffer[bufferIndex++] = person;
            }
        }

        return buffer;
    }

    public Person findByUsername(String username) throws OperationNotSupportedException {

        if (username == null) {
            throw new OperationNotSupportedException();
        }

        Person personToReturn = null;

        for (Person person : elements) {

            if (person == null) {
                continue;
            }

            if (person.getUsername().equals(username)) {
               personToReturn = person;
               break;
            }
        }

        if (personToReturn == null) {
            throw new OperationNotSupportedException();
        }

        return personToReturn;
    }

    public Person findById(long id) throws OperationNotSupportedException {

        Person personToReturn = null;

        for (Person person : elements) {

            if (person == null) {
                continue;
            }

            if (person.getId() == id) {
                personToReturn = person;
                break;
            }
        }

        if (personToReturn == null) {
            throw new OperationNotSupportedException();
        }

        return personToReturn;
    }

    private void setElements(Person... elements) throws OperationNotSupportedException {
        if (elements.length > 16 ||
                elements.length < 1) {
            throw new OperationNotSupportedException();
        }

        this.elements = new Person[16];

        int bufferIndex = 0;

        //sets are used to test for uniqueIDs and usernames
        for (Person person : elements) {
            if (person.getId() < 0){
                throw new OperationNotSupportedException("ID must not be negative!");
            }
            this.elements[bufferIndex++] = person;
            this.peopleNameSet.add(person.getUsername());
            this.peopleIdSet.add(person.getId());
        }

        if (this.elements.length < this.peopleNameSet.size() || this.elements.length < this.peopleIdSet.size()) {
            throw new OperationNotSupportedException("ids and usernames must be unique");
        }

        this.index = elements.length - 1;
    }
}
