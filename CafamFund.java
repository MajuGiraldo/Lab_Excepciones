package edu.sabana.poob.sabanapayroll;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CafamFund implements IFamilyCompensationFund {

    private static Set<Employee> registeredEmployees = new HashSet<>();

    public CafamFund() {

    }

    /**
     * Permite registrar cualquier empleado.
     *
     * @param employee
     * @return
     */
    @Override
    public boolean registerEmployee(Employee employee) throws FamilyCompensationFundException {
        boolean result = false;

        if(!registeredEmployees.contains(employee)){
            registeredEmployees.add(employee);
            result = true;
        }else if(registeredEmployees.contains(employee)){
            throw new FamilyCompensationFundException(FamilyCompensationFundException.EMPLOYEE_REGISTERED);
        }

        return result;
    }

    @Override
    public boolean deleteEmployee(UUID id) throws FamilyCompensationFundException{
        boolean result = false;
        for(Employee e: registeredEmployees){
            if(id.equals(e.getId())){
                registeredEmployees.remove(e);
                result = true;
            }else if (!registeredEmployees.contains(id)){
                throw new FamilyCompensationFundException(FamilyCompensationFundException.EMPLOYEE_IS_NOT_REGISTERED);
            }
        }


        return result;
    }

    @Override
    public boolean isEmployeeRegistered(UUID id) {
        boolean result = false;
        for(Employee e: registeredEmployees){
            if(id.equals(e.getId())){
                result = true;
            }else {
                result = false;
            }
        }

        return result;
    }

    @Override
    public String printBenefits() {
        return "Los beneficios de esta caja de compensación son:\nBeneficioG...\nBeneficioH...\nBeneficioI...";
    }

}
