package br.mack.ps2.dao;

import br.mack.ps2.api.Covid19;

import java.util.List;

public interface Covid19Dao {
boolean create(Covid19 covid19);
List<Covid19> read();
boolean update(Covid19 covid19);
boolean delete(Covid19 covid19);


}
