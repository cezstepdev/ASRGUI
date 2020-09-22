/*
import edu.cmu.sphinx.frontend.util.Microphone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Device {
    private static final String path = "D:\\AIForce1\\configurationapp\\application.properties";

    public Microphone getMicrophone() {
        Microphone mic;
        String micIndex = getMicrophoneIndex();
        mic = new Microphone( 16000, 16, 1,
                true, true, true, 10, false,
                "selectChannel", 2, micIndex, 6400);
        return mic;
    }

    public String getMicrophoneIndex() {
        String mic = "";
        String[] tmp;
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            for (String line : allLines)
                if(line.contains("scen.microphoneportindex ="))
                {
                    tmp = line.split(" ");
                    mic = tmp[tmp.length-1];
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return mic;
    }
}
*/
