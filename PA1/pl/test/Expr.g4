grammar Expr;

// parser rules
prog : (expr ';' NEWLINE?)*;
expr : '(' expr ')'         # parensExpr
     | expr ('*'|'/') expr  # infixExpr
     | expr ('+'|'-') expr  # infixExpr
     | num                  # numberExpr
     | '(' expr ')'         # parensExpr
     ;
num  : INT
     | REAL
     ;

// lexer rules
NEWLINE: [\r\n]+ ;
INT: [0-9]+ ;          // should handle negatives
REAL: [0-9]+'.'[0-9]* ; // should handle signs(+/-)
ID: [a-zA-Z]+ ;
WS: [ \t\r\n]+ -> skip ;
