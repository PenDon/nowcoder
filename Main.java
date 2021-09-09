import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    //    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String[] s = str.split(",");
//        List<Integer> list = new ArrayList<>();
//        for (String weight: s) {
//            list.add(Integer.parseInt(weight));
//        }
//        Integer temp;
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = i + 1; j < list.size(); j++) {
//                if (list.get(i) > list.get(j)) {
//                    temp = list.get(i);
//                    list.set(i, list.get(j));
//                    list.set(j, temp);
//                }
//            }
//        }
//        str = sc.nextLine();
//        Integer total = Integer.parseInt(str);
//        Integer count = 0;
//        for (Integer i: list) {
//            total = total - i;
//            if (total >= 0) {
//                count += 1;
//            } else {
//                break;
//            }
//        }
//        System.out.println(count);
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String[] s = str.split("");
//        StringBuilder s = delete(new StringBuilder(str));
//        System.out.println(s.length());
//    }
//    public static StringBuilder delete(StringBuilder str) {
//        StringBuilder newStr = new StringBuilder("");
//        for (int i = 0; i < str.length(); i++) {
//            if (i == str.length() - 1) {
//                newStr.append(str.charAt(i));
//                break;
//            }
//            if (str.charAt(i) != str.charAt(i+1)) {
//                newStr.append(str.charAt(i));
//            } else {
//                i += 1;
//            }
//        }
//        System.out.println(newStr);
//        System.out.println(str);
//        if (str.toString().equals(newStr.toString())) {
//            return newStr;
//        } else {
//            return delete(newStr);
//        }
//    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Integer m = Integer.parseInt(str.split(" ")[0]);
        Integer t = Integer.parseInt(str.split(" ")[1]);
        Integer p = Integer.parseInt(str.split(" ")[2]);
        str = sc.nextLine();
        List<Integer> list = new ArrayList<>();

        for (String s : str.split(" ")) {
            list.add(Integer.parseInt(s));
        }
        Integer[] listBackup = new Integer[list.size()];
        for (int y = 0; y < list.size(); y++) {
            listBackup[y] = list.get(y);
        }
        // 进行一轮检测
        // 存储错误数据的索引
        List<Integer> error = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            // 小于0 小于上一个值 减去上一个的值大于等于10
            if (list.get(i) < 0) {
                error.add(i);
//                System.out.printf("处理错误[%d]", i);
                errorHandle(error, m, t, list, i, p, listBackup);
            }
            if (i != 0) {
                if (list.get(i) < list.get(i - 1)) {
                    error.add(i);
//                    System.out.printf("处理错误[%d]", i);
                    errorHandle(error, m, t, list, i, p, listBackup);


                }
                if (list.get(i) - list.get(i - 1) >= 10) {
                    error.add(i);
//                    System.out.printf("处理错误[%d]", i);
                    errorHandle(error, m, t, list, i, p, listBackup);


                }
            }
        }
