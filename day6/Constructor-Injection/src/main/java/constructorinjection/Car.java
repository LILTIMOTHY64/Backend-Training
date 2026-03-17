package constructorinjection;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public String drive() {
        return engine.start();
    }
}
