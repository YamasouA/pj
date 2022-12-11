import java.util.Map;

class Result {
    String key;
    Result value;
    String val;
    Result(String contents) {
        this.val = contents;
    }
    Result() {

    }
}

public class Parse {
    public static Result parse(Token tok, boolean is_root) {
        STRUCTUAL type = tok.type;
        if (is_root && type != STRUCTUAL.LEFT_CBRACKET) {
            System.out.println("Error");
            return new Result();
        }
        if (type == STRUCTUAL.LEFT_CBRACKET)
            return parse_object(tok.next);
        else if (type == STRUCTUAL.LEFT_SBRACKET)
            return parse_object(tok.next);
        else {
            Result contents = new Result(tok.contents);
            tok = tok.next;
            return contents;
        }
    }

    public static Result parse_object(Token tok) {
        STRUCTUAL type = tok.type;
        Result json_obj = new Result();
        if (type == STRUCTUAL.RIGHT_CBRACKET){
            tok = tok.next;
            return json_obj;
        }

        String json_key;
        while (true) {
            json_key = tok.contents;
            tok = tok.next;
            if (tok.type != STRUCTUAL.COLON) {
                System.out.println("ERROR");
                return (json_obj);
            }
            Result json_value = parse(tok.next, false);
            json_obj.key = json_key;
            json_obj.value = json_value;
            type = tok.type;
            if (type == STRUCTUAL.RIGHT_CBRACKET) {
                tok = tok.next;
                return json_obj;
            } else if (type != STRUCTUAL.COMMA) {
                System.out.println("ERROR");
                return json_obj;
            }
            tok = tok.next;
        }
    }

    public static Result parse_array(Token tok) {
        return ;
    }
}
