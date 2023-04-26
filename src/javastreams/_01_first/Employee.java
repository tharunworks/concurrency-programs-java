package javastreams._01_first;

public class Employee {

    private Integer id;
    private String name;
    private Double salary;

    public Employee(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void salaryIncrement(double v) {
        this.salary += v;
    }

    public double getSalary() {
        return salary;
    }
}
