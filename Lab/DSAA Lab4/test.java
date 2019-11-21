package 第三章_表栈和队列;

import java.util.Scanner;

/**
 * 实现功能： 输入一个表达式(包含数字+-*除括号) a + b * c + ( d * e + f ) * g 计算出表达式的结果
 *
 * @author nangua
 */
public class test {
    private static Scanner sc;
    // 运算符栈顶元素
    private static Operator topOperator = new Operator();

    public static void main(String[] args) {
        System.out.println("请输入表达式(以空格分隔，例如1 + 2 * ( 1 + 2 ) )：");
        sc = new Scanner(System.in);
        String input = sc.nextLine();
        // 转化为后缀表达式
        Scalculation1(input);
    }

    /**
     * 中缀表达式转化为后缀表达式
     *
     * @param input
     */
    private static void Scalculation1(String input) {
        // 得到输入的表达式并储存在数组中
        String[] expression = input.split(" ");

        @SuppressWarnings("rawtypes")
        MyStack<?> outputStack = new MyStack(); // 输出栈

        @SuppressWarnings("rawtypes")
        MyStack<?> operatorStack = new MyStack(); // 运算符栈

        // 储存（的位置
        int leftBracketsPosition = -1;
        // 运算符栈元素位置（从底往上依次为0,1,2...）
        int osCount = -1;
        // 储存栈顶以下的一个元素

        System.out.println("输入的表达式长度" + expression.length);

        // 中缀转换为后缀
        for (int i = 0; i < expression.length; i++) {

            // 区分是数字还是运算符
            // 如果是数字
            try {
                Integer t = Integer.valueOf(expression[i]);
                outputStack.push(t); // 如果是数字则推入输出栈

                // 如果是运算符
            } catch (Exception e) {

                // 储存运算符的Operator
                Operator storageOperator = new Operator();
                // 如果是运算符则区分运算符优先级然后推入运算符栈
                switch (expression[i]) {
                    case "*":
                        storageOperator.setValue("*");
                        break;
                    case "/":
                        storageOperator.setValue("/");
                        break;
                    case "+":
                        storageOperator.setValue("+");
                        break;
                    case "-":
                        storageOperator.setValue("-");
                        break;
                    case "(":
                        storageOperator.setValue("(");
                        // 记录（的位置
                        break;
                    case ")":
                        storageOperator.setValue(")");
                        break;
                    case "=":
                        storageOperator.setValue("=");
                }

                // 如果第二个元素不为空
                if (osCount != -1) {
                    if (storageOperator.value.endsWith("=")) {
                        while (osCount != -1) {
                            outputStack.push(operatorStack.pop());
                            osCount--;
                        }
                    } else if (storageOperator.value.endsWith("(")) {
                        // 推入运算符栈
                        operatorStack.push(storageOperator);
                        osCount++;
                        leftBracketsPosition = osCount;
                        topOperator = (Operator) operatorStack.top();

                    } else if (storageOperator.value.endsWith(")")) {
                        System.out.println("qqqqqqqqqqqqq: " + osCount);
                        while ((osCount != leftBracketsPosition) && (osCount >= 1)) {
                            outputStack.push(operatorStack.pop());
                            osCount--;
                        }
                        // 再把(号踢出去
                        operatorStack.pop();
                        osCount--;
                        if (osCount != -1) {
                            topOperator = (Operator) operatorStack.top();
                        }

                    } else if ((topOperator.priority >= storageOperator.priority)) {


                        while (((topOperator.priority >= storageOperator.priority))) {

                            if (topOperator.value.equals("(")) {
                                // 推入运算符栈
                                operatorStack.push(storageOperator);
                                osCount++;
                                topOperator = (Operator) operatorStack.top();
                                break;
                            } else {
                                if (osCount == -1) { // 如果已经没有元素
                                    // 推入运算符栈
                                    operatorStack.push(storageOperator);
                                    osCount++;
                                    topOperator = (Operator) operatorStack.top();
                                    break;
                                } else {
                                    // 将栈顶元素推入输出栈
                                    outputStack.push(topOperator);
                                    // 在operator中踢出已经推入输出栈的栈顶并设置新的栈顶
                                    operatorStack.pop();
                                    osCount--;
                                    if (osCount!=-1) {
                                        topOperator = (Operator) operatorStack.top();
                                    } else {
                                        // 推入运算符栈
                                        operatorStack.push(storageOperator);
                                        osCount++;
                                        topOperator = (Operator) operatorStack.top();
                                        break;
                                    }
                                }
                            }
                        }


                    } else if ((topOperator.priority <= storageOperator.priority)) {
                        // 推入运算符栈
                        operatorStack.push(storageOperator);
                        osCount++;
                        topOperator = (Operator) operatorStack.top();

                    }
                } else {
                    // 第一次推入运算符栈
                    operatorStack.push(storageOperator);
                    osCount++;
                    topOperator = (Operator) operatorStack.top();

                }

            }
        }

        System.out.println("******************结果*********************");
        System.out.println("打印输出栈内所有元素：");
        outputStack.show();
        System.out.println();
        System.out.println("输出栈长度：" + outputStack.count);

    }

