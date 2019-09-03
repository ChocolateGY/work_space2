package study.io;

import org.apache.spark.sql.sources.In;

import java.io.*;

/**
 * https://www.jianshu.com/p/715659e4775f
 *
 */
public class LearnIO {
    public static void main(String[] args) throws IOException {
        test12();
        test13();
        test14();
        test15();

    }

    /**
     * 读取控制台中的输入
     */
    public static void test1() throws IOException {
        InputStream input = System.in;
        Reader reader = new InputStreamReader(input);
        BufferedReader br = new BufferedReader(reader);

        String str = br.readLine();
        System.out.println("you type the word:" + str);
    }

    public static void test2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please input a char:");
        char c = (char) br.read();
        System.out.println("the char that you input :" + c);
    }

    static void test3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please input: ");
        char c;
        do {
            c = (char) br.read();
            System.out.println("the char that you input: " + c);
        } while (c != 'q');
    }

    /**
     * 二进制文件的写入和读取
     */
    static void test4() throws IOException {
        byte[] bytes = {12, 21, 34, 11, -128};
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\test4"));
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    static void test5() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\temp\\test4"));
        byte c;
        // 读取写入的二进制文件，输出字节数组
        while ((c = (byte) fileInputStream.read()) != -1) {
            System.out.print(c);
        }
    }

    /**
     * 文本文件的写入和读取
     * <p>
     * write() 方法和 append() 方法并不是像方法名那样，一个是覆盖内容，一个是追加内容，
     * append() 内部也是 write() 方法实现的，也非说区别，也就是 append() 方法可以直接写 null，
     * 而 write() 方法需要把 null 当成一个字符串写入，所以两者并无本质的区别。
     * 需要注意的是这里并没有指定文件编码，可能会出现乱码的问题。
     */
    static void test6() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("D:\\temp\\test5"));
        fileWriter.write("hello,world\n");
        fileWriter.write("不会覆盖原来的内容\n");
        fileWriter.append("并不是追加一行内容，不要被方法名迷惑\n");
        fileWriter.append(null);
        fileWriter.flush();
        System.out.println("文件的默认编码是：" + fileWriter.getEncoding());
        fileWriter.close();
        /**
         *  public Writer append(CharSequence csq) throws IOException {
         *         if (csq == null)
         *             write("null");
         *         else
         *             write(csq.toString());
         *         return this;
         *     }
         */
    }

    static void test7() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("D:\\temp\\test5"), false);
        fileWriter.write("hello,world\n");
        fileWriter.write("会覆盖\n");
        fileWriter.append("下一行");
        fileWriter.flush();
        fileWriter.close();
    }

    static void test8() throws IOException {
        FileReader fileReader = new FileReader(new File("D:\\temp\\test5"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

    }

    /**
     * 使用字节流和字符流的转换类 InputStreamReader 和 OutputStreamWriter 可以指定文件的编码，
     * 使用 Buffer 相关的类来读取文件的每一行。
     *
     * @throws IOException
     * @Test
     */
    static void test9() throws IOException {
        FileReader fileReader = new FileReader(new File("D:\\temp\\test5"));
        int c;
        while ((c = fileReader.read()) != -1) {
            System.out.print((char) c);
        }
    }

    static void test10() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\test10.txt"));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
        outputStreamWriter.write("OutputStreamWriter write \n");
        outputStreamWriter.write("另一行");
        System.out.println("文件编码为：" + outputStreamWriter.getEncoding());
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    static void test11() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\temp\\test10.txt"));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    /**
     * 测试缓冲流
     * <p>
     * res：
     * 复制所用时间： 17
     * 时间：33
     * time is : 38
     * the time is : 27975
     */
    static void test12() throws IOException {
        //输入输出都用缓冲流
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\TalkingData\\资料\\ip.zip"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\ip12.zip"));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        int len = 0;
        byte[] bs = new byte[1024];
        long start = System.currentTimeMillis();
        while ((len = bufferedInputStream.read(bs)) != -1) {
            bufferedOutputStream.write(bs, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制所用时间： " + (end - start));
        bufferedInputStream.close();
        fileInputStream.close();
        bufferedOutputStream.close();
        fileOutputStream.close();
    }

    static void test13() throws IOException {
        //只有输入用缓冲流

        FileInputStream fileInputStream = new FileInputStream(new File("D:\\TalkingData\\资料\\ip.zip"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\ip13.zip"));
        int len = 0;
        byte[] bytes = new byte[1024];
        long start = System.currentTimeMillis();
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("时间：" + (end - start));
        bufferedInputStream.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

    static void test14() throws IOException {
        //输入和输出都不适用缓冲流
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\TalkingData\\资料\\ip.zip"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\ip14.zip"));

        int len = 0;
        byte[] bytes = new byte[1024];
        long start = System.currentTimeMillis();
        while ((len = fileInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
        }
        long end = System.currentTimeMillis();
        System.out.println("time is : " + (end - start));
        fileInputStream.close();
        fileOutputStream.close();
    }

    static void test15() throws IOException {
        //不使用缓冲

        FileInputStream fileInputStream = new FileInputStream(new File("D:\\TalkingData\\资料\\ip.zip"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\temp\\ip15.zip"));

        int b;
        long start = System.currentTimeMillis();
        while ((b = fileInputStream.read()) != -1) {
            fileOutputStream.write(b);
        }
        long end = System.currentTimeMillis();
        System.out.println("the time is : " + (end - start));
        fileInputStream.close();
        fileOutputStream.close();
    }
}
