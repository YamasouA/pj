enum STRUCTUAL {
    LEFT_CBRACKET,
    RIGHT_CBRACKET,
    LEFT_SBRACKET,
    RIGHT_SBRACKET,
    COLON,
    COMMA,
    VAL,
    HEAD;
}

class Token {
    STRUCTUAL type;
    String contents;
    Token next;

    Token(STRUCTUAL type, String contents, Token next) {
        this.type = type;
        this.contents = contents;
        this.next = next;
    }
}

public class Lex {
    public static Token lex(String str) {
        Token tok = new Token(STRUCTUAL.HEAD, "", null);
        Token head = tok;
        STRUCTUAL type;
        String contents;
        Token new_tok;
        int i;

        while (str.length() > 0) {
            System.out.println(str);
            type = peek(str.charAt(0));
            if (type != STRUCTUAL.VAL) {
                contents = str.substring(0, 1);
                new_tok = new Token(type, contents, null);
                str = str.substring(1);
            }
            else {
                i = get_contents(str);
                contents = str.substring(0, i);
                str = str.substring(i);
                new_tok = new Token(type, contents, null);
            }
            tok.next = new_tok;
            tok = tok.next;
        }
        print_list(head);
        return head;
    }

    public static void print_list(Token tok) {
        while (tok != null) {
            System.out.println("type: " + tok.type + " contents: " + tok.contents);
            tok = tok.next;
        }
    }
    public static int get_contents(String str) {
        int i = 0;
        STRUCTUAL type;

        while (i < str.length()) {
            type = peek(str.charAt(i));
            if (type == STRUCTUAL.VAL)
                i++;
            else
                break;
        }
        return i;
    }
    public static STRUCTUAL peek(char c) {
        if (c == '{')
            return STRUCTUAL.LEFT_CBRACKET;
        else if (c == '}')
            return STRUCTUAL.RIGHT_CBRACKET;
        else if (c == '[')
            return STRUCTUAL.LEFT_SBRACKET;
        else if (c == ']')
            return STRUCTUAL.RIGHT_SBRACKET;
        else if (c == ':')
            return STRUCTUAL.COLON;
        else if (c == ',')
            return STRUCTUAL.COMMA;
        else
            return STRUCTUAL.VAL;
    }

}
