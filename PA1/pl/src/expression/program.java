import java.io.IOException;
import org.antlr.v4.runtime.*;

public class program {

    public static void main(String[] args) throws IOException {
                
        // Get Lexer
        ExprLexer lexer = new ExprLexer(CharStreams.fromStream(System.in));
        
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // Pass tokens to parser
        ExprParser parser = new ExprParser(tokens);
		ExprParser.ProgContext parser_ctx = parser.prog();

		// Build AST
		// Build the AST with BuildAstVisitor.java

		// Print AST
		// Print built AST with AstCall.java

		// Evaluate AST
		// Evaluate AST with traversing AST.

	}
}
