package edu.sabana.poob.sabanapayroll;

public class FamilyCompensationFundException extends Exception{

    public static final String EMPLOYEE_NOT_ALLOWED = "This employee type is not allowed to be registered";
    public static final String EMPLOYEE_REGISTERED = "This employee is currently registered";
    public static final String EMPLOYEE_IS_NOT_REGISTERED = "This employee is not registered";

    public FamilyCompensationFundException(String message) {
        super(message);
    }
}
