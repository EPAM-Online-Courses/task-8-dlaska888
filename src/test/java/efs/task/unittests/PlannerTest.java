package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class PlannerTest
{
    private Planner planner;

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void calculateDailyCaloriesDemand_ForAllActivityLevels_ReturnCorrectCalories(ActivityLevel activityLevel)
    {
        // given
        planner = new Planner();
        User user = TestConstants.TEST_USER;

        //when
        Integer calories = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        assertEquals(calories, TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel));

    }

    @Test
    void calculateDailyIntake_ForTestUser_ReturnCorrectDailyIntake()
    {
        // given
        planner = new Planner();
        User user = TestConstants.TEST_USER;

        //when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);

        //then
        assertEquals(dailyIntake.getCalories(), TestConstants.TEST_USER_DAILY_INTAKE.getCalories());
        assertEquals(dailyIntake.getCarbohydrate(), TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate());
        assertEquals(dailyIntake.getFat(), TestConstants.TEST_USER_DAILY_INTAKE.getFat());
        assertEquals(dailyIntake.getProtein(), TestConstants.TEST_USER_DAILY_INTAKE.getProtein());
    }

}
