package main;

import iframe.BookAddIFrame;
import iframe.BookModiAndDelFrame;
import iframe.ReaderAddIFrame;
import iframe.ReaderModiAndDelIFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MenuActions {
    private static Map<String, JInternalFrame> frames = new HashMap<>();
    static BookAddAction BOOK_ADD;
    static BookModiAction BOOK_MODI;
    static ReaderAddAction READER_ADD;
    static ReaderModiAndDelAction READER_MODI_AND_DEL;
    static ExitAction EXIT;

    static {
        BOOK_ADD = new BookAddAction();
        BOOK_MODI = new BookModiAction();
        READER_ADD = new ReaderAddAction();
        READER_MODI_AND_DEL = new ReaderModiAndDelAction();
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
                bookInforAdd.setVisible(true);
            }
        }
    }

    private static class BookModiAction extends AbstractAction {
        BookModiAction() {
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
                bookModiAndDel.setVisible(true);
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

    private static class ReaderAddAction extends AbstractAction {
        public ReaderAddAction() {
            super("读者添加");
            putValue(LONG_DESCRIPTION, "读者相关信息添加");
            putValue(SHORT_DESCRIPTION, "读者添加");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("读者添加") || frames.get("读者添加").isClosed()) {
                JInternalFrame readerAdd = new ReaderAddIFrame();
                frames.put("读者添加", readerAdd);
                Library.addIFrame(readerAdd);
                readerAdd.setVisible(true);
            }
        }
    }

    private static class ReaderModiAndDelAction extends AbstractAction {
        ReaderModiAndDelAction() {
            super("读者信息修改与删除");
            putValue(LONG_DESCRIPTION, "读者信息修改与删除");
            putValue(SHORT_DESCRIPTION, "读者信息修改与删除");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("读者信息修改与删除") || frames.get("读者信息修改与删除").isClosed()) {
                JInternalFrame readerModiAndDel = new ReaderModiAndDelIFrame();
                frames.put("读者信息修改与删除", readerModiAndDel);
                Library.addIFrame(readerModiAndDel);
                readerModiAndDel.setVisible(true);
            }
        }
    }
}
