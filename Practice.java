import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Practice {
    public static void main(String[] args) {
//        charCount();
//        intReverse();
//        stringReverse();
//        sentenceReverse();
//        wordsSort();
//        toBin();
//        move();
//        errorList();
//        generatePassword();
    }
    /**
     * 瓶子问题, 三个空瓶换一瓶
     */
    public static void fun() {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("0")) {
                break;
            }
            int num = Integer.parseInt(s);
            list.add(num / 2);
        }
        sc.close();

        for (Integer i: list) {
            System.out.println(i);
        }

    }

    /**
     * 生成新密码
     */
    public static void generatePassword() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder pwd = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 2);
        map.put("b", 2);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 3);
        map.put("f", 3);
        map.put("g", 4);
        map.put("h", 4);
        map.put("i", 4);
        map.put("j", 5);
        map.put("k", 5);
        map.put("l", 5);
        map.put("m", 6);
        map.put("n", 6);
        map.put("o", 6);
        map.put("p", 7);
        map.put("q", 7);
        map.put("r", 7);
        map.put("s", 7);
        map.put("t", 8);
        map.put("u", 8);
        map.put("v", 8);
        map.put("w", 9);
        map.put("x", 9);
        map.put("y", 9);
        map.put("z", 9);
        for (int i = 0; i < s.length(); i++) {
            String str = ((Character) s.charAt(i)).toString();
            if (Pattern.matches("[a-z]", str)) {
                pwd.append(map.get(str));
                continue;
            }
            if (Pattern.matches("[A-Z]", str)) {
                char d = str.toLowerCase().charAt(0);
                if (d == 122) {
                    pwd.append("a");
                } else {
                    d = (char) (d + 1);
                    pwd.append(d);
                }
                continue;
            }
            pwd.append(str);

        }
        System.out.println(pwd);
    }

    /**
     * 检测指定错误
     */
    public static void errorList() {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<String> filenames = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] strings = s.split("\\\\");
            s = strings[strings.length - 1];
            String filename = s.split(" ")[0];
            if (filename.length() > 16) {
                filename = filename.substring(filename.length() - 16);
            }
            filename = filename + " " + s.split(" ")[1];
            if (map.containsKey(filename)) {
                map.put(filename, map.get(filename) + 1);
            } else {
                filenames.add(filename);
                map.put(filename, 1);
            }
        }
        if (filenames.size() > 8) {
            System.out.println(filenames);
            filenames = filenames.subList(filenames.size() - 8, filenames.size());
        }
        for (String name : filenames) {
            System.out.printf("%s %d\n", name, map.get(name));
        }
    }

    /**
     * move distance
     */
    public static void move() {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String[] cmds = str.split(";");
        int[] result = {0, 0};
        HashSet<Character> set = new HashSet<Character>();
        set.add('A');
        set.add('W');
        set.add('S');
        set.add('D');
        for (String cmd : cmds) {
            char[] charArray = cmd.toCharArray();
            if (charArray.length == 3 || charArray.length == 2) {
                if (set.contains(charArray[0])) {
                    int distance;
                    try {
                        String dis = Character.toString(charArray[1]);
                        if (charArray.length == 3) {
                            dis = dis + charArray[2];
                        }
                        distance = Integer.parseInt(dis);
                    } catch (Exception e) {
                        continue;
                    }
                    if (charArray[0] == 'A') {
                        result[0] = result[0] - distance;
                    } else if (charArray[0] == 'W') {
                        result[1] = result[1] + distance;
                    } else if (charArray[0] == 'S') {
                        result[1] = result[1] - distance;
                    } else if (charArray[0] == 'D') {
                        result[0] = result[0] + distance;
                    }
                }
            }
        }
        System.out.printf("%d,%d", result[0], result[1]);
    }

    /**
     * bin count 1
     */
    public static void toBin() {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        ArrayList<Integer> array = new ArrayList<Integer>();
        int count = 0;
        while (n != 0) {
            if (n % 2 == 1) {
                count += 1;
            }
            array.add(n % 2);
            n = n / 2;
        }
        ;
        System.out.println(count);
        System.out.println(array);
    }

    /**
     * words sort
     */
    public static void wordsSort() {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = s.nextLine();
        }
//        Arrays.sort(str);
        for (String st : str) {
            System.out.println(st);
        }
    }

    /**
     * sentence reverse
     */
    public static void sentenceReverse() {
        Scanner s = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String[] array = s.nextLine().split(" ");
        for (int i = array.length; i > 0; i--) {
            list.add(array[i - 1]);
        }
        System.out.println(String.join(" ", list));
    }

    /**
     * string reverse
     */
    public static void stringReverse() {
        Scanner s = new Scanner(System.in);
        StringBuilder newStr = new StringBuilder(100);
        char[] array = s.nextLine().toCharArray();
        for (int i = array.length; i > 0; i--) {
            newStr.append(array[i - 1]);
        }
        System.out.println(newStr);
    }

    /**
     * integer reverse
     */
    public static void intReverse() {
        Scanner s = new Scanner(System.in);
        StringBuilder newStr = new StringBuilder(100);
        char[] array = s.nextLine().toCharArray();
        for (int i = array.length; i > 0; i--) {
            newStr.append(array[i - 1]);
        }
        System.out.println(newStr);
    }

    /**
     * Statistics on the number of non duplicate strings
     */
    public static void charCount() {
        HashSet<Integer> set = new HashSet<>();
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        char[] array = str.toCharArray();
        for (char ch : array) {
            if ((int) ch <= 127) {
                set.add((int) ch);
            }
        }
        System.out.println(set.size());
    }
}
