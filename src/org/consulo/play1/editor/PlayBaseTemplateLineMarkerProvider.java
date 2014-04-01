/*
 * Copyright 2013 Consulo.org
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

package org.consulo.play1.editor;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.util.ConstantFunction;
import org.consulo.play1.PlayJavaIcons;
import org.consulo.play1.PlayJavaUtil;
import org.consulo.play1.template.base.psi.PlayBaseTemplateFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

/**
 * @author VISTALL
 * @since 23:21/18.03.13
 */
public class PlayBaseTemplateLineMarkerProvider implements LineMarkerProvider
{
  public static final GutterIconNavigationHandler<PsiElement> GUTTER_ICON_NAVIGATION_HANDLER = new GutterIconNavigationHandler<PsiElement>() {
    @Override
    public void navigate(MouseEvent mouseEvent, PsiElement psiElement) {
      PlayBaseTemplateFile templateFile = findTemplateFile(psiElement);
      if(templateFile != null) {
        templateFile.navigate(true);
      }
    }
  };

  @Nullable
  @Override
  public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement psiElement) {
    return null;
  }

  @Override
  public void collectSlowLineMarkers(@NotNull List<PsiElement> psiElements, @NotNull Collection<LineMarkerInfo> lineMarkerInfos) {
    for (PsiElement element : psiElements) {
      PlayBaseTemplateFile templateFile = findTemplateFile(element);
      if(templateFile != null) {
        LineMarkerInfo<PsiElement> lineMarkerInfo = new LineMarkerInfo<PsiElement>(element, element.getTextRange(), PlayJavaIcons.PlayLineMarker, Pass.UPDATE_OVERRIDEN_MARKERS,
            new ConstantFunction<PsiElement, String>("Navigate to template"), GUTTER_ICON_NAVIGATION_HANDLER, GutterIconRenderer.Alignment.LEFT);

        lineMarkerInfos.add(lineMarkerInfo);
      }
    }
  }

  private static PlayBaseTemplateFile findTemplateFile(PsiElement element) {
    if(element instanceof PsiIdentifier && element.getParent() instanceof PsiMethod)
    {
      PsiMethod method = (PsiMethod) element.getParent();

      final PsiClass containingClass = method.getContainingClass();

      if(PlayJavaUtil.isSuperController(containingClass) && method.hasModifierProperty(PsiModifier.STATIC) && method.hasModifierProperty(PsiModifier.PUBLIC))
      {
        assert containingClass != null;

        String qName = StringUtil.notNullize(containingClass.getQualifiedName());
        qName = qName.replace(".", "/");

        if(qName.startsWith("controllers"))
        {
          qName = qName.replace("controllers", "views");
        }

        final Module moduleForPsiElement = ModuleUtil.findModuleForPsiElement(element);
        if(moduleForPsiElement == null)
        {
          return null;
        }

        ModuleRootManager rootManager = ModuleRootManager.getInstance(moduleForPsiElement);
        PsiManager manager = PsiManager.getInstance(element.getProject());
        for(VirtualFile file : rootManager.getSourceRoots())
        {
          VirtualFile virtualFile = file.findFileByRelativePath(qName);
          if(virtualFile == null)
          {
            continue;
          }

          for(VirtualFile child : virtualFile.getChildren())
          {
            if(child.getNameWithoutExtension().equalsIgnoreCase(method.getName()))
            {
              PsiFile psiFile = manager.findFile(child);
              if(psiFile instanceof PlayBaseTemplateFile)
              {
                return (PlayBaseTemplateFile) psiFile;
              }
            }
          }
        }
      }
    }
    return null;
  }
}