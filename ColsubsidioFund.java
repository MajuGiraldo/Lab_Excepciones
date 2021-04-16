package edu.sabana.poob.sabanapayroll;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ColsubsidioFund implements IFamilyCompensationFund {

    private static Map<UUID, Employee> registeredEmployees = new HashMap<>();

    public ColsubsidioFund() {

    }


    /**
     * No permite registrar empleados de tipo EmployeeByCommission.
     *
     * @param employee
     * @return
     */
    @Override
    public boolean registerEmployee(Employee employee) throws FamilyCompensationFundException {
        boolean result = false;

        if(employee instanceof EmployeeByHours || employee instanceof EmployeeBySalary){
            if(!registeredEmployees.containsKey(employee.getId())){
                registeredEmployees.put(employee.getId(), employee);
                result = true;
            }else if(registeredEmployees.containsKey(employee.getId())){
                throw new FamilyCompensationFundException(FamilyCompensationFundException.EMPLOYEE_REGISTERED);
            }

        }if(employee instanceof EmployeeByCommission){
            throw new FamilyCompensationFundException(FamilyCompensationFundException.EMPLOYEE_NOT_ALLOWED);

        }

        return result;
    }


    @Override
    public boolean deleteEmployee(UUID id) throws FamilyCompensationFundException {
        boolean result = false;
        if(registeredEmployees.containsKey(id)){
            registeredEmployees.remove(id);
            result = true;

        }else if(!registeredEmployees.containsKey(id)){
            throw new FamilyCompensationFundException(FamilyCompensationFundException.EMPLOYEE_IS_NOT_REGISTERED);

        }

        return result;
    }

    @Override
    public boolean isEmployeeRegistered(UUID id) {
        boolean result = false;
        if(registeredEmployees.containsKey(id)){
            result = true;
        }else {
            result = false;
        }

        return result;
    }

    @Override
    public String printBenefits() {
        return "Los beneficios de esta caja de compensación son:\nBeneficioA...\nBeneficioB...\nBeneficioC...";
    }

}
