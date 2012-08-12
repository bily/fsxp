// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableSorter.java

package org.lc0277lib.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TableSorter extends AbstractTableModel
{
    private class Row
        implements Comparable
    {

        public int compareTo(Object o)
        {
            int row1 = modelIndex;
            int row2 = ((Row)o).modelIndex;
            for(Iterator it = sortingColumns.iterator(); it.hasNext();)
            {
                Directive directive = (Directive)it.next();
                int column = directive.column;
                Object o1 = tableModel.getValueAt(row1, column);
                Object o2 = tableModel.getValueAt(row2, column);
                int comparison = 0;
                if(o1 == null && o2 == null)
                    comparison = 0;
                else
                if(o1 == null)
                    comparison = -1;
                else
                if(o2 == null)
                    comparison = 1;
                else
                    comparison = getComparator(column).compare(o1, o2);
                if(comparison != 0)
                    return directive.direction != -1 ? comparison : -comparison;
            }

            return 0;
        }

        private int modelIndex;
        final TableSorter this$0;


        public Row(int index)
        {
            super();
            this$0 = TableSorter.this;
            modelIndex = index;
        }
    }

    private class TableModelHandler
        implements TableModelListener
    {

        public void tableChanged(TableModelEvent e)
        {
            if(!isSorting())
            {
                clearSortingState();
                fireTableChanged(e);
                return;
            }
            if(e.getFirstRow() == -1)
            {
                cancelSorting();
                fireTableChanged(e);
                return;
            }
            int column = e.getColumn();
            if(e.getFirstRow() == e.getLastRow() && column != -1 && getSortingStatus(column) == 0 && modelToView != null)
            {
                int viewIndex = getModelToView()[e.getFirstRow()];
                fireTableChanged(new TableModelEvent(TableSorter.this, viewIndex, viewIndex, column, e.getType()));
                return;
            } else
            {
                clearSortingState();
                fireTableDataChanged();
                return;
            }
        }

        final TableSorter this$0;

        private TableModelHandler()
        {
            super();
			this$0 = TableSorter.this;
        }

        TableModelHandler(TableModelHandler tablemodelhandler)
        {
            this();
        }
    }

    private class MouseHandler extends java.awt.event.MouseAdapter
    {

        public void mouseClicked(java.awt.event.MouseEvent e)
        {
            JTableHeader h = (JTableHeader)e.getSource();
            TableColumnModel columnModel = h.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = columnModel.getColumn(viewColumn).getModelIndex();
            if(column != -1)
            {
                int status = getSortingStatus(column);
                if(!e.isControlDown())
                    cancelSorting();
                status += e.isShiftDown() ? -1 : 1;
                status = (status + 4) % 3 - 1;
                setSortingStatus(column, status);
            }
        }

        final TableSorter this$0;

        private MouseHandler()
        {
            super();
			this$0 = TableSorter.this;
        }

        MouseHandler(MouseHandler mousehandler)
        {
            this();
        }
    }

    private static class Arrow
        implements Icon
    {

        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y)
        {
            java.awt.Color color = c != null ? c.getBackground() : java.awt.Color.GRAY;
            int dx = (int)((double)(size / 2) * Math.pow(0.80000000000000004D, priority));
            int dy = descending ? dx : -dx;
            y = y + (5 * size) / 6 + (descending ? -dy : 0);
            int shift = descending ? 1 : -1;
            g.translate(x, y);
            g.setColor(color.darker());
            g.drawLine(dx / 2, dy, 0, 0);
            g.drawLine(dx / 2, dy + shift, 0, shift);
            g.setColor(color.brighter());
            g.drawLine(dx / 2, dy, dx, 0);
            g.drawLine(dx / 2, dy + shift, dx, shift);
            if(descending)
                g.setColor(color.darker().darker());
            else
                g.setColor(color.brighter().brighter());
            g.drawLine(dx, 0, 0, 0);
            g.setColor(color);
            g.translate(-x, -y);
        }

        public int getIconWidth()
        {
            return size;
        }

        public int getIconHeight()
        {
            return size;
        }

        private boolean descending;
        private int size;
        private int priority;

        public Arrow(boolean descending, int size, int priority)
        {
            this.descending = descending;
            this.size = size;
            this.priority = priority;
        }
    }

    private class SortableHeaderRenderer
        implements TableCellRenderer
    {

        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            java.awt.Component c = tableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(c instanceof JLabel)
            {
                JLabel l = (JLabel)c;
                l.setHorizontalTextPosition(2);
                int modelColumn = table.convertColumnIndexToModel(column);
                l.setIcon(getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
            }
            return c;
        }

        private TableCellRenderer tableCellRenderer;
        final TableSorter this$0;


        public SortableHeaderRenderer(TableCellRenderer tableCellRenderer)
        {
            super();
            this$0 = TableSorter.this;
            this.tableCellRenderer = tableCellRenderer;
        }
    }

    private static class Directive
    {

        private int column;
        private int direction;



        public Directive(int column, int direction)
        {
            this.column = column;
            this.direction = direction;
        }
    }


    public TableSorter()
    {
        columnComparators = new HashMap();
        sortingColumns = new ArrayList();
        mouseListener = new MouseHandler(null);
        tableModelListener = new TableModelHandler(null);
    }

    public TableSorter(TableModel tableModel)
    {
        this();
        setTableModel(tableModel);
    }

    public TableSorter(TableModel tableModel, JTableHeader tableHeader)
    {
        this();
        setTableHeader(tableHeader);
        setTableModel(tableModel);
    }

    private void clearSortingState()
    {
        viewToModel = null;
        modelToView = null;
    }

    public TableModel getTableModel()
    {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel)
    {
        if(this.tableModel != null)
            this.tableModel.removeTableModelListener(tableModelListener);
        this.tableModel = tableModel;
        if(this.tableModel != null)
            this.tableModel.addTableModelListener(tableModelListener);
        clearSortingState();
        fireTableStructureChanged();
    }

    public JTableHeader getTableHeader()
    {
        return tableHeader;
    }

    public void setTableHeader(JTableHeader tableHeader)
    {
        if(this.tableHeader != null)
        {
            this.tableHeader.removeMouseListener(mouseListener);
            TableCellRenderer defaultRenderer = this.tableHeader.getDefaultRenderer();
            if(defaultRenderer instanceof SortableHeaderRenderer)
                this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)defaultRenderer).tableCellRenderer);
        }
        this.tableHeader = tableHeader;
        if(this.tableHeader != null)
        {
            this.tableHeader.addMouseListener(mouseListener);
            this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
        }
    }

    public boolean isSorting()
    {
        return sortingColumns.size() != 0;
    }

    private Directive getDirective(int column)
    {
        for(int i = 0; i < sortingColumns.size(); i++)
        {
            Directive directive = (Directive)sortingColumns.get(i);
            if(directive.column == column)
                return directive;
        }

        return EMPTY_DIRECTIVE;
    }

    public int getSortingStatus(int column)
    {
        return getDirective(column).direction;
    }

    private void sortingStatusChanged()
    {
        clearSortingState();
        fireTableDataChanged();
        if(tableHeader != null)
            tableHeader.repaint();
    }

    public void setSortingStatus(int column, int status)
    {
        Directive directive = getDirective(column);
        if(directive != EMPTY_DIRECTIVE)
            sortingColumns.remove(directive);
        if(status != 0)
            sortingColumns.add(new Directive(column, status));
        sortingStatusChanged();
    }

    protected Icon getHeaderRendererIcon(int column, int size)
    {
        Directive directive = getDirective(column);
        if(directive == EMPTY_DIRECTIVE)
            return null;
        else
            return new Arrow(directive.direction == -1, size, sortingColumns.indexOf(directive));
    }

    private void cancelSorting()
    {
        sortingColumns.clear();
        sortingStatusChanged();
    }

    public void setColumnComparator(Class type, Comparator comparator)
    {
        if(comparator == null)
            columnComparators.remove(type);
        else
            columnComparators.put(type, comparator);
    }

    protected Comparator getComparator(int column)
    {
        Class columnType = tableModel.getColumnClass(column);
        Comparator comparator = (Comparator)(Comparator)columnComparators.get(columnType);
        if(comparator != null)
            return comparator;
        if(java.lang.Comparable.class.isAssignableFrom(columnType))
            return COMPARABLE_COMAPRATOR;
        else
            return LEXICAL_COMPARATOR;
    }

    private Row[] getViewToModel()
    {
        if(viewToModel == null)
        {
            int tableModelRowCount = tableModel.getRowCount();
            viewToModel = new Row[tableModelRowCount];
            for(int row = 0; row < tableModelRowCount; row++)
                viewToModel[row] = new Row(row);

            if(isSorting())
                Arrays.sort(viewToModel);
        }
        return viewToModel;
    }

    public int modelIndex(int viewIndex)
    {
        return getViewToModel()[viewIndex].modelIndex;
    }

    public int[] getModelToView()
    {
        if(modelToView == null)
        {
            int n = getViewToModel().length;
            modelToView = new int[n];
            for(int i = 0; i < n; i++)
                modelToView[modelIndex(i)] = i;

        }
        return modelToView;
    }

    public int getRowCount()
    {
        return tableModel != null ? tableModel.getRowCount() : 0;
    }

    public int getColumnCount()
    {
        return tableModel != null ? tableModel.getColumnCount() : 0;
    }

    public String getColumnName(int column)
    {
        return tableModel.getColumnName(column);
    }

    public Class getColumnClass(int column)
    {
        return tableModel.getColumnClass(column);
    }

    public boolean isCellEditable(int row, int column)
    {
        return tableModel.isCellEditable(modelIndex(row), column);
    }

    public Object getValueAt(int row, int column)
    {
        return tableModel.getValueAt(modelIndex(row), column);
    }

    public void setValueAt(Object aValue, int row, int column)
    {
        tableModel.setValueAt(aValue, modelIndex(row), column);
    }

    protected TableModel tableModel;
    public static final int DESCENDING = -1;
    public static final int NOT_SORTED = 0;
    public static final int ASCENDING = 1;
    private static Directive EMPTY_DIRECTIVE = new Directive(-1, 0);
    public static final Comparator COMPARABLE_COMAPRATOR = new Comparator() {

        public int compare(Object o1, Object o2)
        {
            return ((Comparable)o1).compareTo(o2);
        }

    }
;
    public static final Comparator LEXICAL_COMPARATOR = new Comparator() {

        public int compare(Object o1, Object o2)
        {
            return o1.toString().compareTo(o2.toString());
        }

    }
;
    private Row viewToModel[];
    private int modelToView[];
    private JTableHeader tableHeader;
    private java.awt.event.MouseListener mouseListener;
    private TableModelListener tableModelListener;
    private Map columnComparators;
    private List sortingColumns;





}
