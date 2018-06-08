package JComPz;

import dao.Dao;
import model.BookType;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BookMap {
    private static Map<Integer, BookType> map = new HashMap<>();
    private static Map<String, BookType> map2 = new HashMap<>();

    static {
        Vector<BookType> vector = Dao.selectBookCategory();
        for (BookType bookType : vector) {
            map.put(bookType.getId(), bookType);
            map2.put(bookType.getTypeName(), bookType);
        }
    }

    public static String getTypeName(int id) {
        return map.get(id).getTypeName();
    }

    public static BookType getType(int id) {
        return map.get(id);
    }

    public static BookType getType(String typeName) {
        return map2.get(typeName);
    }

}
