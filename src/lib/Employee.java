package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private Gender gender;
	private Family spouse;
	private List<Family> childrens;
	private LocalDate joinedDate;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private enum Gender {
		MALE,
		FEMALE;
	}
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinedDate, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinedDate = joinedDate;
		this.isForeigner = isForeigner;
		this.gender = gender;
		this.childrens = new LinkedList<>();
	}
	
	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1:
				monthlySalary = 3000000;
				break;
			case 2:
				monthlySalary = 5000000;
				break;
			case 3:
				monthlySalary = 7000000;
				break;
		}
		if (isForeigner) {
			monthlySalary = (int) (monthlySalary * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouse = new Family(spouseName, spouseIdNumber);
	}
	
	public void addChild(String childName, String childIdNumber) {
		this.childrens.add(new Family(childName, childIdNumber));
	}
	
	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();
		if (date.getYear() == joinedDate.getYear()) {
			monthWorkingInYear = date.getMonthValue() - joinedDate.getMonthValue();
		}else {
			monthWorkingInYear = 12;
		}
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouse.getId().equals(""), childrens.size());
	}
}
