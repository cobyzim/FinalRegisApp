package zimmerman.regis.regisapplication.domain;

import java.io.Serializable;

public class Account implements Serializable {

    private int id;
    private String studentOrFaculty;
    private String studentIDOrFacultyName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentOrFaculty() {
        //try to get text from selected radio button
        return studentOrFaculty;
    }

    public void setStudentOrFaculty(String studentOrFaculty) {
        this.studentOrFaculty = studentOrFaculty;
    }

    public String getStudentIDOrFacultyName() {
        return studentIDOrFacultyName;
    }

    public void setStudentIDOrFacultyName(String studentIDOrFacultyName) {
        this.studentIDOrFacultyName = studentIDOrFacultyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
