package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MyDocument extends PlainDocument {
    int maxLen = 10;

    public MyDocument(int maxLen) {
        this.maxLen = maxLen;
    }

    public MyDocument() {
        this(10);
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offs, str, a);
    }
}
