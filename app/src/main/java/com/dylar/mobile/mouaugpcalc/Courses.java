package com.dylar.mobile.mouaugpcalc;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Courses {
    @Property
    @Index
    private String level;

    @Property
    @Index
    private String semester;

    @Property
    private String subject;

    @Property
    private String grade;

    @Property
    private int unitLoad;

    @Generated(hash = 8298181)
    public Courses(String level, String semester, String subject, String grade,
            int unitLoad) {
        this.level = level;
        this.semester = semester;
        this.subject = subject;
        this.grade = grade;
        this.unitLoad = unitLoad;
    }

    @Generated(hash = 511247874)
    public Courses() {
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getUnitLoad() {
        return this.unitLoad;
    }

    public void setUnitLoad(int unitLoad) {
        this.unitLoad = unitLoad;
    }
}
