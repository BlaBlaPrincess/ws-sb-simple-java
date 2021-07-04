package com.blablaprincess.springboot_simplejava.business.arraycounting.integers;

import com.blablaprincess.springboot_simplejava.business.common.exceptions.EmptyArrayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IntegersAverageTests {

    private static final IntegersAverage integersAverage = new IntegersAverage();

    private interface TestScenario {
        void test(Integer[] set, Object expected);
    }

    private static final TestScenario toEquals = (set, expected) -> {
        // Act
        double result = integersAverage.count(set);

        // Assert
        assertEquals(expected, result);
    };

    @SuppressWarnings("unchecked")
    private static final TestScenario toThrows = (set, exception) -> {
        // Act + Assert
        assertThrows((Class<Exception>) exception, () -> integersAverage.count(set));
    };

    @DisplayName("count")
    @ParameterizedTest(name = "with {0}")
    @MethodSource("countTestCases")
    void count(String description, Integer[] set, TestScenario testScenario, Object expected) {
        testScenario.test(set, expected);
    }

    static Stream<Arguments> countTestCases() {
        return Stream.of(
                arguments("positive set", new Integer[]{1, 2, 3, 4, 10}, toEquals, 4d),
                arguments("mixed set",    new Integer[]{20, -10},        toEquals, 5d),
                arguments("empty set",    new Integer[]{},               toThrows, EmptyArrayException.class)
                        );
    }

}