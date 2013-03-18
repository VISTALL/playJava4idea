/*
 * Copyright 2010-2013 napile.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.napile.playJava4idea.template.base;

import org.jetbrains.annotations.NotNull;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateNodes;
import org.napile.playJava4idea.template.base.parser.PlayBaseTemplateTokens;
import org.napile.playJava4idea.template.base.parser.lexer.PlayBaseTemplatePrattTokenType;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

/**
 * @author VISTALL
 * @since 19:01/18.03.13
 */
public class PlayBaseTemplateParser implements PsiParser, PlayBaseTemplateTokens, PlayBaseTemplateNodes
{
	public static final PsiParser INSTANCE = new PlayBaseTemplateParser();

	@NotNull
	@Override
	public ASTNode parse(IElementType root, PsiBuilder builder)
	{
		PsiBuilder.Marker marker = builder.mark();
		while(!builder.eof())
		{
			final IElementType tokenType = builder.getTokenType();
			if(tokenType == SHARP)
			{
				advanceNonSimple(builder, TAG, DIV, RBRACE);
			}
			else if(tokenType == PERC)
			{
				advanceNonSimple(builder, SCRIPT, RBRACE, PERC);
			}
			else if(tokenType == DOLLAR)
			{
				advanceSimple(builder, EXPRESSION);
			}
			else if(tokenType == AT)
			{
				advanceSimple(builder, ACTION);
			}
			else if(tokenType == ATAT)
			{
				advanceSimple(builder, ABSOLUTE_ACTION);
			}
			else if(tokenType == AND)
			{
				advanceSimple(builder, MESSAGE);
			}
			else
				builder.advanceLexer();
		}
		marker.done(root);
		return builder.getTreeBuilt();
	}

	private void advanceSimple(PsiBuilder builder, IElementType token)
	{
		PsiBuilder.Marker m = builder.mark();
		builder.advanceLexer(); // start symbol
		builder.advanceLexer(); // {

		skipUntil(builder, TokenSet.create(PlayBaseTemplateTokens.RBRACE));

		if(builder.getTokenType() == RBRACE)
		{
			builder.advanceLexer(); // }
		}
		else
		{
			builder.error(RBRACE.getExpectedText(null));
		}
		m.done(token);
	}

	private void advanceNonSimple(PsiBuilder builder, IElementType done, PlayBaseTemplatePrattTokenType end1, PlayBaseTemplatePrattTokenType end2)
	{
		PsiBuilder.Marker m = builder.mark();
		builder.advanceLexer(); // start symbol
		builder.advanceLexer(); // {

		skipUntil(builder, TokenSet.create(end1));

		if(builder.getTokenType() == end1)
		{
			builder.advanceLexer(); // / or }

			if(builder.getTokenType() == end2)
			{
				builder.advanceLexer();
			}
			else
			{
				builder.error(end2.getExpectedText(null));
			}
		}
		else
		{
			builder.error(end1.getExpectedText(null));
		}

		m.done(done);
	}

	private void skipUntil(PsiBuilder builder, TokenSet tokenSet)
	{
		while(!builder.eof())
		{
			if(tokenSet.contains(builder.getTokenType()))
			{
				break;
			}

			builder.advanceLexer();
		}
	}
}
