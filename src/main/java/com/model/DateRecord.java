package com.model;

/**
 * Created by Andrew on 2016/3/25.
 */
public class DateRecord implements BModel{

    private Integer id;

    private Integer bro_id;
    private Integer sis_id;
    private Integer pair_up_id;
    private Integer weeks;
    private Integer years;

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBro_id() {
        return bro_id;
    }

    public void setBro_id(Integer bro_id) {
        this.bro_id = bro_id;
    }

    public Integer getSis_id() {
        return sis_id;
    }

    public void setSis_id(Integer sis_id) {
        this.sis_id = sis_id;
    }

    public Integer getPair_up_id() {
        return pair_up_id;
    }

    public void setPair_up_id(Integer pair_up_id) {
        this.pair_up_id = pair_up_id;
    }
}
