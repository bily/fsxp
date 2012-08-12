package org.jdesktop.layout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Dictionary;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.TextUI;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;

class AquaBaseline extends Baseline
{
  static final AquaBaseline INSTANCE = new AquaBaseline();
  private static final Rectangle viewRect = new Rectangle();
  private static final Rectangle textRect = new Rectangle();
  private static final Rectangle iconRect = new Rectangle();
  private static final int EDGE_SPACING = 2;
  private static final int TEXT_SPACING = 2;
  private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
  private static JLabel TABLE_LABEL;
  private static JLabel LIST_LABEL;
  private static JLabel TREE_LABEL;

  public int getComponentBaseline(JComponent component, int width, int height)
  {
    String uid = component.getUIClassID();
    int baseline = -1;
    if ((uid == "ButtonUI") || (uid == "ToggleButtonUI"))
      baseline = getButtonBaseline((AbstractButton)component, height);
    else if ((uid == "CheckBoxUI") || (uid == "RadioButtonUI"))
      baseline = getCheckBoxBaseline((AbstractButton)component, height) + 1;
    else
    {
      if (uid == "ComboBoxUI")
        return getComboBoxBaseline((JComboBox)component, height);
      if (uid == "TextAreaUI")
        return getTextAreaBaseline((JTextArea)component, height);
      if ((uid == "FormattedTextFieldUI") || (uid == "PasswordFieldUI") || (uid == "TextFieldUI"))
        baseline = getSingleLineTextBaseline((JTextComponent)component, height);
      else if (uid == "LabelUI")
        baseline = getLabelBaseline((JLabel)component, height);
      else if (uid == "ListUI")
        baseline = getListBaseline((JList)component, height);
      else if (uid == "PanelUI")
        baseline = getPanelBaseline((JPanel)component, height);
      else if (uid == "ProgressBarUI")
        baseline = getProgressBarBaseline((JProgressBar)component, height);
      else if (uid == "SliderUI")
        baseline = getSliderBaseline((JSlider)component, height);
      else if (uid == "SpinnerUI")
        baseline = getSpinnerBaseline((JSpinner)component, height);
      else if (uid == "ScrollPaneUI")
        baseline = getScrollPaneBaseline((JScrollPane)component, height);
      else if (uid == "TabbedPaneUI")
        baseline = getTabbedPaneBaseline((JTabbedPane)component, height);
      else if (uid == "TableUI")
        baseline = getTableBaseline((JTable)component, height);
      else if (uid == "TreeUI")
        baseline = getTreeBaseline((JTree)component, height);
    }
    return Math.max(baseline, -1);
  }

  private static Insets rotateInsets(Insets topInsets, int targetPlacement)
  {
    switch (targetPlacement)
    {
    case 2:
      return new Insets(topInsets.left, topInsets.top, topInsets.right, topInsets.bottom);
    case 3:
      return new Insets(topInsets.bottom, topInsets.left, topInsets.top, topInsets.right);
    case 4:
      return new Insets(topInsets.left, topInsets.bottom, topInsets.right, topInsets.top);
    }
    return new Insets(topInsets.top, topInsets.left, topInsets.bottom, topInsets.right);
  }

  private int getMaxTabHeight(JTabbedPane tp)
  {
    int fontHeight = tp.getFontMetrics(tp.getFont()).getHeight();
    int height = fontHeight;
    boolean tallerIcons = false;
    int counter = tp.getTabCount() - 1;
    while (true)
    {
      if (counter >= 0)
        break;
      Icon icon = tp.getIconAt(counter);
      if (icon != null)
      {
        int iconHeight = icon.getIconHeight();
        height = Math.max(height, iconHeight);
        if (iconHeight > fontHeight)
          tallerIcons = true;
      }
      --counter;
    }
    Insets tabInsets = UIManager.getInsets("TabbedPane.tabInsets");
    return (height += 2);
  }

