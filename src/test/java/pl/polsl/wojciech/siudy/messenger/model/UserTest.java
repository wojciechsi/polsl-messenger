package pl.polsl.wojciech.siudy.messenger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Kowalski");
    }

    private static Stream<Arguments> userNamesStrings() {
        return Stream.of (
                Arguments.arguments("Jan Kowalski", false),
                Arguments.arguments("jkowalski", true),
                Arguments.arguments("jkowalski ", false),
                Arguments.arguments("jkowalski61", true),
                Arguments.arguments("%janKowalski34", true)
        );
    }

    @ParameterizedTest
    @MethodSource("userNamesStrings")
    void nameIsOneWord(String userName, boolean shouldPass) {
        //GIVEN basic setup
        //WHEN
        user.setName(userName);
        //THEN
        assertTrue(shouldPass && user.nameIsOneWord() ||
                !shouldPass && !user.nameIsOneWord());
    }
}