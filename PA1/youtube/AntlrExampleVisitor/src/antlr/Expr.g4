grammar Expr;

/* the grammar name and file name must match */

@header {
	package antlr;
}


// Star Variable
prog: (decl | expr)+ EOF       # Program
    ;

decl: ID ':' INT_TYPE '=' NUM  # Declaration
    ;

// 위로 갈수록 높은 우선순위를 가짐.

// ANTLR resloves ambguities in favor of the alternative give first
expr: expr '*' expr            # Multiplication
    | expr '+' expr            # Addition
    | ID                       # Variable
    | NUM                      # Number
    ;

// Tokens
ID : [a-z][a-zA-Z0-9_]*; // identifiers
NUM : '0' | '-'?[1-9][0-9]*;
INT_TYPE: 'INT';
COMMENT : '--' ~[\r\n]* -> skip;
WS : [ \t\n] -> skip ;

