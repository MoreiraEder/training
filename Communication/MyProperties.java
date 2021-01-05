import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {  

    public static Properties getProperties() {
        Properties properties = new Properties();

        try {
            File file = new File("\\\\C:\\Users\\emoreira\\Documents\\Training\\Threads\\file.properties");
            InputStream stream = new FileInputStream(file);                        
            properties.load(stream);
            
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de propriedades.");
            e.printStackTrace();
        }

        return properties;

    }   
    
}
