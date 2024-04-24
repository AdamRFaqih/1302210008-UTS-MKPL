package lib;

public class TaxFunction {

	private static final int MONTHS_PER_YEAR = 12;
	private static final int MAX_CHILDREN_DEDUCTION = 3;
	private static final int SINGLE_TAX_DEDUCTION = 54_000_000;
	private static final int MARRIED_TAX_DEDUCTION = 4_500_000;
	private static final int CHILD_TAX_DEDUCTION = 1_500_000;
	private static final double TAX_RATE = 0.05;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		inputValidation(monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible, numberOfChildren);
		int annualIncome = getAnnualIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
		int taxable = getTaxable(annualIncome, deductible, isMarried, numberOfChildren);
		return taxable > 0 ? (int) (taxable * TAX_RATE) : 0;
	}

	public static void inputValidation(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, int numberOfChildren){
		if (monthlySalary < 0 || otherMonthlyIncome < 0 || numberOfMonthWorking < 0 || deductible < 0 || numberOfChildren < 0) {
			System.err.println("Input must be positive number");
		}
		if (numberOfMonthWorking > MONTHS_PER_YEAR) {
			System.err.println("More than 12 month working per year");
		}
	}

	public static int getAnnualIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
		return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
	}

	private static int getTaxable(int annualIncome, int deductible, boolean isMarried, int numberOfChildren) {
		int taxable = annualIncome - deductible;
		if (isMarried) {
			taxable -= MARRIED_TAX_DEDUCTION;
		}else {
			taxable -= SINGLE_TAX_DEDUCTION;
		}
		int childDeduction = Math.min(numberOfChildren, MAX_CHILDREN_DEDUCTION);
		taxable -= childDeduction * CHILD_TAX_DEDUCTION;
		return taxable;
	}
	
}
