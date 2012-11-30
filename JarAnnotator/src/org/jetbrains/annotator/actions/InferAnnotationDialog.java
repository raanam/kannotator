package org.jetbrains.annotator.actions;

import com.intellij.ide.util.treeView.AbstractTreeBuilder;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryTreeStructure;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.ui.TitledSeparator;
import com.intellij.ui.treeStructure.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.intellij.util.ui.classpath.ChooseLibrariesDialogBase;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class InferAnnotationDialog extends DialogWrapper {
    private JPanel contentPanel;
    private JCheckBox nullabilityCheckBox;
    private JCheckBox kotlinSignaturesCheckBox;
    private TextFieldWithBrowseButton outputDirectory;
    JScrollPane chooseJarPane;
    private SimpleTree libraryTree;

    public InferAnnotationDialog(@Nullable Project project) {
        super(project);
        $$$setupUI$$$();
        setTitle("Annotate Jar Files");

        outputDirectory.addBrowseFolderListener(
                RefactoringBundle.message("select.target.directory"),
                "Output annotation will be written to this folder",
                null, FileChooserDescriptorFactory.createSingleFolderDescriptor());

        init();
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return libraryTree;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        AbstractTreeBuilder treeBuilder = new SimpleTreeBuilder(libraryTree, new DefaultTreeModel(new DefaultMutableTreeNode()),
                new SimpleTreeStructure() {
                    final SimpleNode node = new NullNode();

                    @Override
                    public Object getRootElement() {
                        return node;
                    }
                },
                WeightBasedComparator.FULL_INSTANCE);

        treeBuilder.initRootNode();

        libraryTree.setDragEnabled(false);
        libraryTree.setShowsRootHandles(true);
        libraryTree.setRootVisible(false);

        return contentPanel;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        final Spacer spacer1 = new Spacer();
        contentPanel.add(spacer1, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        nullabilityCheckBox = new JCheckBox();
        nullabilityCheckBox.setText("Nullability");
        nullabilityCheckBox.setMnemonic('N');
        nullabilityCheckBox.setDisplayedMnemonicIndex(0);
        contentPanel.add(nullabilityCheckBox, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        kotlinSignaturesCheckBox = new JCheckBox();
        kotlinSignaturesCheckBox.setText("Kotlin Signatures");
        kotlinSignaturesCheckBox.setMnemonic('K');
        kotlinSignaturesCheckBox.setDisplayedMnemonicIndex(0);
        contentPanel.add(kotlinSignaturesCheckBox, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Output directory:");
        contentPanel.add(label1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputDirectory = new TextFieldWithBrowseButton();
        contentPanel.add(outputDirectory, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final TitledSeparator titledSeparator1 = new TitledSeparator();
        titledSeparator1.setText("Annotation Types");
        contentPanel.add(titledSeparator1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final TitledSeparator titledSeparator2 = new TitledSeparator();
        titledSeparator2.setText("Output");
        contentPanel.add(titledSeparator2, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Select jar files for annotation inferring: ");
        contentPanel.add(label2, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chooseJarPane = new JScrollPane();
        contentPanel.add(chooseJarPane, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        libraryTree = new SimpleTree();
        libraryTree.putClientProperty("JTree.lineStyle", "Angled");
        chooseJarPane.setViewportView(libraryTree);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPanel;
    }
}