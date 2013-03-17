package org.napile.playJava4idea.facet;

import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.napile.playJava4idea.PlayJavaConstants;
import org.napile.playJava4idea.icons.PlayJavaIcons;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

/**
 * @author VISTALL
 * @since 15:45/17.03.13
 */
public class PlayJavaFacetEditorPanel extends JPanel
{
	private JPanel root;
	private TextFieldWithBrowseButton playPath;

	public PlayJavaFacetEditorPanel(PlayJavaFacet facet, PlayJavaFacetConfiguration configuration)
	{
		$$$setupUI$$$();
		playPath.addBrowseFolderListener(null, "Choose play framework", null, new FileChooserDescriptor(false, true, false, false, false, false)
		{
			@Override
			public boolean isFileSelectable(VirtualFile file)
			{
				if(!super.isFileSelectable(file))
				{
					return false;
				}

				final VirtualFile frameworkDir = file.findChild("framework");
				if(frameworkDir == null || !frameworkDir.isDirectory())
				{
					return false;
				}

				for(VirtualFile child : frameworkDir.getChildren())
				{
					if(PlayJavaConstants.JAR_PATTERN.matcher(child.getName()).find())
					{
						return true;
					}
				}
				return false;
			}

			@Override
			public Icon getIcon(VirtualFile file)
			{
				if(isFileSelectable(file))
				{
					return PlayJavaIcons.ICON_16x16;
				}
				return super.getIcon(file);
			}
		});
	}

	public void setPlayPath(String text)
	{
		playPath.getTextField().setText(text);
	}

	public String getPlayPath()
	{
		return playPath.getTextField().getText();
	}


	private void createUIComponents()
	{
		root = this;
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$()
	{
		createUIComponents();
		root.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		root.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Play path:");
		panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		playPath = new TextFieldWithBrowseButton();
		panel1.add(playPath, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		root.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$()
	{
		return root;
	}
}
