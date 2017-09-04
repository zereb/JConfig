/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JConfig;

import java.io.File;
import java.io.IOException;
import javax.swing.KeyStroke;

/**
 *
 * @author lul
 */



public class Test {
    
   public int kek;
   public int privet;
   public String dratyte;
   
   
   
   
    public static void main(String[] args) {
        JConfig jc = new JConfig("kek");
        Test t = new Test();
        t.kek = jc.getConfigValue("kek", 0);
        t.privet = jc.getConfigValue("privet", 0);
        t.dratyte = jc.getConfigValue("dratyte", "");
        System.out.println("kek: " + t.kek);
        System.out.println("privet: " + t.privet);
        System.out.println("dratyre: " + t.dratyte);
        
       jc.setConfigValue("dratyte", "dratyte saved");
    }

    
}
