package com.creativosoft.examify.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "arrangement_records")
public class ArrangementRecord {
    // Class attributes.
    @Id
    @Column(name = "serial_number")
    private int serialNumber;
    @Column(name = "student_registration_number")
    private int studentRegistrationNumber;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "batch")
    private String batch;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "date")
    private String date;
    @Column(name = "day")
    private String day;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "exam_location")
    private String examLocation;

    // Constructors.
    public ArrangementRecord() {

    }

    public ArrangementRecord(int serialNumber, int studentRegistrationNumber, String studentName, String courseName, String batch,
                      String courseCode, String date, String day, LocalTime startTime,
                      LocalTime endTime, String examLocation) {
        this.serialNumber = serialNumber;
        this.studentRegistrationNumber = studentRegistrationNumber;
        this.studentName = studentName;
        this.courseName = courseName;
        this.batch = batch;
        this.courseCode = courseCode;
        this.date = date;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.examLocation = examLocation;
    }

    // Getters.
    public int getSerialNumber() {
        return serialNumber;
    }

    public int getStudentRegistrationNumber() {
        return studentRegistrationNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getBatch() {
        return batch;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getExamLocation() {
        return examLocation;
    }

    // Setters.
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setStudentRegistrationNumber(int studentRegistrationNumber) {
        this.studentRegistrationNumber = studentRegistrationNumber;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setExamLocation(String examLocation) {
        this.examLocation = examLocation;
    }
}