/*
 * Copyright (C) 1978-@year@ by Me.
 * All rights reserved.
 *
 */
package com.example.swt.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * NG-Group <br>
 * Infrastructure Project <br>
 * <p>
 * FocusBorderCellHighlighter: Example of a different focus cell rendering with a simply focus
 * border
 * <p>
 * Created on 13 juin 2016
 *
 * @author glaizett
 */
public class FocusBorderCellHighlighter {

    private class MyModel {

        private double col1Value = 0.0;

        private double col2Value = 0.0;

        private double col3Value = 0.0;

        public MyModel(double value1, double value2, double value3) {
            this.col1Value = value1;
            this.col2Value = value2;
            this.col3Value = value3;
        }

        public void setValue1(double value) {
            this.col1Value = value;
        }

        public void setValue2(double value) {
            this.col2Value = value;
        }

        public void setValue3(double value) {
            this.col3Value = value;
        }

        public double getValue1() {
            return this.col1Value;
        }

        public double getValue2() {
            return this.col2Value;
        }

        public double getValue3() {
            return this.col3Value;
        }

        @Override
        public String toString() {
            return Double.toString(col1Value) + Double.toString(col2Value)
                    + Double.toString(col3Value);
        }
    }

    private class MyEditingSupport extends EditingSupport {

        private int columnIndex;

        public MyEditingSupport(ColumnViewer viewer, int columnIndex) {
            super(viewer);
            this.columnIndex = columnIndex;
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return new TextCellEditor((Composite) getViewer().getControl());
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            if (element instanceof MyModel) {
                MyModel mymodel = (MyModel) element;
                double value = 0.0;
                switch (columnIndex) {
                    case 0:
                        value = mymodel.getValue1();
                        break;
                    case 1:
                        value = mymodel.getValue2();
                        break;
                    case 2:
                        value = mymodel.getValue3();
                        break;

                    default:
                        break;
                }
                return Double.toString(value);
            }
            return "";
        }

        @Override
        protected void setValue(Object element, Object value) {
            System.out.println(value.toString());
            if (element instanceof MyModel) {
                MyModel myModel = (MyModel) element;

                try {
                    double theValue = Double.parseDouble(value.toString());

                    switch (columnIndex) {
                        case 0:
                            myModel.setValue1(theValue);
                            break;
                        case 1:
                            myModel.setValue2(theValue);
                            break;
                        case 2:
                            myModel.setValue3(theValue);
                            break;
                        default:
                            break;
                    }
                }
                catch (Exception e) {
                    // TODO: handle exception
                }
            }
            getViewer().refresh();
        }

    }

    private class MyColumnLabelProvider extends ColumnLabelProvider {

        private int columnIndex;

        public MyColumnLabelProvider(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        @Override
        public String getText(Object element) {
            if (element instanceof MyModel) {
                MyModel mymodel = (MyModel) element;
                double value = 0.0;
                switch (columnIndex) {
                    case 0:
                        value = mymodel.getValue1();
                        break;
                    case 1:
                        value = mymodel.getValue2();
                        break;
                    case 2:
                        value = mymodel.getValue3();
                        break;

                    default:
                        break;
                }
                return Double.toString(value);
            }
            return "";
        }
    }

    public FocusBorderCellHighlighter(Shell shell) {
        final TableViewer v = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
        v.setContentProvider(ArrayContentProvider.getInstance());

        TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(v,
                new FocusCellOwnerDrawHighlighter(v));
        ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(
                v) {
            @Override
            protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
                return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
                        || event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION
                        || (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
                        || event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
            }
        };

        int feature = ColumnViewerEditor.TABBING_HORIZONTAL
                | ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
                | ColumnViewerEditor.TABBING_VERTICAL | ColumnViewerEditor.KEYBOARD_ACTIVATION;

        TableViewerEditor.create(v, focusCellManager, actSupport, feature);

        String[] columLabels = { "Column 1", "Column 2", "Column 3" };
        int property = 0;
        for (String label : columLabels) {
            createColumnFor(v, label, property++);
        }
        v.setInput(createModel());
        v.getTable().setLinesVisible(true);
        v.getTable().setHeaderVisible(true);
    }

    private void createColumnFor(TableViewer v, String label, int columnIndex) {

        TableViewerColumn viewerColumn = new TableViewerColumn(v, SWT.NONE);
        viewerColumn.getColumn().setWidth(200);
        viewerColumn.getColumn().setMoveable(true);
        viewerColumn.getColumn().setText(label);

        viewerColumn.setEditingSupport(new MyEditingSupport(v, columnIndex));
        viewerColumn.setLabelProvider(new MyColumnLabelProvider(columnIndex));
    }

    private List<MyModel> createModel() {
        List<MyModel> elements = new ArrayList<MyModel>();

        for (int i = 0; i < 10; i++) {
            elements.add(new MyModel(i, 2 * i, 3 * i));
        }
        return elements;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Display display = new Display();

        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        new FocusBorderCellHighlighter(shell);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
