package com.JAVALeetcode;

import com.sun.java.accessibility.util.java.awt.ButtonTranslator;
import javafx.scene.layout.Pane;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

//region 初始化块的学习，静态初始化块的学习
/*
class Root {
    static {
        System.out.println("root的静态初始化块");
    }

    {
        System.out.println("Root的普通初始化块");
    }

    public Root() {
        System.out.println("Root的无参数的构造器");
    }
}

class Mid extends Root {
    static {
        System.out.println("Mid的静态初始化块");
    }
    {
        System.out.println("Mid的普通初始化块");
    }
    public  Mid(){
        System.out.println("Mid的无参数的构造器");
    }
    public  Mid(String msg){
        this();
        System.out.println("Mid的带参数的构造器，参数为："+msg);
    }
}

class Leaf extends  Mid{
    static  {
        System.out.println("Leaf的静态初始化块");
    }
    {
        System.out.println("Leaf的普通初始化块");
    }
    public  Leaf(){
        super("JAVA");
        System.out.println("执行leaf的构造器");
    }
}
*/
//endregion


public class Main {

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        //String str = sc.next();
        System.out.println(isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));

    }

    //region 97. Interleaving String https://leetcode.com/problems/interleaving-string/
    // DP solution,from
    public  static  boolean isInterleave1(String s1, String s2, String s3) {
            return  true;
    }
    //BFS the answer is correct,but time limit exceeded
    public static boolean isInterleave(String s1, String s2, String s3) {
        int len1=s1.length();
        int len2=s2.length();
        int len3=s3.length();
        if(len3!=len1+len2)
            return  false;
        int n1=0,n2=0,n3=0;
        int[] res=new int[len3];
        int[] choseRes=new int[len3];
        for (int i=0;i<len3;i++){
            if(n1<len1&&n2<len2&&s3.charAt(i)==s1.charAt(n1)&&s3.charAt(i)==s2.charAt(n2)){
                res[i]=2;
                choseRes[i]=1;
                n1++;
            }
            else  if(n1<len1&&s3.charAt(i)==s1.charAt(n1)){
                res[0]=1;
                choseRes[i]=1;
                n1++;
            }
            else if(n2<len2&&s3.charAt(i)==s2.charAt(n2)){
                res[i]=1;
                choseRes[i]=2;
                n2++;
            }
            //回溯
            else {
                i--;
                while (i>=0&&(!(res[i]==2&&choseRes[i]==1))){
                    res[i]=0;
                    if(choseRes[i]==1)
                        n1--;
                    else
                        n2--;
                    choseRes[i]=0;
                    i--;
                }
                if(i<0)
                    return  false;
                choseRes[i]=2;
                n1--;
                n2++;
            }
        }
        return  true;
    }
    //endregion

    //region Leetcode 316. Remove Duplicate Letters  https://leetcode.com/problems/remove-duplicate-letters/
    static String removeDuplicateLetters(String s) {
        if(s==null||s.length()==0)
            return  s;
        int[] count =new int[26];
        int len=s.length();
        for (int i=0;i<len;i++)
            count[s.charAt(i)-'a']++;
        int pos=0;
        for(int i=0;i<len;i++){
            if(s.charAt(i)<s.charAt(pos))
                pos=i;
            count[s.charAt(i)-'a']--;
            if(count[s.charAt(i)-'a']==0)
                break;
        }
        String charToRemove=String.valueOf(s.charAt(pos));
        return charToRemove+removeDuplicateLetters(s.substring(pos+1).replace(charToRemove,""));
    }
    //endregion

    //region Test15_3_1 FileOutputStream FileWriter
    static void Test15_3_1()
            throws IOException {
        try (
                FileInputStream fis = new FileInputStream("JAVALeetcode.iml");
                FileOutputStream fos = new FileOutputStream("new.txt");
        ) {
            byte[] bbuf = new byte[32];
            int hasRead = 0;
            while ((hasRead = fis.read(bbuf)) > 0)
                fos.write(bbuf, 0, hasRead);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (FileWriter fw = new FileWriter("poem.txt")) {
            fw.write("dasdassd\r\n");
            fw.write("dasfafdsfdsfsdf");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    //endregion

    //region Test15_3 inputStream,FileReader 测试
    public static void Test15_3() throws IOException {
        FileInputStream fis = new FileInputStream("JAVALeetcode.iml");
        byte[] bbuf = new byte[1024];
        int hasRead = 0;
        while ((hasRead = fis.read(bbuf)) > 0) {
            System.out.print(new String(bbuf, 0, hasRead));
        }
        fis.close();

        try {
            FileReader fr = new FileReader("JAVALeetcode.iml");
            char[] cbuf = new char[32];
            int hasReadC = 0;
            while ((hasReadC = fr.read(cbuf)) > 0) {
                System.out.println(new String(cbuf, 0, hasReadC));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //endregion

    //region Test15_1 文件测试
    public static void Test15_1()
            throws IOException {
        File file = new File(".");
        System.out.println(file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsoluteFile().getParent());
        File tmpFile = File.createTempFile("aaa", ".txt", file);
        tmpFile.deleteOnExit();
        File newfile = new File(System.currentTimeMillis() + "");
        System.out.println("new file is exist  " + newfile.exists());
        newfile.createNewFile();
        boolean flag = newfile.mkdir();
        System.out.println("is mkdir: " + flag);
        String[] fileList = file.list();
        System.out.println("=========all the files is next:============");
        for (String fileName : fileList) {
            System.out.println(fileName);
        }
        File[] roots = File.listRoots();
        System.out.println("==========roots dir is next:==============");
        for (File root : roots) {
            System.out.println(root);
        }
    }
    //endregion

    //region Leetcode:165. Compare Version Numbers https://leetcode.com/problems/compare-version-numbers/
    static int compareVersion(String version1, String version2) {
        String[] strs1 = version1.split("\\.");
        String[] strs2 = version2.split("\\.");
        int l1 = strs1.length;
        int l2 = strs2.length;
        int[] ints1 = new int[l1];
        int[] ints2 = new int[l2];
        int count = l1 < l2 ? l1 : l2;
        for (int i = 0; i < count; i++) {
            int a = Integer.parseInt(strs1[i]);
            int b = Integer.parseInt(strs2[i]);
            if (a < b)
                return -1;
            if (a > b)
                return 1;
        }
        if (l1 == l2)
            return 0;
        else if (l1 < l2) {
            for (int i = l1; i < l2; i++)
                if (!Pattern.matches("0+", strs2[i]))
                    return -1;
            return 0;
        } else {
            for (int i = l2; i < l1; i++)
                if (!Pattern.matches("0+", strs1[i]))
                    return 1;
            return 0;
        }
    }
    //endregion

    //region Test12_2 Swing shixian
    public static class SwingComponet {
        JFrame f = new JFrame("Test");
        Icon icoOk = new ImageIcon("ico/ok.png");
        JButton jbtnOk = new JButton("confirm", icoOk);
        JRadioButton jradibtnMale = new JRadioButton("male", true);
        JRadioButton jradibtnFemale = new JRadioButton("female", false);
        ButtonGroup bg = new ButtonGroup();

        public void init() {
            JPanel jpBottom = new JPanel();
            jpBottom.add(jbtnOk);
            f.add(jpBottom, BorderLayout.SOUTH);
            bg.add(jradibtnFemale);
            bg.add(jradibtnMale);
            f.setVisible(true);
            JPanel jpCheck = new JPanel();
            jpCheck.add(jradibtnFemale);
            jpCheck.add(jradibtnMale);
            f.add(jpCheck);
            f.pack();
        }
    }

    //endregion

    //region Test11_5_1 多事件触发
    class Test11_5_1 {
        Frame f = new Frame("Test");
        TextArea ta = new TextArea(6, 40);
        Button b1 = new Button("buton1");
        Button btn2 = new Button("btn2");

        public void init() {
            FirstListener f1 = new FirstListener();
            b1.addActionListener(f1);
            b1.addActionListener(new SecondListener());
            btn2.addActionListener(f1);
            f.add(ta);
            Panel p = new Panel();
            p.add((b1));
            p.add(btn2);
            f.add(p, BorderLayout.SOUTH);
            f.pack();
            f.setVisible(true);
            f.addWindowListener(new MyListenner());
        }

        class MyListenner implements WindowListener {
            @Override
            public void windowOpened(WindowEvent e) {
                ta.append("first open\n");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                ta.append("close windows\n");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ta.append("closed\n");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                ta.append("minimized\n");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                ta.append("reserve\n");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                ta.append("jihuop\n");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                ta.append("lost focus\n");
            }
        }

        class FirstListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.append("the first listener " + e.getActionCommand() + "\n");
            }
        }

        class SecondListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.append("clicked" + e.getActionCommand() + "\n");
            }
        }
    }
    //endregion

    //region Test11.5 事件
    public static class Test11_5 {
        private Frame f = new Frame("testEvent");
        private Button ok = new Button("confirm");
        TextField tf = new TextField(30);

        void init() {
            ok.addActionListener(new OkListenner());
            f.add(tf);
            f.add(ok, BorderLayout.SOUTH);
            f.pack();
            f.setVisible(true);
        }

        class OkListenner implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("user has clicked the ok");
                tf.setText("Hello world");
            }
        }
    }
    //endregion

    //region AWT11.4
    public static class Test11_4 {
        Frame f = new Frame("Test11-4");
        FileDialog d1 = new FileDialog(f, "select file", FileDialog.LOAD);
        FileDialog d2 = new FileDialog(f, "select file to save", FileDialog.SAVE);
        Button b1 = new Button("open file");
        Button b2 = new Button("save file");

        public void init() {

            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    d1.setVisible((true));
                    System.out.println(d1.getDirectory() + d1.getFile());
                }
            });
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d2.setVisible(true);
                    System.out.println(d2.getDirectory() + d2.getFile());
                }
            });
            f.add(b1);
            f.add(b2, BorderLayout.SOUTH);
            f.pack();
            f.setVisible(true);
        }

    }
    //endregion

    //region AWT11.2
    static void Test11_2() {
        Frame f = new Frame("testwindow");
        Panel p = new Panel();
        p.add(new TextField(20));
        p.add(new Button("click me"));
        f.add(p);
        f.setBounds(30, 30, 400, 800);
        f.setVisible(true);
    }

    //endregion
    //region throwTest
    public static void throwChecked(int a)
            throws IOException {
        System.out.println(a);
    }

    public static void throwRuntime(int a) {
        System.out.println(a * 2);
    }

    public static void T10_4_2() {
        //throwChecked(4);
        //throwRuntime(8);

    }
    //endregion

    //region 50. Pow(x, n)   https://leetcode.com/problems/powx-n/
    static double myPow(double x, int n) {
        //return  Math.pow(x,n);
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        if (n < 0)
            return 1 / x * (myPow(1 / x, -(n + 1)));
        double y = myPow(x, n / 2);
        double z = y * y;
        return (n % 2 == 0) ? z : z * x;
    }
    //endregion

    //region 8. String to Integer (atoi)
    //https://leetcode.com/problems/string-to-integer-atoi/
    static int myAtoi(String str) {
        while (str.startsWith(" "))
            str = str.substring(1);
        boolean flag = true;
        long res = 0;
        if (str.startsWith("+"))
            str = str.substring(1);
        else if (str.startsWith("-")) {
            str = str.substring(1);
            flag = false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                res = res * 10 + Integer.parseInt(String.valueOf(str.charAt(i)));
                if (res > Integer.MAX_VALUE) {
                    if (flag)
                        return 2147483647;
                    else
                        return -2147483648;
                }
            } else
                break;
        }
        return (int) res * (flag ? 1 : -1);

    }


    // endregion

    static void Test8_2_1() {
        Collection book = new HashSet();
        book.add("a");
        book.add("b");
        book.add("c");
        Iterator it = book.iterator();
        while (it.hasNext()) {
            String b = (String) it.next();
            System.out.println(b);
            if (b.equals("b"))
                it.remove();
        }
        System.out.println(book);
    }

    //region 房子数目
    /*
        private static int count = 0;
        static int row = 0;
        static int column = 0;
        static void  GetBig()
        {
            int[][] grid = { { 0, 0, 0, 0 }, { 0, 1,1, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 } };
            System.out.println(GetBig(grid));
        }

        static int GetBig(int[][] grid) {

            //获取行列
            row = grid.length;
            column = grid[0].length;

            //设置新的flag矩阵，然后初始化为0。
                int[][] flag =new int[row][column];

            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j < column; j++)
                {
                    flag[i][j] = 0;
                }
            }

            count = 0;
            for (int i = 0; i < row; i++)
            {
                for (int j = 0; j < column; j++)
                {
                    if (grid[i][j] == 0 || flag[i][j] == 1)
                    continue;
                    //初始化房子数量为0
                    //初始化到来方向
                    int direc = 1;
                    while (Check(grid, flag, i, j, direc) != 0)
                    { }
                    count++;
                }
            }
            return count;
        }


        private static int Check(int[][] grid, int[][] flag, int i, int j, int direc)
        {
            flag[i][j] = 1;
            if (direc != 1 && j > 0 && grid[i][ j - 1] == 1 && flag[i][j - 1] == 0)
            Check(grid, flag, i, j - 1, 3);
            if (direc != 2 && i > 0 && grid[i - 1][j] == 1 && flag[i - 1][j] == 0)
            Check(grid, flag, i - 1, j, 4);
            if (direc != 3 && j < column - 1 && grid[i][ j + 1] == 1 && flag[i][ j + 1] == 0)
            Check(grid, flag, i, j + 1, 1);
            if (direc != 4 && i < row - 1 && grid[i + 1][j] == 1 && flag[i + 1][j] == 0)
            Check(grid, flag, i + 1, j, 2);
            return 0;
        }
    */
    //endregion

    //反转数组
    static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    //正则表达式
    static void Zhenzebiaodas() {
        String regex1 = "[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+";
        String value = "192.168.1.1";
        String regex2 = "[0-9]*\\.[0-9]*\\.[0-9]*\\.[0-9]*";
        String regex3 = "[0-9]{1,}\\.[0-9]{1,}\\.[0-9]{1,}\\.[0-9]{1,}";
        String regex4 = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        System.out.println(value.matches(regex1));
        System.out.println(value.matches(regex2));
        System.out.println(value.matches(regex3));
        System.out.println(value.matches(regex4));

    }

    //看想等
    static void shifouxiangdeng() {
        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = "a" + "bc";
        String s5 = new String("a") + "bc";
        System.out.println(s1 == s2);
        System.out.println(s4 == s5);
        System.out.println(s1 == s3);
        System.out.println(s2 == s4);

    }

    //region 叫号系统
