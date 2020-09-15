package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveToFile {

    private String server;
    private String post;
    private String port;
    private int inputDeviceIndex;
    private int outputDeviceIndex;
    private final String csFile = "AppConfig.config";
    private final String jFile = "application.properties";

    public SaveToFile(String server, String post, String port, int inputDeviceIndex, int outputDeviceIndex) {
        this.port = port;
        this.server = server;
        this.post = post;
        this.inputDeviceIndex = inputDeviceIndex;
        this.outputDeviceIndex = outputDeviceIndex;
    }

    //preparing to save
    public void modify() {
        if(!Files.exists(Paths.get(csFile)))
        {
            createFile(csFile);
            saveConfig(csFile);
        }
            
        if(!Files.exists(Paths.get(jFile)))
        {
            createFile(jFile);
            saveConfig(jFile);
        }
        
        modifyCS();
        modifyJ();
    }

    //creating files when dont exist
    public void createFile(String name) {
        try {
            File myFile = new File(name);
            myFile.createNewFile();
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //creating default configs using ConfigData class
    public void saveConfig(String name) {
        FileWriter fileWriter;
        try {
            ConfigData configData = new ConfigData();
            fileWriter = new FileWriter(name);
            if(name.equals(csFile))
                fileWriter.write(configData.csConfig);
            else
                fileWriter.write(configData.jConfig);
            System.out.println("saving...");
            fileWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //modifying c# config in some lines
    public void modifyCS() {
        try {
            BufferedReader file = new BufferedReader(new FileReader(csFile));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if(line.toLowerCase().contains("<add key = \"url\" value = \"wss://"))
                    line = "\t\t<add key = \"url\" value = \"wss://" + server + "/console/" + post + "/" + port + "/\"/>";
                if(line.toLowerCase().contains("<add key = \"output\" value = \""))
                    line = "\t\t<add key = \"output\" value = \"" + outputDeviceIndex + "\"/>";
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream(csFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    //modifying Java config in some lines
    public void modifyJ() {
        try {
            BufferedReader file = new BufferedReader(new FileReader(jFile));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if(line.toLowerCase().contains("server.port ="))
                    line = "server.port = " + port;
                if(line.toLowerCase().contains("scen.id ="))
                    line = "scen.id = " + post;
                if(line.toLowerCase().contains("scen.server ="))
                    line = "scen.server = " + server;
                if(line.toLowerCase().contains("scen.microphoneportindex ="))
                    line = "scen.microphoneportindex = " + inputDeviceIndex;

                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream(jFile);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
