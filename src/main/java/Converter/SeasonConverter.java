package Converter;

import Controller.SeasonController;
import Model.Season;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeasonConverter {
    ResultSet resultSet;

    public SeasonConverter(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Season singleSeason() throws Exception {
        SeasonController controller = new SeasonController();
        Season season = new Season();
        season.setSeasonName(resultSet.getString("seasonName"));
        season.setStartDate(resultSet.getString("startDate"));
        season.setEndDate(resultSet.getString("endDate"));
        season.setCoefficient(resultSet.getInt("coefficient"));
        return season;
    }

    public ArrayList<Season> seasonArray() throws Exception {
        ArrayList<Season> seasons = new ArrayList<>();
        while(resultSet.next()) {
            seasons.add(this.singleSeason());
        }
        return seasons;
    }
}
