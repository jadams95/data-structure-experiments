lexer grammar HtmlLexer;

// Define tokens
TAG_OPEN : '<' ;
TAG_CLOSE : '>' ;
SLASH : '/' ;
EQUALS : '=' ;
STRING : '"' .*? '"' ;
NAME : [a-zA-Z]+ ;
WS : [ \t\n\r]+ -> skip ;
