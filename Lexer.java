import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static List<Token> lex(String code) {
        List<Token> tokens = new ArrayList<>();
        String[] lines = code.split("\n");

        for (String line : lines) {
            String[] words = line.split(" ");

            for (String word : words) {
                if (word.matches("[a-zA-Z]+")) {
                    tokens.add(new Token("ID", word));
                } else if (word.matches("\\d+")) {
                    tokens.add(new Token("NUMBER", word));
                } else if (word.equals("=")) {
                    tokens.add(new Token("ASSIGN", word));
                } else if (word.equals("if")) {
                    tokens.add(new Token("IF", word));
                } else if (word.equals("else")) {
                    tokens.add(new Token("ELSE", word));
                } else if (word.equals("print")) {
                    tokens.add(new Token("PRINT", word));
                }
            }
        }

        return tokens;
    }

    public static void main(String[] args) {
        String code = "x = 10\nif x > 5\nprint(\"Hello, world!\")";
        List<Token> tokens = lex(code);

        // Tabela de apresentação dos resultados
        System.out.println("Token\t\tValor");
        System.out.println("--------------------------");
        for (Token token : tokens) {
            System.out.println(token.getToken() + "\t\t" + token.getValue());
        }
    }
}