    /**
     * 数组实现的简单栈
     *
     * @author nangua
     */
    static class MyStack<T> {
        MyStack() {
        }

        Object[] array = new Object[1];;
        // 数组元素个数
        int count = 0;
        // 栈顶元素
        Object topOfStack = "-1";

        private void show() {
            Object o = new Object();
            for (int i = 0; i < array.length; i++) {
                o = array[i];
                if (o instanceof Operator) {
                    Operator ceshi = (Operator) o;
                    if ((!ceshi.value.equals("(")) && (!ceshi.value.equals(")"))) {
                        System.out.print(" " + ceshi.value);
                    }

                } else {
                    System.out.print(" " + o);
                }
            }
        }

        /**
         * push进栈
         *
         * @param num
         */
        private void push(Object num) {
            // 如果数组为空
            if (count == 0) {
                array[count] = num;
            } else {
                // 如果数组不为空，扩充数组
                Object[] newarray = new Object[count + 1];
                // 复制数组
                for (int i = 0; i < array.length; i++) {
                    newarray[i] = array[i];
                }
                array = newarray;
                array[count] = num;
            }
            count++;
            topOfStack = num;
        }

        /**
         * 找出栈顶的下一个元素
         */
        private Object next() {
            // 如果总元素大于或等于2个
            if (count - 1 >= 0) {
                return array[count - 1];
            }
            return "无元素可next";
        }

        /**
         * 出栈
         *
         * @return
         */
        private Object pop() {
            // 如果数组为空
            if (count == 0) {
                try {
                    throw new Exception("栈为空时不能pop");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "无元素可pop";
            } else {
                if (count == 1) {
                    topOfStack = array[0];
                    count = 0;
                } else {
                    topOfStack = array[count - 1];
                    // 缩小数组
                    Object[] newarray = new Object[count - 1];
                    for (int j = 0; j < newarray.length; j++) {
                        newarray[j] = array[j];
                    }
                    array = newarray;
                    count--;
                }
                return topOfStack;
            }
        }

        /**
         * 显示栈顶元素
         */
        private Object top() {
            // 如果数组为空
            if (count == 0) {
                try {
                    throw new Exception("栈为空时不能top");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "无元素可top";
            } else {
                if (count == 1) {
                    topOfStack = array[0];
                } else {
                    topOfStack = array[count - 1];
                }
                return topOfStack;
            }
        }

        /**
         * 测试方法：打印栈内数组 过滤掉了（）左右括号
         */

    }

    /**
     * 运算符类，储存优先级和运算符
     *
     * @author nangua
     */
    static class Operator {
        String value;
        // 优先级默认为-1
        int priority = -1;

        public Operator() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
            if (value.equals("+") || (value.equals("-"))) {
                this.priority = 1;
            } else if (value.equals("*") || (value.equals("/"))) {
                this.priority = 2;
            } else if (value.equals("(") || (value.equals(")"))) {
                this.priority = 3;
            } else if (value.equals("=")) {
                this.priority = 0;
            }
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
