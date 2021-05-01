package com.inkostilaton.dbpractice;

public class EmpModel {

    private int emp_id;
    String name;
    String title;
    String department;
    String office;
    String superior;
    String startDate;
    String endDate;

    public EmpModel(int emp_id, String name, String title, String department, String office, String superior, String startDate, String endDate) {
        this.emp_id = emp_id;
        this.name = name;
        this.title = title;
        this.department = department;
        this.office = office;
        this.superior = superior;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public String getOffice() {
        return office;
    }

    public String getSuperior() {
        return superior;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
