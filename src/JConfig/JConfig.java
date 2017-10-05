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
    
    private String homeDirectory;
    private Path config;
    private String SCofnig;
    private List<String> arguments;
    private HashMap<String, String> hm;
    
   public JConfig(String SConfig) {
        SConfig = SConfig + CFG;
        config = Paths.get(SConfig);
        hm = new HashMap<String, String>();

        try {
            init(SConfig);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            createConfig(SConfig);
        }
    }

    private void init(String SConfig) throws IOException {

        arguments = Files.readAllLines(Paths.get(SConfig), StandardCharsets.UTF_8);

       // System.err.println("Loading config " + config.toFile().getAbsolutePath());
       // System.err.println("Can read: " + config.toFile().canRead() + " can write: " + config.toFile().canWrite());

        for (String argument : arguments) {
           // System.err.println(" " + argument);
            String name = argument.substring(0, argument.indexOf("="));
            String value = argument.substring(argument.indexOf("=") + 1, argument.indexOf(";"));
            // System.err.println(name+": "+value);
            hm.put(name, value);
        }

    }

    private boolean createConfig(String SCongif) {
        try {
            Files.createFile(config);
           // System.err.println("Config created: " + config.toFile().getAbsolutePath());
            init(SCongif);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }
        return true;
    }
    
    private boolean createConfig() {
        try {
            Files.createFile(config);
           // System.err.println("Config created: " + config.toFile().getAbsolutePath());
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }
        return true;
    }
    private boolean createConfig(Path new_config) {
        try {
            Files.createFile(new_config);
           // System.err.println("Config created: " + config.toFile().getAbsolutePath());
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }
        return true;
    }
    
    
    public HashMap getConfig(){
        return hm;
    }
    
    /**
     *
     * @param name name of the variable
     * @param convertion always true
     * @return int
     */
    public int getConfigValue(String name, boolean convertion){
        try {
            return  Integer.parseInt(hm.get(name));
        } catch (NumberFormatException e) {
            return 0;
        }
        
    }
    
    public String getConfigValue(String name){
        if(!hm.containsKey(name)){
            setConfigValue(name, "");
        }
        return  hm.get(name);
    }
    
    
    public void setConfigValue(String name, String value){
        hm.put(name, value);
        if(saveConfig()) System.err.println("Config saved: "+config.toFile().getAbsolutePath());
    }
    public boolean isConfigEmpty(){
        return arguments.isEmpty();
    }
    
    private boolean saveConfig() {
        
        try {
            Files.deleteIfExists(config);
            createConfig();
            PrintWriter pw = new PrintWriter(new FileOutputStream(config.toFile()));
            for (Map.Entry<String, String> entry : hm.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
               // System.err.println("Writing: "+key + "=" + value + ";");
                pw.println(key + "=" + value + ";");
            }
            pw.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }
    
    public boolean saveConfig(String path) {
        Path new_config=Paths.get(path);
        try {
            Files.deleteIfExists(new_config);
            createConfig();
            PrintWriter pw = new PrintWriter(new FileOutputStream(new_config.toFile()));
            for (Map.Entry<String, String> entry : hm.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
               // System.err.println("Writing: "+key + "=" + value + ";");
                pw.println(key + "=" + value + ";");
            }
            pw.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }


    public Path getConfigPath() {
        return config;
    }

}
