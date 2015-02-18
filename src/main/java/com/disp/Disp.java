package com.disp;

import com.config.Config;
import com.disp.disp.control.loadExcell.Report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by disp.chimc on 28.10.14.
 */
public interface Disp {

    //загрузка полного отчета в ArrayList
    public ArrayList<Report> load_report(String path);
    //загрузка конфигурации
    public ArrayList<Config> load_config(String path) throws IOException;
    //сохраням в отчет
    public void save_report(ArrayList<Report> reports, String path, ArrayList<Config> configs);
    //загружаем зоны отделений
    public void load_departmetn(String path) throws IOException;

    public void loadReport(String path) throws IOException;

    public ArrayList<Report> getReport();

    public ArrayList<Config> getConfigs();

    public Map<String,String> getDepartMap();

}
