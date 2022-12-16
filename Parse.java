import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

class Ret {
    Obj obj;
    Token tok;
}
class Obj {
    String key;
    static Value val;
    Map<String, Value> m;
}

class Value {
    String str;
    int num;
    Obj obj;
    List<String> arr;
    boolean bool;
    boolean is_null;
    Value(String str) {
        this.str = str;
    }
}

public class Parse {
    static Token tok;
    public static Obj parse(Token tok1, boolean is_root) {
        tok = tok1;
        STRUCTUAL type = tok.type;
        if (is_root && type != STRUCTUAL.LEFT_CBRACKET) {
            System.out.println("Error");
        }
        if (type == STRUCTUAL.LEFT_CBRACKET) {
//            return parse_object(tok.next);
            tok = tok.next;
            return parse_object();
        }
        else if (type == STRUCTUAL.LEFT_SBRACKET) {
//            return parse_array(tok.next);
        }
//        else {
//            Map<String, Result> contents = new HashMap();
//            contents.put(tok.contents);
//            tok = tok.next;
//            return contents;
//        }
        return new Obj();
    }

    public static boolean consume(STRUCTUAL type) {
        System.out.println("consume " + tok.type);
        if (tok.type == type){
            tok = tok.next;
            return true;
        }
        return false;
    }

    public static boolean peak(Token tok, STRUCTUAL type) {
        System.out.println("peak: " + tok.type);
        if (tok.type == type)
            return true;
        return false;
    }

    public static Value parse_type(Token tok) {
        return new Value(tok.contents);
    }

//    public static Obj parse_object(Token tok) {
public static Obj parse_object() {
        Obj json_obj = new Obj();
//        Ret ret = new Ret();
        if (tok.type == STRUCTUAL.RIGHT_CBRACKET)
//            return ret;
            return json_obj;
        while (true) {
            String key = tok.contents;
            System.out.println("key: " + tok.contents);
            tok = tok.next;
            System.out.println("contents1: " + tok.contents);
            if (!consume(STRUCTUAL.COLON)) {
                System.out.println("ERROR");
//                ret.obj = json_obj;
//                ret.tok = tok;
//                return ret;
                return json_obj;
            }
            System.out.println("contents2: " + tok.contents);
            Value val = parse_type(tok);
            json_obj.key = key;
            json_obj.val = val;
            tok = tok.next;
            if (peak(tok, STRUCTUAL.RIGHT_CBRACKET)) {
                tok = tok.next;
//                ret.obj = json_obj;
//                ret.tok = tok;
//                return ret;
                return json_obj;
            }
        }

    }

}
