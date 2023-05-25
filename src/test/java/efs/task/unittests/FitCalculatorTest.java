package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest
{

    @Test
    void shouldReturnTrue_whenDietRecommended()
    {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void isBMICorrect_ForGivenData_ShouldReturnFalse()
    {
        //given
        double weight = 69.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void isBMICorrect_ForHeight0_ShouldThrowIllegalArgumentException()
    {
        //given
        double weight = 100;
        double height = 0.0;

        //when
        Executable fun = () -> FitCalculator.isBMICorrect(weight, height);

        //then
        assertThrows(IllegalArgumentException.class, fun);
    }

    @ParameterizedTest(name = "{index}: FitCalculator.isBMICorrect({0}, 1.72) = True")
    @ValueSource(doubles = {85, 90, 95})
    void isBMICorrect_ForGivenWeightList_ShouldReturnTrue(double weight)
    {
        //given
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "{index}: FitCalculator.isBMICorrect({0}, 1.72) = False")
    @ValueSource(doubles = {50, 65, 70})
    void isBMICorrect_ForGivenWeightList_ShouldReturnFalse(double weight)
    {
        //given
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "{index}: FitCalculator.isBMICorrect({0},{1}) = False")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void isBMICorrect_ForGivenCsvData_ShouldReturnFalse(Double weight, Double height)
    {
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void findUserWithTheWorstBMI_ForTestUsersList_ShouldReturnWorstBMIUser()
    {
        //given
        List<User> usersList = TestConstants.TEST_USERS_LIST;

        //when
        User worstBMI = FitCalculator.findUserWithTheWorstBMI(usersList);

        //then
        assertEquals(97.3, worstBMI.getWeight());
        assertEquals(1.79, worstBMI.getHeight());
    }

    @Test
    void findUserWithWorstBMI_ForEmptyList_ShouldReturnNull()
    {
        //given
        List<User> usersList = new ArrayList<>();

        //when
        User worstBMI = FitCalculator.findUserWithTheWorstBMI(usersList);

        //then
        assertNull(worstBMI);
    }

    @Test
    void calculateBMIScore_ForTestUsersList_ShouldReturnTestUsersBMIScore()
    {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;

        //when
        double[] userBMIScores = FitCalculator.calculateBMIScore(users);

        //then
        assertArrayEquals(TestConstants.TEST_USERS_BMI_SCORE, userBMIScores);
    }


}