package sourceCode.codeOfList;

import java.util.*;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2023/12/23 13:43
 */
public class ListCode {

    public static void main(String[] args) {
        testArrayList();
        // testArrayCopy();
        // testVector();
        // testLinkedList();
    }

    public static void testLinkedList() {
        LinkedList<String> strings = new LinkedList<>();
        strings.add("a1");
        strings.add("a2");
        strings.add("a3");
        strings.add("a4");
        strings.add("a5");
        strings.add(3, "a22");
    }

    public static void testArrayList() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a1");
        strings.add("a2");
        strings.add("a3");
        strings.add("a4");
        strings.add("a5");
        strings.add("a6");
        strings.add("a7");
        strings.add("a8");
        strings.add("a9");
        strings.add("a10");
        strings.add("a11");
        strings.add("a12");
        strings.add("a13");
        strings.add("a14");
        strings.add("a15");
        strings.add("a16");
        strings.set(0, "a1(1)");
        strings.remove("a12");
        Collections.synchronizedList(new ArrayList<String>());
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void testVector() {
        Vector<Integer> vector = new Vector<>(2);
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);

        // System.arraycopy();

        for (Integer i : vector) {

        }
    }

    public static void testArrayCopy() {
        // 定义原数组，长度为8
        int scores[] = new int[]{100, 81, 68, 75, 91, 66, 75, 100};
        // 定义目标数组
        int newScores[] = new int[]{80, 82, 71, 92, 68, 71, 87, 88, 81, 79, 90, 77};
        System.out.println("原数组中的内容如下：");
        // 遍历原数组
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + "\t");
        }
        System.out.println("\n目标数组中的内容如下：");
        // 遍历目标数组
        for (int j = 0; j < newScores.length; j++) {
            System.out.print(newScores[j] + "\t");
        }
        System.arraycopy(scores, 4, newScores, 0, 4);
        // 复制原数组中的一部分到目标数组中
        System.out.println("\n替换元素后的目标数组内容如下：");
        // 循环遍历替换后的数组
        for (int k = 0; k < newScores.length; k++) {
            System.out.print(newScores[k] + "\t");
        }
    }
}
