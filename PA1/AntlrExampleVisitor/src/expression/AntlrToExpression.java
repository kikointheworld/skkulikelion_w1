package expression;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
// import antlr.ExprBaseVisitor;
import antlr.ExprParser.AdditionContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.MultiplicationContext;
import antlr.ExprParser.NumberContext;
import antlr.ExprParser.ProgramContext;
import antlr.ExprParser.VariableContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {
	
	private List<String> vars; // stores all the vairables declared in the program so far
	private List<String> semanticErrors;
	// note that semantic error is different from sysntax errors.

	public AntlrToExpression(List<String> semanticErrors) {
		vars = new ArrayList<>();
		this.semanticErrors = semanticErrors;
	}

	@Override
	public Expression visitProgram(ProgramContext ctx) {

		return super.visitProgram(ctx);
	}

	@Override
	public Expression visitDeclaration(DeclarationContext ctx) {
		// ID() is a method generated to correspond to the token ID in the source grammar.
		Token idToken = ctx.ID().getSymbol(); // equivalent to : ctx.getChild(0).getSymbol()
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine();
		String id = ctx.getChild(0).getText();
		// Maintianing the vars list for semantic error reporting
		if (vars.contains(id)) {
			semanticErrors.add("Error: vairable " + id + " already declared (" + line + ", " + column + ")") ;

		}
		else {
			vars.add(id);
		}
		String type = ctx.getChild(2).getText();
		int value = Integer.parseInt(ctx.NUM().getText());
		return new VariableDeclaration(id, type, value);
	}

	@Override
	public Expression visitMultiplication(MultiplicationContext ctx) {
		Expression left = visit(ctx.getChild(0)); // recusilvely vist the left subtree of the current Multiplication node
		Expression right = visit(ctx.getChild(2));
		return new Multiplication(left, right);
	}

	@Override
	public Expression visitAddition(AdditionContext ctx) {
		Expression left = visit(ctx.getChild(0)); // recusilvely vist the left subtree of the current Multiplication node
		Expression right = visit(ctx.getChild(2));
		return new Addition(left, right);
	}

	@Override
	public Expression visitVariable(VariableContext ctx) {
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine();
		String id = ctx.getChild(0).getText();
		if (!vars.contains((id))) {
			semanticErrors.add("Error: vairable " + id + " not declared (" + line + ", " + column + ")") ;
		}

		return new Variable(id);
	}

	@Override
	public Expression visitNumber(NumberContext ctx) {
		String numText = ctx.getChild(0).getText();
		int num = Integer.parseInt(numText);
		return new Number(num);
		
	}
	
}
