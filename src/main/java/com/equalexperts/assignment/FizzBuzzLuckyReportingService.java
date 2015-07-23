package com.equalexperts.assignment;


import com.equalexperts.assignment.validators.RangeValidation;

import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.of;

public class FizzBuzzLuckyReportingService implements IFizzBuzzLuckyReportingService {

    private final RangeValidation rangeValidation;
    private static final Predicate<Integer> DIV_BY_3 = arg -> (arg % 3) == 0;
    private static final Predicate<Integer> DIV_BY_5 = arg -> (arg % 5) == 0;
    private static final Predicate<Integer> CONTAINS_3 = arg -> (valueOf(arg).contains("3"));

    public FizzBuzzLuckyReportingService(RangeValidation rangeValidation) {
        this.rangeValidation = rangeValidation;
    }

    public String execute(int startRange, int endRange) {
        rangeValidation.validateRange(startRange, endRange);
        String reduced = reduceToFizzBuzz(startRange, endRange);
        Map<String, Long> stats = getStat(reduced);
        return transform(stats);
    }

    private String reduceToFizzBuzz(int startRange, int endRange) {
        StringJoiner joiner = new StringJoiner(" ");
        rangeClosed(startRange, endRange)
                .mapToObj(this::transform)
                .forEach(joiner::add);

        return joiner.toString();
    }

    private String transform(int number) {
        if (CONTAINS_3.test(number)) return "lucky";
        if (DIV_BY_3.and(DIV_BY_5).test(number)) return "fizzbuzz";
        if (DIV_BY_3.test(number)) return "fizz";
        if (DIV_BY_5.test(number)) return "buzz";
        return valueOf(number);
    }

    private  String transform(Map<String,Long> stats) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        stats.forEach((k,v) -> joiner.add(k + ":" + v));
        return joiner.toString();
    }


    private Map<String, Long> getStat(String line) {
        return of(line.split(" ")).map(getStatName()).collect(groupingBy(arg -> arg, counting()));
    }

    private Function<String,String> getStatName() {
        return arg -> arg.matches("\\d+")? "integer":arg;
    }
}
