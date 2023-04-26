package javastreams._01_first;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ApplyNewSalaryTest {
    @Test
    public void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() throws Exception {
        Employee[] arrayOfEmps = {
                new Employee(1, "Jeff Bezos", 100000.0),
                new Employee(2, "Bill Gates", 200000.0),
                new Employee(3, "Mark Zuckerberg", 300000.0)
        };
        List<Employee> empList = Arrays.asList(arrayOfEmps);
        empList.stream().forEach(e -> e.salaryIncrement(10000.0));

        Assertions.assertEquals(110000.0, empList.stream().filter(e -> e.getSalary() > 10000.0).findFirst().orElseThrow(() -> new Exception("no employee found")).getSalary());

    }
}
