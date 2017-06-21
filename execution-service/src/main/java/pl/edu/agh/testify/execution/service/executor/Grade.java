package pl.edu.agh.testify.execution.service.executor;

import java.util.Arrays;

public enum Grade {
    _2_0(2.0), _3_0(3.0), _3_5(3.5), _4_0(4.0), _4_5(4.5), _5_0(5.0);

    private double val;

    Grade(double val) {
        this.val = val;
    }

    public static Grade fromDouble(double grade) {
        if (grade <= 2.0) return _2_0;
        if (grade >= 5.0) return _5_0;
        return Arrays.stream(Grade.values())
                .filter(g -> g.val == grade)
                .findFirst().orElse(_2_0);
    }
}
