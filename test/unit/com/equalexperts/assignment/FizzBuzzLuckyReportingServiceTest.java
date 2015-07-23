package com.equalexperts.assignment;


import com.equalexperts.assignment.FizzBuzzLuckyReportingService;
import com.equalexperts.assignment.IFizzBuzzLuckyReportingService;
import com.equalexperts.assignment.validators.RangeValidation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class FizzBuzzLuckyReportingServiceTest {

    private IFizzBuzzLuckyReportingService fizzBuzz;

    @Mock
    private RangeValidation rangeValidation;

    @Before
    public void init() {
        fizzBuzz = new FizzBuzzLuckyReportingService(rangeValidation);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForInvalidRanges() {
        doThrow(IllegalArgumentException.class).when(rangeValidation).validateRange(anyInt(), anyInt());
        fizzBuzz.execute(0, 2);
    }

    @Test
    public void shouldReturnNumberStat() {
        doNothing().when(rangeValidation).validateRange(anyInt(), anyInt());
        assertEquals(fizzBuzz.execute(1, 2), "integer:2");
    }

    @Test
    public void shouldReturnAllStats() {
        doNothing().when(rangeValidation).validateRange(anyInt(), anyInt());
        assertEquals(fizzBuzz.execute(1, 16), getExpectedData(2, 8, 1, 3, 2));
    }

    private String getExpectedData(int luckyCount, int intCount, int fizzBuzzCount, int fizzCount, int buzzCount) {
        return "lucky:" + luckyCount + "\n" +
               "integer:" + intCount + "\n" +
               "fizzbuzz:" + fizzBuzzCount + "\n" +
               "fizz:" + fizzCount + "\n" +
               "buzz:" + buzzCount;
    }


}
