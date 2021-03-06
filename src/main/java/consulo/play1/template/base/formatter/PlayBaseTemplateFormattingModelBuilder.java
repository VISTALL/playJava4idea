/*
 * Copyright 2013-2016 consulo.io
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

package consulo.play1.template.base.formatter;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.templateLanguages.DataLanguageBlockWrapper;
import com.intellij.formatting.templateLanguages.TemplateLanguageBlock;
import com.intellij.formatting.templateLanguages.TemplateLanguageFormattingModelBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleSettings;

/**
 * @author VISTALL
 * @since 18:05/26.03.13
 */
public class PlayBaseTemplateFormattingModelBuilder extends TemplateLanguageFormattingModelBuilder
{
	@Override
	public TemplateLanguageBlock createTemplateLanguageBlock(@Nonnull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, @Nullable List<DataLanguageBlockWrapper> foreignChildren, @Nonnull CodeStyleSettings codeStyleSettings)
	{
		return new PlayBaseTemplateBlock(node, wrap, alignment, this, codeStyleSettings, foreignChildren);
	}
}
