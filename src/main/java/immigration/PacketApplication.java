package immigration;

import immigration.view.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PacketApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacketApplication.class, args);
    }

}
