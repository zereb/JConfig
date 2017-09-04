/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JConfig;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author lul
 */
public class Test {
    
     public static void main(String[] args) {
        
      File myFile = new File(",");
        System.out.println("Имя файла: " + myFile.getName());
        System.out.println("Родительский каталог: " + myFile.getParent());
        if(myFile.exists())
            System.out.println("Файл существует");
        else
            System.out.println("Файл еще не создан");
         
        System.out.println("Размер файла: " + myFile.length());
        if(myFile.canRead())
            System.out.println("Файл доступен для чтения");
        else
            System.out.println("Файл не доступен для чтения");
         
        if(myFile.canWrite())
            System.out.println("Файл доступен для записи");
        else
            System.out.println("Файл не доступен для записи");
         
        // создадим новый файл
        File newFile = new File("C://SomeDir//MyFile");
        try
        {
            boolean created = newFile.createNewFile();
            if(created)
                System.out.println("Файл создан");
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }  
    } 
    
    
}
