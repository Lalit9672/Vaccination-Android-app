package com.example.gaming;

public class POJO2 {
  public  String id;

  public String Patient_Age;
  public String Patient_dose;
  public String intro;
  public String timePeriod;
  public String precaution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getPrecaution() {
        return precaution;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
    }
    public POJO2()
    {

    }
    public POJO2(String id, String AGE, String dose, String intro, String timePeriod, String precaution)
    {
        this.id=id;
        this.Patient_Age=AGE;
        this.Patient_dose=dose;
        this.intro=intro;
        this.timePeriod=timePeriod;
        this.precaution=precaution;
    }
}
