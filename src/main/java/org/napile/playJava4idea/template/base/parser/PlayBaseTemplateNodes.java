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

package org.napile.playJava4idea.template.base.parser;

import org.napile.playJava4idea.template.base.parser.lexer.PlayBaseTemplateElementType;
import org.napile.playJava4idea.template.base.psi.PlayBaseTemplateTag;

/**
 * @author VISTALL
 * @since 19:09/18.03.13
 */
public interface PlayBaseTemplateNodes
{
	PlayBaseTemplateElementType TAG = new PlayBaseTemplateElementType("TAG", PlayBaseTemplateTag.class);

	PlayBaseTemplateElementType EXPRESSION = new PlayBaseTemplateElementType("EXPRESSION");

	PlayBaseTemplateElementType ACTION = new PlayBaseTemplateElementType("ACTION");

	PlayBaseTemplateElementType ABSOLUTE_ACTION = new PlayBaseTemplateElementType("ABSOLUTE_ACTION");

	PlayBaseTemplateElementType MESSAGE = new PlayBaseTemplateElementType("MESSAGE");

	PlayBaseTemplateElementType SCRIPT = new PlayBaseTemplateElementType("SCRIPT");
}
