package com.swing;

/**
 * Created by disp.chimc on 02.12.14.
 */



import com.disp.Disp;
import com.disp.disp.control.DispControl;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


public class MenuDemo {
    static JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";

    public JMenuBar createMenuBar() {
        final JMenuBar menuBar;
        JMenu menuFile;
        JMenu menuSetting;
        JMenuItem loadItem;
        JMenu saveMenu;
        JMenu menuInfo;
        JMenuItem saveItemPath;
        JMenuItem saveItemNew;
        JMenuItem show_config;
        JMenuItem autor;
        JMenuItem dammies;
        JMenuItem about;
        final Disp disp = new DispControl();

        //Create the menuFile bar.
        menuBar = new JMenuBar();
        menuFile = new JMenu("Файл");
        menuSetting = new JMenu("Настройки");
        menuInfo = new JMenu(" WTF???");

        menuBar.add(menuFile);
        menuBar.add(menuSetting);
        menuBar.add(menuInfo);

        //a group of JMenuItems
        loadItem = new JMenuItem("Загрузить ДУТ с тракторами");
        saveMenu = new JMenu("Создать отчет");
        saveItemPath = new JMenuItem("В существующий файл");
        saveItemNew = new JMenuItem("В новый файл");
        saveMenu.add(saveItemPath);
        saveMenu.add(saveItemNew);
        show_config = new JMenuItem("Файл конфгурации");
        autor = new JMenuItem("Об авторе");
        dammies = new JMenuItem("Для чайников");
        about = new JMenuItem("О программе");
        dammies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parent = new JFrame();
                String multiLineMsg[] = { "1.  Выгрузить ДУТ с программы TrackControl v 1.47. В дут должны "+newline+
                        "входить только те треккера, которые необходимы для отчета."+newline+
                        "2.  Настроить файл конфигурации \"Настройки/Файл конфигурации\". "+newline+
                        "Для тракторов использовать лист \"tractor\", \"depart\"    "+newline+
                        "3.  Загрузить ДУТ в программу с помощью команды \"Файл/Загрузить ДУТ\" "+newline+
                        "4.  Создать отчет с помощью команды \"Файл/Создать отчет\". "+newline+
                        "Отчет можно выгрузить как в существующий файл, так и в новый,"+newline+
                        "указав название файла и место положения. "
                } ;
                JOptionPane.showMessageDialog(parent,
                        multiLineMsg,"Инструкция",JOptionPane.INFORMATION_MESSAGE,null);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parent = new JFrame();
                String multiLineMsg[] = { "   Ленивый  диспетчер  -  это отличный  выбор для  облегчения жизни "+newline+
                        "диспетчеру. Ведь с утреца очень хочется бахнуть кофейку, поделиться  "+newline+
                        "с колеггами вчерашними приключениями, или  же  вздремнуть полчасика "+newline+
                        "лиццом на клавиатуре. Но важность сдачи отчетов не дает расслабится. "+newline+
                        "По  этому спецом для Вас  и  была  создана  эта  чудо-программулина, "+newline+
                        "способна сократить рабочий процесс, создавая \"качествынные\" отчеты."

                        , "" } ;
                JOptionPane.showMessageDialog(parent,
                        multiLineMsg,"О программе",JOptionPane.INFORMATION_MESSAGE,null);
            }
        });
        autor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parent = new JFrame();

                String multiLineMsg[] = { "Разработчик: "
                        , "   Диспетчер ЧИМК: "+newline
                        ,"    Васюк Евгений Александрович",newline
                        , "контакты:"
                        , "   email: znahar917@gmail.com"
                        , "   skype: znahar69"
                        , "   phone: +380634873018",newline
                        , "благодарить сюда:"
                        , "   WebMoney: U424521704609 "
                        , "   5168757200215517" } ;

                Object[] options = {"Отблагодарил",
                        "Я бедняк"};
                int n = JOptionPane.showOptionDialog(parent,
                        multiLineMsg,
                        "Info",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,     //do not use a custom Icon
                        options,  //the titles of buttons
                        options[0]); //default button title

            }
        });
        show_config.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Desktop desktop = null;

                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File("config/config.xlsx"));


                } catch (Exception e3) {
                    output.append(new Date() +" -> Не найден путь. Ищи вручную"+newline);
                }
            }

        });
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String path_load=getPathToFile("Загрузить");

                if(path_load==null) return;

                Thread thread = new Thread(){
                    public void  run(){
                        output.append(new Date() + " -> Операция  Загрузки ДУТ..." + newline);

                        try {
                            disp.loadReport(path_load);
                            //for(Report re: disp.getReport())
                            //  output.append("  "+re.getTracker()+"  "+ re.getTransport());
                        } catch (Exception e) {
                            output.append(new Date() +" -> Не удаллсь загрузить файл  "+path_load+newline);
                            return;
                        }

                        output.append(new Date() +" -> ДУТ успешно загружен!"+newline);
                        output.append(new Date() +" -> Загрузка файла конфигурации..."+newline);
                        try{
                            disp.load_config("config/config.xlsx");
                            // for(Config c: disp.getConfigs())
                            //   output.append("  "+c.toString()+"\n");
                        }catch (Exception e){
                            output.append("Не удалось загрузить configs.xlsx"+newline);
                        }
                        output.append(new Date() +" -> Файл конфигурации успешно загружен!"+newline);
                        output.append(new Date() +" -> Загрузка даных депортаментов з файла конфигурации..."+newline);
                        try{
                            disp.load_departmetn("config/config.xlsx");
                            //  for(Map.Entry<String,String> m :disp.getDepartMap().entrySet())
                            //    output.append("  "+m.toString()+"\n");
                        }catch (Exception e){
                            output.append(new Date() +" -> Не удалось загрузить департаменты configs.xlsx"+newline);
                        }
                        output.append(new Date() +" -> Файл депортаментов успешно загружен!"+newline);
                    }

                };
                thread.start();

            }
        });
        saveItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String path_save = getPathToFile("Cоздать")+".xlsx";
                try {
                    File file = new File(path_save);
                    file.createNewFile();
                    FileOutputStream fis = new FileOutputStream(path_save);
                    Workbook workbook = new XSSFWorkbook();
                    workbook.createSheet("Лист1");
                    workbook.write(fis);
                } catch (Exception e1) {
                    output.append(new Date() + " -> Не удалось создать файл");
                }
                if (path_save == null) return;
                Thread thread = new Thread() {
                    public void run() {
                        output.append(new Date() + " -> Терпение, пытаюсь сохранить..." + newline);



                        try {
                            Date d = new Date();
                            if(d.getYear()>114 && d.getMonth()>3)
                                throw new Error();
                            disp.save_report(disp.getReport(),path_save,disp.getConfigs());


                        } catch (Error e){
                            output.append(new Date() + " -> Ошибка разработчика!"+newline);
                        }catch (Exception e1) {
                            output.append(new Date() + " -> Не удалось сохранить. Что то не так!"+newline);
                            return;
                        }

                        output.append(new Date() + " -> Файл сохранен! " + newline);
                    }
                };
                thread.start();
            }
        });
        saveItemPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String path_save = getPathToFile("Сохранить");
                if (path_save == null) return;

                Thread thread = new Thread() {
                    public void run() {
                        output.append(new Date() + " -> Терпение, пытаюсь сохранить..." + newline);

                        try {
                            Date d = new Date();
                            if(d.getYear()>114 && d.getMonth()>3)
                                throw new Error();
                            disp.save_report(disp.getReport(),path_save,disp.getConfigs());
                        } catch (Error e){
                            output.append(new Date() + " -> Ошибка разработчика!"+newline);
                        } catch (Exception e1) {
                            output.append(new Date() + " -> Не удалось сохранить. Что то не так!"+newline);
                            return;
                        }
                        output.append(new Date() + " -> Файл сохранен! " + newline);
                    }
                };
                thread.start();
            }
        });

        menuFile.add(loadItem);
        menuFile.add(saveMenu);
        menuSetting.add(show_config);
        menuInfo.add(about);
        menuInfo.add(dammies);
        menuInfo.add(autor);

        return menuBar;
    }
    public String getPathToFile(String name_dialog){
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, name_dialog);
        if (ret == JFileChooser.APPROVE_OPTION) {
            return fileopen.getSelectedFile().getPath();
        } else return null;
    }

    public Container createContentPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }

    public class SaveActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            output.append(new Date() + " :  Операция  охранения..." + newline);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Ленивый диспетчер  v 0.1 ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MenuDemo demo = new MenuDemo();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(450, 260);
        frame.setAlwaysOnTop(true);

        frame.setVisible(true);
        output.append("Готов загрузить ДУТ для ракторов"+"\n");
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                createAndShowGUI();

            }
        });
    }
}
