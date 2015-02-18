package com.config;

import com.disp.Disp;
import com.disp.disp.control.DispControl;
import com.disp.disp.control.loadExcell.Report;
import com.disp.disp.control.loadExcell.TransportAction;
import com.disp.disp.control.saveExcell.TransportExcell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by disp.chimc on 26.11.14.
 */
public class Test {


    public static void main(String []s) throws IOException {
        Date d = new Date();
        if(d.getYear()!=115 && d.getMonth()>4) {
            System.out.println("No Well done!");
        }


    }
}
