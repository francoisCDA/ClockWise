package fr.labom2i.domainEntity;


import java.util.ArrayList;
import java.util.List;

public class Employee extends User {

    private String firstname;

    private String lastname;

    private Integer minuntesPerWeek;

    private List<TimeStamp> timeStamps;

    public Employee() {
        timeStamps = new ArrayList<TimeStamp>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getMinuntesPerWeek() {
        return minuntesPerWeek;
    }

    public void setMinuntesPerWeek(Integer minuntesPerWeek) {
        this.minuntesPerWeek = minuntesPerWeek;
    }

    public List<TimeStamp> getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(List<TimeStamp> timeStamps) {
        this.timeStamps = timeStamps;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private RoleUser role;
        private String firstname;
        private String lastname;
        private Integer minuntesPerWeek;
        private List<TimeStamp> timeStamps;

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setRole(RoleUser role) {
            this.role = role;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setMinuntesPerWeek(Integer minuntesPerWeek) {
            this.minuntesPerWeek = minuntesPerWeek;
            return this;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTimeStamps(List<TimeStamp> timeStamps) {
            this.timeStamps = timeStamps;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setRole(role);
            employee.setFirstname(firstname);
            employee.setLastname(lastname);
            employee.setMinuntesPerWeek(minuntesPerWeek);
            employee.setTimeStamps(timeStamps);
            return employee;
        }
    }
}

