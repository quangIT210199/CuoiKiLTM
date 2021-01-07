/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_913;

import java.io.Serializable;

class Student implements Serializable {

    private static final long serialVersionUID = 20151107;

    private int id;
    private String code;
    private float gpa;
    private String gpaLetter;

    public Student(int id, String codeString, float gpa, String gpaLetter) {
        this.id = id;
        this.code = codeString;
        this.gpa = gpa;
        this.gpaLetter = gpaLetter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String codeString) {
        this.code = codeString;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getGpaLetter() {
        return gpaLetter;
    }

    public void setGpaLetter(String gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    @Override
    public String toString() {
        return "Student : " + " Id: " + id + ", Code: " + code + ", Gpa: " + gpa + ", GpaLetter: " + gpaLetter;
    }

}
