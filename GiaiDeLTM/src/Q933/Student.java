/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q933;

import java.io.Serializable;

/**
 *
 * @author HoangHung
 */
public class Student implements Serializable{
    private static final long serialVersionUID=20161107;
    private int id;
    private String code;
    private String name;
    private String email;

    public Student() {
    }

    public Student(int id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  this.id+" "+this.code+" "+this.name+" "+this.email;
    }
    
}
