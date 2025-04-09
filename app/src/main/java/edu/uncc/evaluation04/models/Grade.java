package edu.uncc.evaluation04.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grade")
public class Grade {

    @PrimaryKey(autoGenerate = true)
    public int gid;
    public String semester;
    public String courseNumber;
    public String courseName;
    public double courseHours;
    public String letterGrade;


    public Grade() {
    }

    public Grade(String semester, String courseNumber, String courseName, double courseHours, String letterGrade) {
        this.semester = semester;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseHours = courseHours;
        this.letterGrade = letterGrade;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(double courseHours) {
        this.courseHours = courseHours;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gid=" + gid +
                ", semester='" + semester + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseHours=" + courseHours +
                ", letterGrade='" + letterGrade + '\'' +
                '}';
    }
}
