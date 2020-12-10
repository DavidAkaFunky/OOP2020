import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class App {
    public static void app(String[] args) throws FileNotFoundException{
        Cat cat = new Cat(10, 16, "Tareco");
        CatOutputChannel coc = new CatOutputChannel(
                                    new DataOutputStream(
                                        new BufferedOutputStream(
                                            new FileOutputStream("tareco.txt"))
        coc.put(cat);
    }
}
