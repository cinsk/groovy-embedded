package hello;

import org.apache.bsf.BSFManager;
import org.apache.bsf.BSFException;

import java.util.List;
import java.util.stream.Collectors;
import java.io.FilenameFilter;
import java.io.File;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello");

        File dir = new File(args[0]);
        File[] filesList = dir.listFiles((File d, String name) -> {
                return name.endsWith(".gvy") || name.endsWith(".groovy") ||
                name.endsWith(".gy") || name.endsWith(".gsh");
            });
        for (File f: filesList) {
            System.out.println("File: " + f);
        }

        try {
            String myScript = "println('Hello World')\n  return 1 < 10";
            BSFManager manager = new BSFManager();
            Boolean answer = (Boolean) manager.eval("groovy", "myScript.groovy", 0, 0, myScript);
            System.out.println(String.format("%s", answer));
        }
        catch (BSFException e) {
            System.err.println(e.getMessage());
        }
    }
}
