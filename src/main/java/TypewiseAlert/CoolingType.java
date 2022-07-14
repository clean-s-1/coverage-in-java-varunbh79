package TypewiseAlert;

public enum CoolingType {

    PASSIVE_COOLING(0,35),
    HI_ACTIVE_COOLING(0,45),
    MED_ACTIVE_COOLING(0,40);

    private Integer lowTemp;
    private Integer highTemp;

    CoolingType(Integer lowTemp, Integer highTemp) {
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

    public Integer getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(Integer lowTemp) {
        this.lowTemp = lowTemp;
    }

    public Integer getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(Integer highTemp) {
        this.highTemp = highTemp;
    }
}
