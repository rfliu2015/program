package main;

import iframe.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActions {
    private static Map<String, JInternalFrame> frames = new HashMap<>();
    static BookAddAction BOOK_ADD;
    static BookBorrowAction BOOK_BORROW;
    static BookModiAction BOOK_MODI;
    static BookTypeAddAction BOOK_TYPE_ADD;
    static BookTypeModiAction BOOK_TYPE_MODI;
    static ReaderAddAction READER_ADD;
    static ReaderModiAndDelAction READER_MODI_AND_DEL;
    static ExitAction EXIT;

    static {
        BOOK_ADD = new BookAddAction();
        BOOK_BORROW = new BookBorrowAction();
        BOOK_MODI = new BookModiAction();
        BOOK_TYPE_ADD = new BookTypeAddAction();
        BOOK_TYPE_MODI = new BookTypeModiAction();
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

    private static class BookBorrowAction extends AbstractAction {
        public BookBorrowAction() {
            super("图书借阅管理");
            putValue(LONG_DESCRIPTION, "图书借阅管理");
            putValue(SHORT_DESCRIPTION, "图书借阅管理");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书借阅管理") || frames.get("图书借阅管理").isClosed()) {
                BookBorrowIFrame bookBorrow = new BookBorrowIFrame();
                frames.put("图书借阅管理", bookBorrow);
                Library.addIFrame(bookBorrow);
                bookBorrow.setVisible(true);
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

    private static class BookTypeAddAction extends AbstractAction {
        public BookTypeAddAction() {
            super("图书类型添加");
            putValue(LONG_DESCRIPTION, "图书类型添加");
            putValue(SHORT_DESCRIPTION, "图书类型添加");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书类型添加") || frames.get("图书类型添加").isClosed()) {
                BookTypeAddIFrame bookTypeAdd = new BookTypeAddIFrame();
                frames.put("图书类型添加", bookTypeAdd);
                Library.addIFrame(bookTypeAdd);
                bookTypeAdd.setVisible(true);
            }
        }
    }

    private static class BookTypeModiAction extends AbstractAction {
        public BookTypeModiAction() {
            super("图书类别修改");
            putValue(LONG_DESCRIPTION, "图书类别修改");
            putValue(SHORT_DESCRIPTION, "图书类别修改");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!frames.containsKey("图书类别修改") || frames.get("图书类别修改").isClosed()) {
                BookTypeModiIFrame bookTypeModi = new BookTypeModiIFrame();
                frames.put("图书类别修改", bookTypeModi);
                Library.addIFrame(bookTypeModi);
                bookTypeModi.setVisible(true);
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
