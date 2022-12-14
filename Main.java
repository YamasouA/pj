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
//            f = new File("only-obj");
//            f = new File("only-arr");
            f = new File("mix-obj-arr");
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
//        System.out.println("hoge");
        for (int i = 0; i < indentSize; i++)
            System.out.print(" ");
        System.out.println("key: " + obj.key);
        if (obj.val.str != null) {
            for (int i = 0; i < indentSize; i++)
                System.out.print(" ");
            System.out.println("val: " + obj.val.str);
        } else if (obj.val.arr != null) {
            for (Value v: obj.val.arr) {
                if (v.str != null) {
                    for (int i = 0; i < indentSize; i++)
                        System.out.print(" ");
                    System.out.println(v.str);
                }
                else {
                    System.out.println("key: " + v.obj.key);
                    System.out.println("val: " + v.obj.val.str);
                }
            }
        } else {
            if (obj.val.obj != null)
                show_obj(obj.val.obj, indentSize+2);
        }
    }
}
