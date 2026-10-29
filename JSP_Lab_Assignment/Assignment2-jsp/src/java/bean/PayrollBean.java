/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 2: PayrollBean - looks up an employee and calculates salary,
 * deductions and tax (Model). Data is a static dummy list.
 */
package bean;

import java.util.*;

public class PayrollBean {
    // Dummy employee table: id -> {name, designation, department, basic salary}
    private static final Map<String, String[]> EMPLOYEES = new HashMap<>();
    static {
        EMPLOYEES.put("E101", new String[]{"Rahul Sharma", "Software Developer", "IT", "40000"});
        EMPLOYEES.put("E102", new String[]{"Priya Das", "HR Executive", "HR", "30000"});
        EMPLOYEES.put("E103", new String[]{"Amit Roy", "Project Manager", "IT", "80000"});
        EMPLOYEES.put("E104", new String[]{"Neha Singh", "Intern", "Marketing", "15000"});
    }

    private String empId = "";
    private String name = "";
    private String designation = "";
    private String department = "";
    private double basic = 0;
    private boolean found = false;

    /** Setting the ID loads the employee details. */
    public void setEmpId(String id) {
        empId = (id == null) ? "" : id.trim().toUpperCase();
        String[] row = EMPLOYEES.get(empId);
        if (row != null) {
            found = true;
            name = row[0];
            designation = row[1];
            department = row[2];
            basic = Double.parseDouble(row[3]);
        } else {
            found = false;
        }
    }

    public String getEmpId()       { return empId; }
    public String getName()        { return name; }
    public String getDesignation() { return designation; }
    public String getDepartment()  { return department; }
    public double getBasic()       { return basic; }
    public boolean isFound()       { return found; }

    // ---- Earnings ----
    public double getHra()   { return basic * 0.20; }   // 20% of basic
    public double getDa()    { return basic * 0.10; }   // 10% of basic
    public double getGross() { return basic + getHra() + getDa(); }

    // ---- Tax bracket (on monthly gross) ----
    public int getTaxRate() {
        double g = getGross();
        if (g <= 25000) return 0;
        if (g <= 50000) return 5;
        if (g <= 100000) return 10;
        return 20;
    }
    public double getIncomeTax() { return getGross() * getTaxRate() / 100; }

    // ---- Deductions ----
    public double getPf()              { return basic * 0.12; }  // 12% of basic
    public double getProfessionalTax() { return 200; }
    public double getTotalDeductions() { return getPf() + getProfessionalTax() + getIncomeTax(); }

    public double getNetSalary() { return getGross() - getTotalDeductions(); }

    // ---- Rows for JSTL tables: {label, amount} ----
    public List<Object[]> getEarnings() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Basic Salary", basic});
        list.add(new Object[]{"HRA (20%)", getHra()});
        list.add(new Object[]{"DA (10%)", getDa()});
        return list;
    }
    public List<Object[]> getDeductions() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Provident Fund (12%)", getPf()});
        list.add(new Object[]{"Professional Tax", getProfessionalTax()});
        list.add(new Object[]{"Income Tax", getIncomeTax()});
        return list;
    }
}
