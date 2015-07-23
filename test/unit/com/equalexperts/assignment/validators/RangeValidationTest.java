package com.equalexperts.assignment.validators;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RangeValidationTest {

    private RangeValidation rangeValidation;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void init() {
        rangeValidation = new RangeValidation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenStartRangeIsLessThanZeroOrEqualToZero() {
        rangeValidation.validateRange(-1, 10);
        rangeValidation.validateRange(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenEndRangeIsLessThanOrEqualToStartRange() {
        rangeValidation.validateRange(10, 10);
        rangeValidation.validateRange(10, 1);
    }

    @Test
    public void shouldNotThrowIllegalArgumentExceptionForValidRanges() {
        rangeValidation.validateRange(1, 10);
        rangeValidation.validateRange(10, 20);
    }
}
