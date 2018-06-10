package dao;

import JComPz.BookMap;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;
import model.BookInfo;
import model.BookType;
import model.Operator;
import model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Dao {

    private static Connection connection = null;

    /**
     * 构造函数. 构造connection对象, 创建链接.
     */
    private Dao() {
        try {
            if (connection == null) {
                String dbDriver = "com.mysql.jdbc.Driver";
                Class.forName(dbDriver);
                String dbUrl = "jdbc:mysql://localhost:3306/db_library?useUnicode=true&characterEncoding=UTF8";
                String dbUser = "root";
                String dbPwd = "000123456";
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            }
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver:" + cnfe);
        } catch (SQLException se) {
            System.out.println("Error connecting database:" + se);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录界面检查用户是否存在
     *
     * @param name
     * @param pwd
     * @return
     */
    public static Operator check(String name, String pwd) {
        Operator operator = new Operator();
        /* 查询记录 */
        String sql = "SELECT * FROM tb_operator where name='" + name + "' and password='" + pwd + "'";
        ResultSet rs = Dao.executeQuery(sql);
        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                operator.setId(rs.getString("id"));
                operator.setName(rs.getString("name"));
                operator.setPassword(rs.getString("password"));
                operator.setGrade(rs.getString("admin"));
            }

            Dao.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return operator;
    }

    public static boolean deleteReader(String isbn) {
        boolean successful = false;
        String sql = "DELETE FROM tb_reader WHERE ISBN='" + isbn + "';";

        try {
            Dao.executeUpdate(sql);
            successful = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dao.close();
        return successful;
    }

    /**
     * 查询书的类别
     *
     * @return
     */
    public static Vector<BookType> selectBookCategory() {
        String sql = "SELECT * from tb_booktype";
        Vector<BookType> vector = new Vector<>();
        ResultSet resultSet = executeQuery(sql);
        try {
            while (resultSet.next()) {
                BookType bookType = new BookType();
                bookType.setDaysAvailable(Integer.parseInt(resultSet.getString("days")));
                bookType.setId(Integer.parseInt(resultSet.getString("id")));
                bookType.setTypeName(resultSet.getString("typename"));
                bookType.setFinePerDay(Double.parseDouble(resultSet.getString("fk")));

                vector.add(bookType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vector;
    }

    /**
     * 根据bookinfo来向数据库中添加一本书
     *
     * @param b
     * @return
     */
    public static boolean insertBook(BookInfo b) {
        boolean successful = false;
        try {
            String sql = "INSERT INTO tb_bookinfo(ISBN,typeId,bookname,writer,translator,publisher," +
                    "date,price) VALUES('" + b.getISBN() + "','" + b.getTypeId() + "','" + b.getBookName()
                    + "','" + b.getAuthor() + "','" + b.getTranslator() + "','" + b.getPublisher()
                    + "','" + java.sql.Date.valueOf(b.getPublicationDate()) + "'," + b.getPrice() + ")";
            System.out.println("sql=");
            System.out.println(sql);
            successful = executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        close();
        return successful;
    }

    /**
     * 添加新的读者至数据库中
     *
     * @param r
     * @return
     */
    public static boolean insertReader(Reader r) {
        boolean successful = false;
        String sql = "INSERT INTO tb_reader(name,sex,age,identityCard,date,maxNum,tel," +
                "keepMoney,zj,zy,ISBN,bztime) VALUES('" + r.getName() + "','" + r.getSex() + "'," + r
                .getAge() + ",'" + r.getIdentityCard() + "','" + Date.valueOf(r.getValidDate()) + "'," + r
                .getMaxNum() + ",'" + r.getTelNumber() + "','" + r.getKeepMoney() + "'," + r
                .getIdentityCardType() + ",'" + r.getOccupation() + "','" + r.getNumber() +
                "','" + Date.valueOf(r.getStartDate()) + "')";
        try {
            executeUpdate(sql);
            successful = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Dao.close();
        return successful;
    }

    /**
     * 根据ISBN查询书目
     *
     * @param isbn
     * @return
     */
    public static List<BookInfo> selectBookInfo(String isbn) {
        List<BookInfo> bookInfoList = new ArrayList<>();
        String sql = "SELECT * FROM tb_bookinfo where ISBN='" + isbn + "'";
        ResultSet resultSet = executeQuery(sql);

        try {
            while (resultSet.next()) {
                BookInfo bookInfo = new BookInfo();
                bookInfo.setAuthor(resultSet.getString("writer"));
                bookInfo.setBookName(resultSet.getString("bookname"));
                bookInfo.setISBN(resultSet.getString("ISBN"));
                bookInfo.setPrice(resultSet.getDouble("price"));
                bookInfo.setPublicationDate(resultSet.getString("date"));
                bookInfo.setPublisher(resultSet.getString("publisher"));
                bookInfo.setTranslator(resultSet.getString("translator"));
                bookInfo.setTypeId(resultSet.getInt("typeid"));
                bookInfo.setBookType(BookMap.getType(bookInfo.getTypeId()));

                bookInfoList.add(bookInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dao.close();
        return bookInfoList;
    }

    /**
     * 返回所有图书信息.
     */
    public static List<BookInfo> selectBookInfo() {
        String sql = "SELECT * FROM tb_bookinfo";
        List<BookInfo> bookInfoList = new ArrayList<>();
        ResultSet resultSet = executeQuery(sql);

        try {
            while (resultSet.next()) {
                BookInfo bookInfo = new BookInfo();

                bookInfo.setAuthor(resultSet.getString("writer"));
                bookInfo.setBookName(resultSet.getString("bookname"));
                bookInfo.setISBN(resultSet.getString("ISBN"));
                bookInfo.setPrice(resultSet.getDouble("price"));
                bookInfo.setPublicationDate(resultSet.getString("date"));
                bookInfo.setPublisher(resultSet.getString("publisher"));
                bookInfo.setTranslator(resultSet.getString("translator"));
                bookInfo.setTypeId(resultSet.getInt("typeid"));
                bookInfo.setBookType(BookMap.getType(bookInfo.getTypeId()));

                bookInfoList.add(bookInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dao.close();
        return bookInfoList;
    }

    /**
     * 返回由所有读者组成的List
     *
     * @return
     */
    public static List<Reader> selectReader() {
        Vector<Reader> readerVector = new Vector<>();
        String sql = "SELECT * FROM tb_reader";

        try {
            ResultSet resultSet = Dao.executeQuery(sql);

            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setName(resultSet.getString("name"));
                reader.setSex(resultSet.getString("sex"));
                reader.setAge(resultSet.getInt("age"));
                reader.setIdentityCard(resultSet.getString("identityCard"));
                reader.setValidDate(resultSet.getString("date"));
                reader.setMaxNum(resultSet.getInt("maxNum"));
                reader.setTelNumber(resultSet.getString("tel"));
                reader.setKeepMoney(resultSet.getDouble("keepMoney"));
                reader.setIdentityCardType(resultSet.getInt("zj"));
                reader.setOccupation(resultSet.getString("zy"));
                reader.setNumber(resultSet.getString("ISBN"));
                reader.setStartDate(resultSet.getString("bztime"));

                readerVector.add(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dao.close();
        return readerVector;
    }

    public static Boolean updateBook(BookInfo b) {
        boolean successful = false;
        try {
            String sql = "UPDATE tb_bookinfo SET ISBN='" + b.getISBN() + "',typeid='" + b
                    .getTypeId() + "',bookname='" + b.getBookName() + "',writer='" + b.getAuthor()
                    + "',translator='" + b.getTranslator() + "',publisher='" + b.getPublisher() + "'," +
                    "date='" + b.getPublicationDate() + "',price=" + b.getPrice() + " where " +
                    "ISBN='" + b.getISBN() + "'";
            successful = executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return successful;
    }

    /**
     * 对数据库读者信息表的更新
     *
     * @param r
     * @return
     */
    public static Boolean updateReader(Reader r) {
        Boolean successful = false;
        String sql = "UPDATE tb_reader SET name='" + r.getName() + "',sex='" + r.getSex() + "'," +
                "age=" + r.getAge() + ",identityCard='" + r.getIdentityCard() + "',date='" +
                r.getValidDate() + "',maxNum=" + r.getMaxNum() + ",tel='" + r
                .getTelNumber() + "',keepMoney=" + r.getKeepMoney() + ",zj=" + r.getIdentityCardType() + ",zy='" + r
                .getOccupation() + "',ISBN='" + r.getNumber() + "',bztime='" + r
                .getStartDate() + "' WHERE ISBN='" + r.getNumber() + "'";
        try {
//            System.out.println(sql);
            Dao.executeUpdate(sql);
            successful = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        Dao.close();
        return successful;
    }

    /**
     * Dao的控制关闭操作
     */
    private static void close() {
        try {
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            connection = null;
        }
    }

    /**
     * 提供的对数据库查询
     *
     * @param sql
     * @return
     */
    private static ResultSet executeQuery(String sql) {
        try {
            if (connection == null) {
                new Dao();//这个过程会新建链接
            }

            return connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE).executeQuery(sql);

        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    /**
     * 提供对数据库的更新
     *
     * @param sql
     * @return
     */
    private static boolean executeUpdate(String sql) {
        try {
            if (connection == null) {
                new Dao();
            }

            int ret = connection.createStatement().executeUpdate(sql);
            return ret != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
