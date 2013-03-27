package org.napile.playJava4idea.route.psi.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.napile.playJava4idea.route.psi.PlayRouteElementTypes;
%%

%public
%class _PlayRouteLexer
%implements FlexLexer
%final
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

LineTerminator = \r|\n|\r\n

END_OF_LINE_COMMENT="#"[^\r\n]*

%%

//GET|POST|PUT|DELETE|OPTIONS|HEAD|WS|\*
"GET" {return PlayRouteElementTypes.METHOD_TYPE;}
"POST" {return PlayRouteElementTypes.METHOD_TYPE;}
"PUT" {return PlayRouteElementTypes.METHOD_TYPE;}
"DELETE" {return PlayRouteElementTypes.METHOD_TYPE;}
"OPTIONS" {return PlayRouteElementTypes.METHOD_TYPE;}
"HEAD" {return PlayRouteElementTypes.METHOD_TYPE;}
"WS" {return PlayRouteElementTypes.METHOD_TYPE;}
"*" {return PlayRouteElementTypes.METHOD_TYPE;}

{END_OF_LINE_COMMENT} {return PlayRouteElementTypes.COMMENT;}

{LineTerminator} | [ \t\f] {return PlayRouteElementTypes.WHITE_SPACE;}

. {return PlayRouteElementTypes.SOME_REFERENCE;}