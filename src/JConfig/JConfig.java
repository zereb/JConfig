/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JConfig;

/**
 *
 * @author lul
 */


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



public class JConfig {
    
    public static final String CFG=".cfg";
    
    public String homeDirectory;
    public Path config;
    public String SCofnig;
    public List<String> arguments;
    public HashMap<String, String> hm;
    
    public JConfig(String SConfig) {
        SConfig = SConfig + CFG;
        config = Paths.get(SConfig);
        hm = new HashMap<String, String>();
   
        try {
            arguments = Files.readAllLines(Paths.get(SConfig), StandardCharsets.UTF_8);
            
            System.out.println("Loading config "+ config.toFile().getAbsolutePath());
            System.out.println("Can read: "+config.toFile().canRead()+" can write: "+config.toFile().canWrite());
            
            for (String argument : arguments) {
                System.out.println(" "+argument);
                String name = argument.substring(0, argument.indexOf("="));
                String value = argument.substring(argument.indexOf("=")+1, argument.indexOf(";"));
                //System.out.println(name+": "+value);
                hm.put(name, value);
            }
            
            
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        
    }

    private boolean createConfig() {
        try {
            Files.createFile(config);
            System.out.println("Config created: "+config.toFile().getAbsolutePath());
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }
        return true;
    }
    
    
    public HashMap getConfig(){
        return hm;
    }
    
    public int getConfigValue(String name, int i){
        try {
            return  Integer.parseInt(hm.get(name));
        } catch (NumberFormatException e) {
            return 0;
        }
        
    }
    
    public String getConfigValue(String name, String s){
        return  hm.get(name);
    }
    
    
    public void setConfigValue(String name, String value){
        hm.put(name, value);
        if(saveConfig()) System.out.println("Config saved: "+config.toFile().getAbsolutePath());
    }
    
    private boolean saveConfig() {

        try {
            Files.deleteIfExists(config);
            createConfig();
            PrintWriter pw = new PrintWriter(new FileOutputStream(config.toFile()));
            for (Map.Entry<String, String> entry : hm.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("Writing: "+key + "=" + value + ";");
                pw.println(key + "=" + value + ";");
            }
            pw.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }

}
