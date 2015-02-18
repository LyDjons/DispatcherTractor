package com;

import com.config.Config;
import com.disp.Disp;
import com.disp.disp.control.DispControl;
import com.disp.disp.control.loadExcell.Report;
import com.disp.disp.control.loadExcell.TransportAction;
import com.disp.disp.control.saveExcell.SaveExclell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by disp.chimc on 23.12.14.
 */
public class Start {
        public static void main(String []args) throws IOException {
            Disp disp = new DispControl();
            disp.load_report("Document.xlsx");
            disp.load_config("config/config.xlsx");
            disp.load_departmetn("config/config.xlsx");

for(Config c : disp.getConfigs())
    System.out.println(c.getTracker() +"    "+c.getNum_agreg() );



        }
}
