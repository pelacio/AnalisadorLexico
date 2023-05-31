import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int currentTokenIndex;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public void parseProgram() {
        while (currentTokenIndex < tokens.size()) {
            parseStatement();
        }
    }

    private void parseStatement() {
        Token currentToken = getNextToken();

        if (currentToken.getToken().equals("ID")) {
            parseAssignment();
        } else if (currentToken.getToken().equals("IF")) {
            parseIfStatement();
        } else if (currentToken.getToken().equals("PRINT")) {
            parseExpression();
        } else {
            throw new SyntaxError("Token inesperado: " + currentToken.getToken());
        }
    }

    private void parseAssignment() {
        parseExpression();

        Token currentToken = getNextToken();
        if (!currentToken.getToken().equals("ASSIGN")) {
            throw new SyntaxError("Esperado '=' após a expressão, mas encontrado: " + currentToken.getToken());
        }

        parseExpression();
    }

    private void parseIfStatement() {
        parseExpression();

        Token currentToken = getNextToken();
        if (!currentToken.getToken().equals("IF")) {
            throw new SyntaxError("Esperado 'if' após a expressão, mas encontrado: " + currentToken.getToken());
        }

        parseExpression();

        currentToken = getNextToken();
        if (!currentToken.getToken().equals("ELSE")) {
            throw new SyntaxError("Esperado 'else' após a expressão if, mas encontrado: " + currentToken.getToken());
        }

        parseExpression();
    }

    private void parseExpression() {
        Token currentToken = getNextToken();

        if (currentToken.getToken().equals("ID")) {
            System.out.println("Variável encontrada: " + currentToken.getValue());
        } else if (currentToken.getToken().equals("NUMBER")) {
            System.out.println("Número encontrado: " + currentToken.getValue());
        } else {
            throw new SyntaxError("Token inesperado: " + currentToken.getToken());
        }
    }

    private Token getNextToken() {
        if (currentTokenIndex < tokens.size()) {
            return tokens.get(currentTokenIndex++);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String code = "x = 10\nif x > 5\nprint(\"Hello, world!\")";
        List<Token> tokens = Lexer.lex(code);

        Parser parser = new Parser(tokens);
        parser.parseProgram();

        // Tabela de apresentação dos resultados
        System.out.println("Token\t\tValor");
        System.out.println("--------------------------");
        for (Token token : tokens) {
            System.out.println(token.getToken() + "\t\t" + token.getValue());
        }
    }
}
