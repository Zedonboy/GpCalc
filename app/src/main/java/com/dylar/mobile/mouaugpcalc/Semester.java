package com.dylar.mobile.mouaugpcalc;

import org.greenrobot.greendao.annotation.*;

@Entity(nameInDb = "semesterTable",
indexes = {
        @Index(value = "level")
})
public class Semester {
    @Id(autoincrement = true)
    private Long id;

    @Property
    private Float gp = 5.0f;

    @Property
    private String level = "100L";

    @Property
    private String gpName = "First Class";

    @Property
    private String semester = "Second Semester";

    @Generated(hash = 2079565610)
    public Semester(Long id, Float gp, String level, String gpName,
            String semester) {
        this.id = id;
        this.gp = gp;
        this.level = level;
        this.gpName = gpName;
        this.semester = semester;
    }

    @Generated(hash = 58335877)
    public Semester() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getGp() {
        return this.gp;
    }

    public void setGp(Float gp) {
        this.gp = gp;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGpName() {
        return this.gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
