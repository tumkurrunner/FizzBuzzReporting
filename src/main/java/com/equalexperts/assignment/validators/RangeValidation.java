package com.equalexperts.assignment.validators;

public class RangeValidation {

    public void validateRange(int startRange, int endRange) {
        if(startRange <= 0) {
            throw new IllegalArgumentException("Start range should be greater than 0");
        }

        if(endRange <= startRange) {
            throw new IllegalArgumentException("End range should be greater than start range");
        }
    }
}
