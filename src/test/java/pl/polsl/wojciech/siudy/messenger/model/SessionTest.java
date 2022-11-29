package pl.polsl.wojciech.siudy.messenger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.wojciech.siudy.messenger.Exceptions.EmptyBoxException;

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
        String actualValue = session.getInbox().lastElement().getContent();
        //THEN
        assertEquals(contentOfMessage, actualValue, "Message not added to inbox");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "Cześć"})
    void testAddMessageToOutbox(String contentOfMessage) {
        //GIVEN
        Message message = new Message(user, contentOfMessage);
        //WHEN
        session.addMessageToOutbox(message);
        String actualValue = session.getOutbox().getLast().getContent();
        //THEN
        assertEquals(contentOfMessage, actualValue, "Message not added to inbox");
    }

    interface VoidWithException extends Executable {
        @Override
        void execute() throws Throwable;
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "Cześć"})
    void TestGetMessageToSend(String contentOfMessage) {
        VoidWithException DoGetMessageToSend = () -> session.getMessageToSend();
        //GIVEN
        Message message = new Message(user, contentOfMessage);
        //THEN
        assertThrows(EmptyBoxException.class, DoGetMessageToSend);
        //WHEN
        session.addMessageToOutbox(message);
        String actualValue = session.getOutbox().getLast().getContent();
        //THEN
        assertEquals(contentOfMessage, actualValue, "Message not added to inbox");
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