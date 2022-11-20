package pl.polsl.wojciech.siudy.messenger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Kowalski");
    }

    @Test
    void nameIsOneWord() {
        //GIVEN basic setup
        //THEN
        assertTrue(user.nameIsOneWord());

        //GIVEN
        user = new User("Jan Kowalski");
        //THEN
        assertFalse(user.nameIsOneWord());
    }
}