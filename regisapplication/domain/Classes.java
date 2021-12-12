package zimmerman.regis.regisapplication.domain;

import java.io.Serializable;

public class Classes implements Serializable {

    private int id;

    private String studentIdOrFacultyName, courseNameTitle, meetingInfo, location, term, startDate;

    public Classes(int id, String studentIdOrFacultyName) {
        this.id = id;
        this.studentIdOrFacultyName = studentIdOrFacultyName;
    }

    public Classes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentIdOrFacultyName() {
        return studentIdOrFacultyName;
    }

    public void setStudentIdOrFacultyName(String studentIdOrFacultyName) {
        this.studentIdOrFacultyName = studentIdOrFacultyName;
    }

    public String getCourseNameTitle() {
        return courseNameTitle;
    }

    public void setCourseNameTitle(String courseNameTitle) {
        this.courseNameTitle = courseNameTitle;
    }

    public String getMeetingInfo() {
        return meetingInfo;
    }

    public void setMeetingInfo(String meetingInfo) {
        this.meetingInfo = meetingInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return courseNameTitle;
    }
}
