package JComPz;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ReaderMap {
    static Map<String, Integer> map = new HashMap<>();  //(证件类型, 编号)
    static Map<String, String> map2 = new HashMap<>();  //(证件类型, 工作)

    static {
        String[] cardTypes = new String[]{"学生证", "教师证"};
        String[] jobs = new String[]{"学生", "教师"};

        for (int t = 0; t < cardTypes.length; t++) {
            map.put(cardTypes[t], t);
            map2.put(cardTypes[t], jobs[t]);
        }
    }

    public static Integer getCardType(String string) {
        return map.get(string);
    }

    public static String getCardType(int type) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(type)) {
                return key;
            }
        }

        return null;
    }

    public static String getOccupation(String cardType) {
        return map2.get(cardType);
    }

    public static Vector<String> getAllOccupations() {
        return new Vector<>(map2.values());
    }

    public static Vector<String> getAllCardTypes() {
        return new Vector<>(map.keySet());
    }
}
