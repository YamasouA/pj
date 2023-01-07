import java.lang.reflect.Array;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/*
class Ret {
    Obj obj;
    Token tok;
}
*/
class Obj {
    String key;
    Value val;
    Map<String, Value> m;
    Obj (ArrayList<Value> val) {
//        for (Value v: val) {
//            System.out.println(v.str);
//        }
        this.key = null;
        this.val = new Value(val); }
    Obj () { this.key = null; this.val = null; }
}

class Value {
    String str;
    int num;
    Obj obj;
    ArrayList<Value> arr;
    boolean bool;
    boolean is_null;
    Value(String str) {
        this.str = str;
    }
    Value() { this.str = null; this.obj=null; }
    Value(ArrayList<Value> arr) { this.arr = new ArrayList<>(); this.arr = arr;}
}

public class Parse {
    static Token tok;
    public static Obj parse(Token tok1, boolean is_root) {
        tok = tok1;
        STRUCTUAL type = tok.type;
//        if (is_root && type != STRUCTUAL.LEFT_CBRACKET) {
//            System.out.println("Error");
//        }
        if (type == STRUCTUAL.LEFT_CBRACKET) {
//            return parse_object(tok.next);
            tok = tok.next;
            return parse_object();
        }
        else if (type == STRUCTUAL.LEFT_SBRACKET) {
            tok = tok.next;
            return new Obj(parse_array());
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
//        System.out.println("consume " + tok.type);
        if (tok.type == type){
            tok = tok.next;
            return true;
        }
        return false;
    }

    public static boolean peak(Token tok, STRUCTUAL type) {
//        System.out.println("peak: " + tok.type);
        if (tok == null)
            return false;
        if (tok.type == type)
            return true;
        return false;
    }

    public static Value parse_type(Token tok) {
        Value val = new Value();
        if (tok.type == STRUCTUAL.LEFT_CBRACKET) {
            val.obj = parse(tok, false);
        } else if (tok.type == STRUCTUAL.VAL) {
            val.str = new String(tok.contents);
        }
        return val;
    }
//    public static Value parse_value(Token tok) {
//        Value val= new Value();
//        val.obj = parse(tok ,false);
//        return val;
//    }

    public static ArrayList<Value> parse_array() {
        ArrayList<Value> arr = new ArrayList<>();
//        System.out.println("here1");
        while (tok != null) {
            if (peak(tok, STRUCTUAL.RIGHT_SBRACKET))
                return arr;
            if (peak(tok, STRUCTUAL.VAL)) {
                arr.add(parse_type(tok));
                if (tok != null)
                    tok = tok.next;
            }
            consume(STRUCTUAL.COMMA);
//            if (!consume(STRUCTUAL.COMMA)) {
////                System.out.println("ERROR");
//                return arr;
//            }
//            System.out.println("here");
        }
//        for (Value v: arr) {
//            System.out.println(v.str);
//        }
        return arr;
    }
//    public static Obj parse_object(Token tok) {
    public static Obj parse_object() {
        Obj json_obj = new Obj();
        Value val = new Value();
        if (tok.type == STRUCTUAL.RIGHT_CBRACKET)
            return json_obj;
        while (tok != null) {
            String key = tok.contents;
            tok = tok.next;
            if (!consume(STRUCTUAL.COLON)) {
                System.out.println("ERROR");
                return json_obj;
            }
            val = parse_type(tok);

            json_obj.key = key;
            json_obj.val = val;
            if (tok != null)
                tok = tok.next;
            if (peak(tok, STRUCTUAL.RIGHT_CBRACKET)) {
                return json_obj;
            }
        }
        return json_obj;
    }

}
