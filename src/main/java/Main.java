import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabase.class);

    }
}
