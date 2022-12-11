public class Main {
    public static void main(String[] args) {
        Token tok = Lex.lex("{[contents: val]}");
        Result res = Parse.parse(tok, true);
    }
}
