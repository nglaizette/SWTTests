/*
 * Copyright (C) 1978-@year@ by ME.
 * All rights reserved.
 *
 */
package com.example.swt.widgets;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * NG-Group <br>
 * Infrastructure Project <br>
 * <p>
 * FormLayoutExample:
 * <p>
 * Created on 13 juin 2016
 *
 * @author NG
 */
public class FormLayoutExample {
    Display d;

    Shell s;

    FormLayoutExample() {
        d = new Display();
        s = new Shell(d);
        s.setSize(250, 250);

        s.setText("A FormLayout Example");
        FormLayout formlayout = new FormLayout();
        formlayout.marginHeight = 50;
        formlayout.marginWidth = 5;

        s.setLayout(formlayout);

        final Label l1 = new Label(s, SWT.RIGHT);
        FontDescriptor boldDescriptor = FontDescriptor.createFrom(l1.getFont()).setStyle(SWT.BOLD);
        Font boldFont = boldDescriptor.createFont(l1.getDisplay());
        l1.setFont(boldFont);
        l1.setText("First Name super super long super long");
        FormData fd = new FormData();
        fd.top = new FormAttachment(10, 10);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(30, 0);
        fd.right = new FormAttachment(40, 0);
        l1.setLayoutData(fd);

        final Label l2 = new Label(s, SWT.RIGHT);
        l2.setText("Last Name");
        fd = new FormData();
        fd.top = new FormAttachment(l1, 5);
        fd.left = new FormAttachment(0, 10);
        fd.bottom = new FormAttachment(40, 0);
        fd.right = new FormAttachment(40, 0);
        l2.setLayoutData(fd);

        final Text t1 = new Text(s, SWT.BORDER | SWT.SINGLE);
        fd = new FormData();
        fd.top = new FormAttachment(l1, 0, SWT.TOP);
        fd.left = new FormAttachment(l1, 10);
        t1.setLayoutData(fd);

        final Text t2 = new Text(s, SWT.BORDER | SWT.SINGLE);
        fd = new FormData();
        fd.top = new FormAttachment(l2, 0, SWT.TOP);
        fd.left = new FormAttachment(l2, 10);
        t2.setLayoutData(fd);

        s.open();
        while (!s.isDisposed()) {
            if (!d.readAndDispatch()) {
                d.sleep();
            }
        }
        d.dispose();
    }

    public static void main(String[] argv) {
        new FormLayoutExample();
    }
}
