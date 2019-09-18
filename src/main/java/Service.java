import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Service {

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(
                "/home/danil/IdeaProjects/MyFirstTest/src/main/resources/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