//        System.out.println(error);
        int countFinal = 0;
        for (int z = 0; z < listBackup.length; z++) {
            if (!listBackup[z].equals(9999)) {
                countFinal += 1;
            }
        }
        System.out.println(countFinal);
        // 错误的数值处理:
        // 是否检测到故障:若检测到故障，丢弃检测到故障开始 -  直到故障恢复的数据;
        // 若未检测到故障, 由前一个正常值代替, 若无正常值，丢弃数据
        // 错误数据处理

    }

    public static void errorHandle(List<Integer> error, Integer m, Integer t, List<Integer> list, Integer i, Integer p, Integer[] listBackUp) {
//        System.out.printf("end is [%s]", i);
        Integer errorIndex = hasError(error, m, t, 0, list, i);
        if (errorIndex.equals(-1)) {
            // 未检测到故障, 由前一个正常值替代
//            System.out.println("未检测到故障!");
            for (Integer e : error) {
                Integer j = e - 1;
                while (error.contains(j)) {
                    j = j - 1;
                }
                if (j < 0) {
                    // todo 前面没有正常值, 移除该值
                    listBackUp[e] = 9999;
//                    System.out.printf("移除索引[%d]", e);

                } else {
                    // 有正常值, 替换为正常值
                    list.set(e, list.get(j));
                    listBackUp[e] = list.get(j);
                }
            }

        } else {
            // 检测到故障, 丢弃数据
            Integer correctIndex = hasCorrect(error, p, errorIndex, errorIndex, list);
            if (correctIndex.equals(-1)) {
                // todo 未恢复, 丢弃故障产生之后的所有数据
//                System.out.println("故障未恢复");
                for (int k = errorIndex; k < listBackUp.length; k++) {
                    listBackUp[k] = 9999;
                }
            } else {
                // 故障恢复, 丢弃部分数据
//                System.out.printf("故障恢复, 移除[%d] - [%d]之间的数据", errorIndex, correctIndex);
                for (int x = errorIndex; x < correctIndex; x++) {
                    listBackUp[x] = 9999;
                }
            }
        }
//        System.out.println(list);
//        System.out.println(Arrays.toString(listBackUp));
    }

    /**
     * 是否检测到故障, 检测到故障时返回故障发生的 index, 未检测到故障时返回 -1
     *
     * @param errorList
     * @return
     */
    public static Integer hasError(List<Integer> errorList, Integer m, Integer t, Integer i, List<Integer> data, Integer end) {
//        System.out.printf("i is [%s]", i);
//        System.out.printf("end is [%s]", end);
        if (i >= end) {
//            System.out.println("未检测到故障！");
            return -1;
        }
        Integer count = 0;
        for (int j = i; j < m + i; j++) {
            if (errorList.contains(j)) {
                count += 1;
            }
            if (count.equals(t)) {
//                System.out.println("error!");
                // 返回故障发生的索引
                return j;
            }
        }
//        System.out.println("i++");
        return hasError(errorList, m, t, i + 1, data, end);

    }

    /**
     * 检索故障是否恢复
     *
     * @param errorList  错误的索引列表
     * @param p          故障是否恢复判定周期
     * @param errorIndex 故障产生的index
     * @param i          递归索引
     * @return 故障是否恢复, 从哪一个索引开始恢复
     */
    public static Integer hasCorrect(List<Integer> errorList, Integer p, Integer errorIndex, Integer i, List<Integer> data) {
        if (i > data.size()) {
//            System.out.print("故障未恢复");
            return -1;
        }
        for (; i < p + i; i++) {
            // 若错误索引列表中存在, 则未恢复, 检索下一周期
            if (errorList.contains(i)) {
                return hasCorrect(errorList, p, errorIndex, i + 1, data);
            }

        }
        // 检索完毕, 周期内未产生错误, 判定故障恢复, 返回故障恢复的index
//        System.out.printf("故障恢复于[%d]", i);
        return i;
    }
}
/*
 * 1. 给定一个物品重量列表，再给定一个包裹最大承重，计算包裹最大可装载物品数量
     * 输入示例 :
     * 10,4,15,8
     * 25
     * 输出 :
     * 3
 * 2. 字符消消乐：给定一个字符串, 消除其中相邻并且相同的部分, 区分大小写, 输出最后的字符串
     * 输入示例 :
     * Mmbccbc
     * 输出 :
     * Mmc
     * 备注 : 消除 cc 后, 新的字符串中 bb 符合消除条件, 继续消除
 * 3. 给定错误数据判定逻辑, 给定一组数据, 根据逻辑检测错误的数据
     * 错误数据判定逻辑：当前数据小于0 或者 小于上一个数据 或者 当前数据减去上一个数据的值大于等于10, 则判定为错误数据
     * 当检测到一个错误数据时, 需要进行错误处理
     * 错误数据处理逻辑:
     *      给定一个判定是否故障的逻辑：在连续的 M 个数据中至少有 T 个错误时, 判定为
     *      再给定一个判定故障是否恢复的逻辑：
 */
