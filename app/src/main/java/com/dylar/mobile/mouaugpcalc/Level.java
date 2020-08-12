package com.dylar.mobile.mouaugpcalc;

import org.greenrobot.greendao.annotation.*;

@Entity(nameInDb = "levelTable")
public class Level {
    @Id(autoincrement = true)
    private Long id;

    @Property
    private Float gp = 5.0f;

    @Property
    @Unique
    private String level;

    @Property
    private String gpName = "First Class";

    @Generated(hash = 3566498)
    public Level(Long id, Float gp, String level, String gpName) {
        this.id = id;
        this.gp = gp;
        this.level = level;
        this.gpName = gpName;
    }

    @Generated(hash = 723561372)
    public Level() {
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
}
