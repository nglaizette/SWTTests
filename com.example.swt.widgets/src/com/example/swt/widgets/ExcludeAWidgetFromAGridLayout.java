/*
 * Copyright (C) 1978-@year@ by me.
 * All rights reserved.
 *
 */
package com.example.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * NG-Group <br>
 * Infrastructure Project <br>
 * <p>
 * ExcludeAWidgetFromAGridLayout:
 * <p>
 * Created on 13 juin 2016
 *
 * @author NG
 */
public class ExcludeAWidgetFromAGridLayout {
    public static void main(String[] args) {

        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(3, false));

        Button b = new Button(shell, SWT.PUSH);
        b.setText("Button 0");
        GridData data0 = new GridData();
        data0.horizontalSpan = 3;
        data0.horizontalAlignment = SWT.FILL;
        b.setLayoutData(data0);

        final Button bHidden = new Button(shell, SWT.PUSH);
        bHidden.setText("Button 1");
        GridData data = new GridData();
        data.exclude = true;
        data.horizontalSpan = 3;
        data.horizontalAlignment = SWT.FILL;
        bHidden.setLayoutData(data);

        Button bh = new Button(shell, SWT.CHECK);
        bh.setText("hide");
        bh.setSelection(true);
        bh.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event e) {
                Button b = (Button) e.widget;
                GridData data = (GridData) bHidden.getLayoutData();
                data.exclude = b.getSelection();
                bHidden.setVisible(!data.exclude);
                shell.layout();
            }
        });

        b = new Button(shell, SWT.PUSH);
        b.setText("Button 2");
        b = new Button(shell, SWT.PUSH);
        b.setText("Button 3");
        b = new Button(shell, SWT.PUSH);
        b.setText("Button 4");

        shell.setSize(400, 400);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