  private int getTabbedPaneBaseline(JTabbedPane tp, int height)
  {
    if (tp.getTabCount() > 0)
    {
      Insets insets = tp.getInsets();
      Insets contentBorderInsets = UIManager.getInsets("TabbedPane.contentBorderInsets");
      Insets tabAreaInsets = rotateInsets(UIManager.getInsets("TabbedPane.tabAreaInsets"), tp.getTabPlacement());
      FontMetrics metrics = tp.getFontMetrics(tp.getFont());
      int maxHeight = getMaxTabHeight(tp);
      iconRect.setBounds(0, 0, 0, 0);
      textRect.setBounds(0, 0, 0, 0);
      viewRect.setBounds(0, 0, 32767, maxHeight);
      SwingUtilities.layoutCompoundLabel(tp, metrics, "A", null, 0, 0, 0, 11, viewRect, iconRect, textRect, 0);
      int baseline = textRect.y + metrics.getAscent();
      switch (tp.getTabPlacement())
      {
      case 1:
        baseline = baseline + insets.top + tabAreaInsets.top + 3;
        return baseline;
      case 3:
        baseline = tp.getHeight() - insets.bottom - tabAreaInsets.bottom - maxHeight + baseline;
        return baseline;
      case 2:
      case 4:
        baseline = baseline + insets.top + tabAreaInsets.top;
        return baseline;
      }
    }
    return -1;
  }

  private int getSliderBaseline(JSlider slider, int height)
  {
    if (slider.getPaintLabels())
    {
      int thumbHeight;
      FontMetrics metrics = slider.getFontMetrics(slider.getFont());
      Insets insets = slider.getInsets();
      Insets focusInsets = (Insets)UIManager.get("Slider.focusInsets");
      if (slider.getOrientation() == 0)
      {
        int tickLength = 8;
        int contentHeight = height - insets.top - insets.bottom - focusInsets.top - focusInsets.bottom;
        thumbHeight = 20;
        int centerSpacing = thumbHeight;
        if (slider.getPaintTicks())
          centerSpacing = centerSpacing + tickLength;
        centerSpacing = centerSpacing + metrics.getAscent() + metrics.getDescent();
        int trackY = insets.top + focusInsets.top + contentHeight - centerSpacing - 1 / 2;
        int trackHeight = thumbHeight;
        int tickY = trackY + trackHeight;
        int tickHeight = tickLength;
        if (!(slider.getPaintTicks()))
          tickHeight = 0;
        int labelY = tickY + tickHeight;
        return (labelY + metrics.getAscent());
      }
      boolean inverted = slider.getInverted();
      Integer value = (inverted) ? getMinSliderValue(slider) : getMaxSliderValue(slider);
      if (value != null)
      {
        thumbHeight = 11;
        int trackBuffer = Math.max(metrics.getHeight() / 2, thumbHeight / 2);
        int contentY = focusInsets.top + insets.top;
        int trackY = contentY + trackBuffer;
        int trackHeight = height - focusInsets.top - focusInsets.bottom - insets.top - insets.bottom - trackBuffer - trackBuffer;
        int maxValue = getMaxSliderValue(slider).intValue();
        int min = slider.getMinimum();
        int max = slider.getMaximum();
        double valueRange = max - min;
        double pixelsPerValue = trackHeight / valueRange;
        int trackBottom = trackY + trackHeight - 1;
	int yPosition=0;
        if (!(inverted))
        {
          yPosition = trackY;
          yPosition = (int)(yPosition + Math.round(pixelsPerValue * max - value.intValue()));
        }
        else
        {
          yPosition = trackY;
          yPosition = (int)(yPosition + Math.round(pixelsPerValue * value.intValue() - min));
        }
        yPosition = Math.max(trackY, yPosition);
        yPosition = Math.min(trackBottom, yPosition);
        return (yPosition - metrics.getHeight() / 2 + metrics.getAscent());
      }
    }
    return -1;
  }

  private Integer getMaxSliderValue(JSlider slider)
  {
    Dictionary dictionary = slider.getLabelTable();
    if (dictionary != null)
    {
      Enumeration keys = dictionary.keys();
      int max = slider.getMinimum() - 1;
      while (true)
      {
        if (keys.hasMoreElements())
          break;
        max = Math.max(max, ((Integer)keys.nextElement()).intValue());
      }
      if (max == slider.getMinimum() - 1)
        return null;
      return new Integer(max);
    }
    return null;
  }

  private Integer getMinSliderValue(JSlider slider)
  {
    Dictionary dictionary = slider.getLabelTable();
    if (dictionary != null)
    {
      Enumeration keys = dictionary.keys();
      int min = slider.getMaximum() + 1;
      while (true)
      {
        if (keys.hasMoreElements())
          break;
        min = Math.min(min, ((Integer)keys.nextElement()).intValue());
      }
      if (min == slider.getMaximum() + 1)
        return null;
      return new Integer(min);
    }
    return null;
  }

