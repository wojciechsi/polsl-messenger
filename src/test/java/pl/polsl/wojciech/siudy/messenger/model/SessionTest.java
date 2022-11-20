package pl.polsl.wojciech.siudy.messenger.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    private User user;
    private Session session;

    @BeforeEach
    public void setUp() {
        user = new User("Kowalski");
        session = new Session(user, "127.0.0.1", 80, 81);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "Cześć"})
    void testAddMessageToInbox(String contentOfMessage) {
        //GIVEN
        Message message = new Message(user, contentOfMessage);
        //WHEN
        session.addMessageToInbox(message);
        String actualValue = session.getInbox().toArray()[0].toString();
        //THEN
        assertEquals(contentOfMessage, actualValue, "Message not added to inbox");
    }

    @Test
    void testAddMessageToOutbox() {
    }

    @Test
    void TestGetMessageToSend() {
    }

    @Test
    void TestAnyMessages() {
        //GIVEN default setup
        //WHEN
        boolean ifMessages = session.anyMessages();
        //THEN
        assertFalse(ifMessages);

        //GIVEN
        Message message = new Message(user, "Test message");
        //WHEN
        session.addMessageToInbox(message);
        ifMessages = session.anyMessages();
        //THEN
        assertTrue(ifMessages);
    }
}