package com.blablaprincess.springboot_simplejava.business.digitsrepresentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DigitsRepresentationTests {

    @DisplayName("getDigitsArray")
    @ParameterizedTest(name = "with {0}")
    @MethodSource("getDigitsArrayTestCases")
    void getLength(String description, Integer num, Integer[] expected) {
        // Act
        Integer[] result = DigitsRepresentation.getDigitsArray(num);

        // Assert
        assertArrayEquals(expected, result);
    }

    static Stream<Arguments> getDigitsArrayTestCases() {
        return Stream.of(
                arguments("positive num",  12345, new Integer[]{1, 2, 3, 4, 5}),
                arguments("negative num", -12345, new Integer[]{1, 2, 3, 4, 5}),
                arguments("single digit",  2,     new Integer[]{2})
                        );
    }

}