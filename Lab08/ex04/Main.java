import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        CatRegistry reg = new CatRegistry();
        Cat cat1 = new Cat("Tareco", 20);
        Cat cat2 = new Cat("Meow", 15);
        reg.put(cat1);
        reg.put(cat2);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cats.dat")));
            oos.writeObject(reg);
            oos.close();
        } catch (IOException e) { e.printStackTrace(); }

        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("cats.dat")));
            CatRegistry prevReg = (CatRegistry) ois.readObject();
            ois.close();
            System.out.println(prevReg.get("Tareco"));
            System.out.println(prevReg.get("Meow"));
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
    }
}