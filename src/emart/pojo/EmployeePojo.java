
package emart.pojo;

public class EmployeePojo {

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeePojo{" + "empid=" + empid + ", empname=" + empname + ", job=" + job + ", salary=" + salary + '}';
    }

    
    private String empid;
    private String empname;
    private String job;
    private double salary;
}
