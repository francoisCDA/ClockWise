package fr.labom2i.entity;

public class User {

    protected Long id;

    protected String email;

    protected String password;

    protected String role;

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static class Builder{
        private Long id;
        private String email;
        private String password;
        private String role;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder role(String role){
            this.role = role;
            return this;
        }

        public User build(){
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            return user;
        }
    }

}
