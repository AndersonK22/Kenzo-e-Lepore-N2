package br.mack.ps2.dao;

import br.mack.ps2.api.Covid19;
import br.mack.ps2.api.Result;
import br.mack.ps2.api.Trends;

import java.text.SimpleDateFormat;
import java.util.List;

public class TrendsDao {
    Trends dataBase;
    Covid19Dao covid19DAO;

    public TrendsDao(Covid19Dao covid19DAO) {
        this.covid19DAO = covid19DAO;
        this.dataBase = new Trends();
        read();
    }

    public void read() {

        List<Covid19> coronas = covid19DAO.read();
        List<Covid19> coronas2 = coronas;
        for (int i = 0; i < coronas.size(); i++) {
            SimpleDateFormat data= new SimpleDateFormat("yyyy-MM-dd");

            Covid19 covid19 = coronas.get(i);
            Result result = new Result();

            result.setDate(data.format(covid19.getDate()));
            result.setValue(covid19.getValue());
            this.dataBase.getResults().add(result);
        }


    }

    public Trends getAllTrends() {
        return this.dataBase;
    }

}