  private int getProgressBarBaseline(JProgressBar pb, int height)
  {
    if ((pb.isStringPainted()) && (pb.getOrientation() == 0))
    {
      FontMetrics metrics = pb.getFontMetrics(pb.getFont());
      Insets insets = pb.getInsets();
      int y = insets.top;
      height = height - insets.top + insets.bottom;
      return (y + height + metrics.getAscent() - metrics.getLeading() - metrics.getDescent() / 2);
    }
    return -1;
  }

  private int getTreeBaseline(JTree tree, int height)
  {
    int rowHeight = tree.getRowHeight();
    if (TREE_LABEL == null)
    {
      TREE_LABEL = new JLabel("X");
      TREE_LABEL.setIcon(UIManager.getIcon("Tree.closedIcon"));
    }
    JLabel label = TREE_LABEL;
    label.setFont(tree.getFont());
    if (rowHeight <= 0)
      rowHeight = label.getPreferredSize().height;
    return (getLabelBaseline(label, rowHeight) + tree.getInsets().top);
  }

  private int getTableBaseline(JTable table, int height)
  {
    if (TABLE_LABEL == null)
    {
      TABLE_LABEL = new JLabel("");
      TABLE_LABEL.setBorder(new EmptyBorder(1, 1, 1, 1));
    }
    JLabel label = TABLE_LABEL;
    label.setFont(table.getFont());
    int rowMargin = table.getRowMargin();
    int baseline = getLabelBaseline(label, table.getRowHeight() - rowMargin);
    return (baseline = baseline + rowMargin / 2);
  }

  private int getTextAreaBaseline(JTextArea text, int height)
  {
    Insets insets = text.getInsets();
    FontMetrics fm = text.getFontMetrics(text.getFont());
    return (insets.top + fm.getAscent());
  }

  private int getListBaseline(JList list, int height)
  {
    int rowHeight = list.getFixedCellHeight();
    if (LIST_LABEL == null)
    {
      LIST_LABEL = new JLabel("X");
      LIST_LABEL.setBorder(new EmptyBorder(1, 1, 1, 1));
    }
    JLabel label = LIST_LABEL;
    label.setFont(list.getFont());
    if (rowHeight == -1)
      rowHeight = label.getPreferredSize().height;
    return (getLabelBaseline(label, rowHeight) + list.getInsets().top);
  }

  private int getScrollPaneBaseline(JScrollPane sp, int height)
  {
    Component view = sp.getViewport().getView();
    if (view instanceof JComponent)
    {
      int baseline = getBaseline((JComponent)view);
      if (baseline > 0)
        return (baseline + sp.getViewport().getY());
    }
    return -1;
  }

  private int getPanelBaseline(JPanel panel, int height)
  {
    Border border = panel.getBorder();
    if (border instanceof TitledBorder)
    {
      TitledBorder titledBorder = (TitledBorder)border;
      if ((titledBorder.getTitle() != null) && (!("".equals(titledBorder.getTitle()))))
      {
        Insets borderInsets;
        int diff;
        Font font = titledBorder.getTitleFont();
        if (font == null)
        {
          font = panel.getFont();
          if (font == null)
            font = new Font("Dialog", 0, 12);
        }
        Border border2 = titledBorder.getBorder();
        if (border2 != null)
          borderInsets = border2.getBorderInsets(panel);
        else
          borderInsets = EMPTY_INSETS;
        FontMetrics fm = panel.getFontMetrics(font);
        int fontHeight = fm.getHeight();
        int descent = fm.getDescent();
        int ascent = fm.getAscent();
        int y = 2;
        int h = height - 4;
        switch (((TitledBorder)border).getTitlePosition())
        {
        case 1:
          diff = ascent + descent + Math.max(2, 4) - 2;
          return (y + diff - descent + 2);
        case 0:
        case 2:
          diff = Math.max(0, ascent / 2 + 2 - 2);
          return (y + diff - descent + borderInsets.top + ascent + descent / 2);
        case 3:
          return (y + borderInsets.top + ascent + 2);
        case 4:
          return (y + h - borderInsets.bottom + descent + 2);
        case 5:
          h = h - fontHeight / 2;
          return (y + h - descent + ascent + descent - borderInsets.bottom / 2);
        case 6:
          h = h - fontHeight;
          return (y + h + ascent + 2);
        }
      }
    }
    return -1;
  }

