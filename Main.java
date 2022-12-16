import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File f;
        BufferedReader br = null;
        String line = null;
        try {
            f = new File("test");
            br = new BufferedReader(new FileReader(f));

            line = br.readLine();
            while (line != null) {
                System.out.println("===== input: " + line + " =====");
                Token tok = Lex.lex(line);
//            System.out.println("=======================");
                Obj res = Parse.parse(tok.next, true);
//            System.out.println("=======================");
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void show_obj(Obj obj) {

        System.out.println("key: " + obj.key);
        if (Obj.val.str != null)
            System.out.println("val: " + obj.val);
        else {
            show_obj(obj.val.obj);
        }
    }
}
