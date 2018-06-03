package main;

import iframe.BookAddIFrame;
import iframe.BookModiAndDelFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MenuActions {
    private static Map<String, JInternalFrame> frames = new HashMap<>();
    static BookAddAction BOOK_ADD;
    static BookModiAndDelAction BOOK_MODI_AND_DEL;
    static ExitAction EXIT;

    static {
        BOOK_ADD = new BookAddAction();
        BOOK_MODI_AND_DEL = new BookModiAndDelAction();
        EXIT = new ExitAction();
    }

    /**
     * 新的图书添加动作.
     */
    private static class BookAddAction extends AbstractAction {
        BookAddAction() {
            super("图书信息添加");
            putValue(Action.LONG_DESCRIPTION, "为图书馆添加新的图书信息");
            putValue(Action.SHORT_DESCRIPTION, "图书信息添加");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书信息添加") || frames.get("图书信息添加").isClosed()) {
                JInternalFrame bookInforAdd = new BookAddIFrame();
                frames.put("图书信息添加", bookInforAdd);
                Library.addIFrame(bookInforAdd);
            }
        }
    }

    private static class BookModiAndDelAction extends AbstractAction {
        BookModiAndDelAction() {
            super("图书信息修改和删除");
            putValue(Action.LONG_DESCRIPTION, "图书的信息修改和删除");
            putValue(Action.SHORT_DESCRIPTION, "图书信息修改和删除");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书信息修改和添加") || frames.get("图书信息修改和添加").isClosed()) {
                JInternalFrame bookModiAndDel = new BookModiAndDelFrame();
                frames.put("图书信息修改和添加", bookModiAndDel);
                Library.addIFrame(bookModiAndDel);
            }
        }
    }

    private static class ExitAction extends AbstractAction {
        public ExitAction() {
            super("退出图书馆管理系统");
            putValue(LONG_DESCRIPTION, "退出图书馆管理系统界面");
            putValue(SHORT_DESCRIPTION, "退出系统");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
