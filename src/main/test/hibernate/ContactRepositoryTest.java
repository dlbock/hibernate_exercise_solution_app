package hibernate;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContactRepositoryTest {
    private ContactRepository contactRepository = new ContactRepository();

    @Before
    public void cleanRepository() {
        contactRepository.clean();            
    }

    @Test
    public void shouldSaveContactsToRepository() {
        contactRepository.save(createContact("Robert", "James"));
        contactRepository.save(createContact("Joe", "Smith"));

        List<Contact> savedContacts = contactRepository.findAll();
        assertThat(savedContacts.size(), is(2));
    }

    private Contact createContact(String firstName, String lastName) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        return contact;
    }
}
