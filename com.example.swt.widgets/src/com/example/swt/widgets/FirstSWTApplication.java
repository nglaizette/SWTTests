/*
 * Copyright (C) 1978-@year@ by ME.
 * All rights reserved.
 *
 */
package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * NG-Group <br>
 * Infrastructure Project <br>
 * <p>
 * FirstSWTApplication:
 * <p>
 * Created on 13 juin 2016
 *
 * @author NG
 */
public class FirstSWTApplication {
    public static void main(String[] args) {
        Display display = new Display();

        Shell shell = new Shell(display);

        // the layout manager handle the layout
        // of the widgets in the container
        shell.setLayout(new FillLayout(SWT.VERTICAL));

        // Shell can be used as container
        Label label = new Label(shell, SWT.BORDER);
        label.setText("This is a label:");
        label.setToolTipText("This is the tooltip of this label");

        Text text = new Text(shell, SWT.NONE);
        text.setText("This is the text in the text widget");
        text.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

        Button button = new Button(shell, SWT.PUSH);

        // register listener for the selection event
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println("Called!");
            }
        });

        // set widgets size to their preferred size
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