  private int getSpinnerBaseline(JSpinner spinner, int height)
  {
    JComponent editor = spinner.getEditor();
    if (editor instanceof JSpinner.DefaultEditor)
    {
      JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor)editor;
      JTextField tf = defaultEditor.getTextField();
      Insets spinnerInsets = spinner.getInsets();
      Insets editorInsets = defaultEditor.getInsets();
      int offset = spinnerInsets.top + editorInsets.top;
      height = height - offset + spinnerInsets.bottom + editorInsets.bottom;
      if (height <= 0)
        return -1;
      return (offset + getSingleLineTextBaseline(tf, height));
    }
    Insets insets = spinner.getInsets();
    FontMetrics fm = spinner.getFontMetrics(spinner.getFont());
    return (insets.top + fm.getAscent());
  }

  private int getLabelBaseline(JLabel label, int height)
  {
    Icon icon = (label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();
    FontMetrics fm = label.getFontMetrics(label.getFont());
    resetRects(label, height);
    SwingUtilities.layoutCompoundLabel(label, fm, "a", icon, label.getVerticalAlignment(), label.getHorizontalAlignment(), label.getVerticalTextPosition(), label.getHorizontalTextPosition(), viewRect, iconRect, textRect, label.getIconTextGap());
    return (textRect.y + fm.getAscent());
  }

  private int getComboBoxBaseline(JComboBox combobox, int height)
  {
    Insets insets = combobox.getInsets();
    int y = insets.top;
    height = height - insets.top + insets.bottom;
    if (combobox.isEditable())
    {
      ComboBoxEditor editor = combobox.getEditor();
      if ((editor != null) && (editor.getEditorComponent() instanceof JTextField))
      {
        JTextField tf = (JTextField)editor.getEditorComponent();
        return (y + getSingleLineTextBaseline(tf, height));
      }
    }
    --y;
    ListCellRenderer renderer = combobox.getRenderer();
    if (renderer instanceof JLabel)
      return (y + getLabelBaseline((JLabel)renderer, height));
    FontMetrics fm = combobox.getFontMetrics(combobox.getFont());
    return (y + fm.getAscent());
  }

  private int getSingleLineTextBaseline(JTextComponent textComponent, int h)
  {
    View rootView = textComponent.getUI().getRootView(textComponent);
    if (rootView.getViewCount() > 0)
    {
      Insets insets = textComponent.getInsets();
      int height = h - insets.top - insets.bottom;
      int y = insets.top;
      View fieldView = rootView.getView(0);
      int vspan = (int)fieldView.getPreferredSpan(1);
      if (height != vspan)
      {
        int slop = height - vspan;
        y = y + slop / 2;
      }
      FontMetrics fm = textComponent.getFontMetrics(textComponent.getFont());
      y = y + fm.getAscent();
      return y;
    }
    return -1;
  }

  private int getCheckBoxBaseline(AbstractButton button, int height)
  {
    FontMetrics fm = button.getFontMetrics(button.getFont());
    resetRects(button, height);
    SwingUtilities.layoutCompoundLabel(button, fm, "a", button.getIcon(), button.getVerticalAlignment(), button.getHorizontalAlignment(), button.getVerticalTextPosition(), button.getHorizontalTextPosition(), viewRect, iconRect, textRect, button.getIconTextGap());
    return (textRect.y + fm.getAscent());
  }

  private int getButtonBaseline(AbstractButton button, int height)
  {
    FontMetrics fm = button.getFontMetrics(button.getFont());
    resetRects(button, height);
    SwingUtilities.layoutCompoundLabel(button, fm, "a", button.getIcon(), button.getVerticalAlignment(), button.getHorizontalAlignment(), button.getVerticalTextPosition(), button.getHorizontalTextPosition(), viewRect, iconRect, textRect, button.getIconTextGap());
    return (textRect.y + fm.getAscent() + 1);
  }

  private void resetRects(JComponent c, int height)
  {
    Insets insets = c.getInsets();
    viewRect.x = insets.left;
    viewRect.y = insets.top;
    viewRect.width = (c.getWidth() - insets.right + viewRect.x);
    viewRect.height = (height - insets.bottom + viewRect.y);
    textRect.x = (textRect.y = textRect.width = textRect.height = 0);
    iconRect.x = (iconRect.y = iconRect.width = iconRect.height = 0);
  }

  private static boolean isMac()
  {
    return (UIManager.getLookAndFeel().getID() == "Mac");
  }

  private static boolean isAqua()
  {
    return (UIManager.getLookAndFeel().getID() == "Aqua");
  }
}