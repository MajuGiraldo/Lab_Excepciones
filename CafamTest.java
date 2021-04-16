package edu.sabana.poob.sabanapayroll;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class CafamTest {

    private static Faker faker;

    private static List<Employee> employees;
    private static Department department;

    private static EmployeeBySalary employeeBySalary;
    private static EmployeeBySalary employeeBySalary2;
    private static EmployeeByHours employeeByHours;
    private static EmployeeByCommission employeeByCommission;
    private static EmployeeByCommission employeeByCommission2;
    private static EmployeeByHours employeeByHours2;

    private static IFamilyCompensationFund cafamFund;

    @BeforeAll
    public static void setUp() {
        faker = new Faker(new Locale("en-US"));

        department = new Department("Engineering");

        employeeBySalary = new EmployeeBySalary(faker.name().firstName(), faker.name().lastName(), department, 1000000);
        employeeBySalary2 = new EmployeeBySalary(faker.name().firstName(), faker.name().lastName(), department, 1000000);
        employeeByHours = new EmployeeByHours(faker.name().firstName(), faker.name().lastName(), department, 40);
        employeeByHours2 = new EmployeeByHours(faker.name().firstName(), faker.name().lastName(), department, 50);
        employeeByCommission = new EmployeeByCommission(faker.name().firstName(), faker.name().lastName(), department, 100);
        employeeByCommission2 = new EmployeeByCommission(faker.name().firstName(), faker.name().lastName(), department, 120);

        employees = new ArrayList<>();
        employees.add(employeeBySalary);
        employees.add(employeeByHours);
        employees.add(employeeByCommission);
        employees.add(employeeByCommission2);

        cafamFund = new CafamFund();
    }

    @Test
    @DisplayName("GIVEN a employee by salary WHEN try to register THEN success")
    public void shouldRegisterEmployee() throws FamilyCompensationFundException {

        assertTrue(cafamFund.registerEmployee(employeeByHours2));
    }

    @Test
    @DisplayName("GIVEN a employee by commission WHEN try to register THEN success")
    public void shouldRegisterEmployeeWhenByCommission() throws FamilyCompensationFundException {

        assertTrue(cafamFund.registerEmployee(employeeByCommission));
    }

    @Test
    @DisplayName("GIVEN a employee by salary registered WHEN try to register again THEN fails")
    public void shouldNotRegisterEmployeeWhenDuplicated() throws FamilyCompensationFundException {

        assertTrue(cafamFund.registerEmployee(employeeBySalary));

        Exception e = assertThrows(FamilyCompensationFundException.class, () -> cafamFund.registerEmployee(employeeBySalary));
        assertEquals(FamilyCompensationFundException.EMPLOYEE_REGISTERED, e.getMessage());
    }

    @Test
    @DisplayName("GIVEN a employee by salary registered WHEN try to delete THEN success")
    public void shouldDeleteEmployee() throws FamilyCompensationFundException {

        assertTrue(cafamFund.registerEmployee(employeeBySalary));
        assertTrue(cafamFund.deleteEmployee(employeeBySalary.getId()));
    }

    @Test
    @DisplayName("GIVEN a employee by salary not registered WHEN try to delete THEN fails")
    public void shouldNotDeleteEmployee() {

        Exception e = assertThrows(FamilyCompensationFundException.class, () -> cafamFund.deleteEmployee(employeeBySalary.getId()));
        assertEquals(FamilyCompensationFundException.EMPLOYEE_IS_NOT_REGISTERED, e.getMessage());
    }

    @Test
    @DisplayName("GIVEN a employee by salary registered WHEN try to validate is registered THEN success")
    public void shouldValidateEmployeeIsRegistered() throws FamilyCompensationFundException {

        assertTrue(cafamFund.registerEmployee(employeeBySalary2));
        assertFalse(cafamFund.isEmployeeRegistered(employeeBySalary2.getId()));
    }

    @Test
    @DisplayName("GIVEN a employee by salary not registered WHEN try to validate is registered THEN fails")
    public void shouldNotValidateEmployeeIsRegistered() {

        assertFalse(cafamFund.isEmployeeRegistered(employeeBySalary.getId()));
    }

    @Test
    public void shouldPrintBenefits() {

        String benefits = cafamFund.printBenefits();
        assertNotNull(benefits);
    }

}
