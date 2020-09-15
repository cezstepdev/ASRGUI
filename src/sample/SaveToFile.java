package sample;

import java.io.*;

public class SaveToFile {
    File myFile;

    public void createFile() {
        try {
            myFile = new File("AppConfig.config");
            if(myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void modify(String server, String stanowisko, String port) {
        try {
            BufferedReader file = new BufferedReader(new FileReader("AppConfig.config"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                System.out.println("linia" + line);
                if(line.toLowerCase().contains("<add key = \"url\" value = \"wss://"))
                {
                    line = "\t\t<add key = \"url\" value = \"wss://" + server + "/console/" + stanowisko + "/" + port + "/\"/>";
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            FileOutputStream fileOut = new FileOutputStream("AppConfig.config");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public void saveConfig(String config) {
        createFile();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("AppConfig.config");
            fileWriter.write(config);
            System.out.println("saving...");
            fileWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
