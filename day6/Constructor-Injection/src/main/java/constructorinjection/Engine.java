package constructorinjection;

import org.springframework.stereotype.Component;

@Component
public class Engine {
    public String start() {
        return "Engine Started";
    }
}
