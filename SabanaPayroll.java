package edu.sabana.poob.sabanapayroll;

import java.util.*;

public class SabanaPayroll {

    private Collection<Employee> employees;
    private List<Department> departments;
    private Map<String, IFamilyCompensationFund> compensationFunds;

    public SabanaPayroll(List<Employee> employees, List<Department> departments) {
        this.employees = employees;
        this.departments = departments;
    }

    public SabanaPayroll(Collection<Employee> employees) {
        this.employees = employees;
        this.compensationFunds = new HashMap<>();
        this.compensationFunds.put(CafamFund.class.getSimpleName(), new CafamFund());
        this.compensationFunds.put(CompensarFund.class.getSimpleName(), new CompensarFund());
        this.compensationFunds.put(ColsubsidioFund.class.getSimpleName(), new ColsubsidioFund());
    }

    public double calculateEmployeeSalary(){
        double salary = 0;
        for(Employee e: employees){
            if(e instanceof EmployeeByCommission){
                salary = e.calculateSalary();
            }
            if(e instanceof EmployeeBySalary){
                salary = e.calculateSalary();
            }
            if(e instanceof EmployeeByHours){
                salary = e.calculateSalary();
            }
        }
        return salary;
    }


    public double calculateDepartmentSalaries(Department departmentId){
        return departmentId.calculateDepartmentSalaries();
    }


    public double calculateUniversitySalaries(){
        double departmentSalaries;
        double totalSalary = 0;
        for(Department d: departments){
            departmentSalaries = d.calculateDepartmentSalaries();
            totalSalary += departmentSalaries;

        }
        return totalSalary;
    }


    public void printPayroll(){
        String print = "";
        for (Employee e: employees){
            if(e instanceof EmployeeBySalary){
                print = e.toString();
            }
            if(e instanceof EmployeeByHours){
                print = e.toString();
            }
            if(e instanceof EmployeeByCommission){
                print = e.toString();
            }
        }
        System.out.println(print);
    }

    public boolean depositToEmployee(String name, String lastName, BankAccount account, Check check, double amount){
        boolean result = false;
        for(Employee e: employees){
            if(e.getName().equals(name) && e.getLastName().equals(lastName)){
                e.depositToEmployee(account, check, amount);
                result = true;
            }
        }
        return result;
    }

    public boolean assigneFamilyCompensation(String IFamilyCompensationFund, UUID employeeId) {
        boolean result = false;
        for (Employee e: employees){
            if (e.getId().equals(employeeId)){
                e.assignFamilyCompensation(compensationFunds.get(IFamilyCompensationFund));
                result = true;
            }
        }

        return result;
    }

}