/*
    private static int count = 0;
    private static LinkedList<String> qNormal = new LinkedList<String>();
    private static LinkedList<String> qVIP = new LinkedList<String>();

    static  void jiaohaoxitong()
    {
        Scanner scanner = new Scanner(System.in);
        String string = "";
        boolean isQuit = false;
        while(!isQuit){
            string=scanner.nextLine();
            if (string.startsWith("get")) {
                get(string);
            }else if (string.equals("call")) {
                call();
            }else if (string.startsWith("delete")) {
                delete(string);
            }else if (string.equals("count")) {
                count();
            }else if (string.contains("countN")) {
                countN(string);
            }else if (string.equals("reset")) {
                reset();
            }else if(string.equals("quit")){
                isQuit = true;
            }else{
                System.out.println("error");
            }
        }
        scanner.close();
    }
    public static void get(String string) {
        int normalN=qNormal.isEmpty()?1:Integer.parseInt(qNormal.getLast())+1;
        int vipN;
        if(qVIP.isEmpty())
            vipN=1;
        else
        {
            String[] ss=qVIP.getLast().split(" ");
            vipN=Integer.parseInt(ss[1])+1;
        }
        count=normalN>vipN?normalN:vipN;
        if(count>100000){
            System.out.println("error");
        }else if (string.equals("get")) {
            qNormal.add(String.valueOf(count));
            System.out.println(count);
        }else if (string.equals("get vip")) {
            qVIP.add("vip "+count);
            System.out.println("vip "+count);
        }else {
            System.out.println("error");
        }
    }

    public static void call() {
        if (!qNormal.isEmpty()||!qVIP.isEmpty()) {
                if (!qVIP.isEmpty()) {
                    System.out.println(qVIP.remove(0));
                }else{
                    System.out.println(qNormal.remove(0));
                }
        }else{
            System.out.println("error");
        }
    }

    public static void delete(String string) {
        String[] string2 = string.split(" ");
        if (string2.length != 2 ||!string2[1].matches("\\d")) {
            System.out.println("error");
            return;
        }else {

            for (int i = 0; i < qVIP.size(); i++) {
                if (qVIP.get(i).contains(string2[1])) {
                    System.out.println(qVIP.remove(i));
                    return;
                }
            }
            for (int i = 0; i < qNormal.size(); i++) {
                if (qNormal.get(i).contains(string2[1])) {
                    System.out.println(qNormal.remove(i));
                    return;
                }
            }

            System.out.println("error");
            return;
        }

    }

    public static void count() {
        System.out.println(qNormal.size()+qVIP.size());
        return;
    }

    public static void countN(String string) {
        String[] string2 = string.split(" ");
        if (string2.length != 2 ||!string2[1].matches("\\d")) {
            System.out.println("error");
            return;
        }else {

            int vipLength = qVIP.size();
            for (int i = 0; i < vipLength; i++) {
                String[] strS=qVIP.get(i).split(" ");
                if (strS[1].equals(string2[1])) {
                    System.out.println(i+1);
                    return;
                }
            }

            int length = qNormal.size();
            for (int i = 0; i < length; i++) {
                if (qNormal.get(i).equals(string2[1])) {
                    //System.out.println("vipLength"+vipLength+",i"+i);
                    System.out.println(i+vipLength+1);
                    return;
                }
            }
            System.out.println("error");
            return;
        }
    }

    public static void reset() {
        qNormal.clear();
        qVIP.clear();
        count = 0;
    }
*/
//endregion

    //输入输出
    private static void Test_in_out() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] ss = input.split(" ");
        System.out.println(ss[ss.length - 1].length());
    }

    private static void Test7_5_2() {
        boolean b = Pattern.matches("a.*b", "adsdsadab");
        System.out.println(b);
    }

    public static void Test7_4_2() {
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.YEAR));
        System.out.println(c.get(Calendar.MONTH));
        System.out.println(c.get(Calendar.DATE));
        c.set(2016, 9, 12, 9, 12, 12);
        System.out.println(c.getTime());
    }

    public static void Test7_4_1() {
        Date d1 = new Date();
        Date d2 = new Date(System.currentTimeMillis() + 100);
        System.out.println(d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));
    }

    //递归求和
    public static int get(int n) {
        if (n == 1)
            return 1;
        else if (n == 2)
            return 4;
        else
            return get(n - 1) * 2 + get(n - 2);
    }
}


//region 静态类学习
/*
class  A{
    public  static  int a=print(1);
    public  int aa=print(2);
    static  {print(3);}
    {print(4);}
    public  A(String s)
    {
        System.out.println(5);
    }
    public  static  int print(int i)
    {
        System.out.println(i);
        return  i;
    }
}

class  B extends  A{
    public  static  int b=print(6);
    public  int bb=print(7);
    static  {print(8);}
    public  B(String s){
        super(s);
        System.out.println(s);
    }
    public  static  int print(int i){
        System.out.println(i);
        return  i;
    }
}

*/
//endregion