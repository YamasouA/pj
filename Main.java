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
                if (line.charAt(0) == '/' && line.charAt(1) == '/'){
                    line = br.readLine();
                    continue;
                }
                System.out.println("===== input: " + line + " =====");
                Token tok = Lex.lex(line);
//            System.out.println("=======================");
                Obj res = Parse.parse(tok.next, true);
                show_obj(res, 0);
//            System.out.println("=======================");
                line = br.readLine();


            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void show_obj(Obj obj, int indentSize) {

        for (int i = 0; i < indentSize; i++)
            System.out.print(" ");
        System.out.println("key: " + obj.key);
        if (obj.val.str != null) {
            for (int i = 0; i < indentSize; i++)
                System.out.print(" ");
            System.out.println("val: " + obj.val.str);
        } else {
            if (obj.val.obj != null)
                show_obj(obj.val.obj, indentSize+2);
        }
    }
}
