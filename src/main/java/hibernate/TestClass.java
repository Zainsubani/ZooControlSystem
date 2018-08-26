package hibernate;

import hibernate.dao.*;
import hibernate.entity.*;
import xml.ReaderXML;
import xml.WriterXML;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClass {

    public static void main (String[] args){
        /*

        ReaderXML reader = new ReaderXML();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:\\Users\\Максим\\Desktop\\test_output.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader.parseXML(fis);


        WriterXML writer = new WriterXML();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("C:\\Users\\Максим\\Desktop\\test_outpu.xml");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        writer.writeXML(fos);


        System.out.println(DBResourceManager.getAnimalDAO().query("from Animal", null).get(0));
        */
  }
}
