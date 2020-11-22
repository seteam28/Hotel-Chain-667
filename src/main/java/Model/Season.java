package Model;

public class Season {
    private String seasonName;
    private String startDate;
    private String endDate;
    private double coefficient;

    public Season(){
    }

    public double getCoefficient() {
        return coefficient;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
