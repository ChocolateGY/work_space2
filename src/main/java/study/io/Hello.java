package study.io;

import java.io.*;
import java.util.concurrent.Callable;

/**
 * Created by root on 2017-11-28.
 */
public class Hello {
    public static void main(String[] args) throws IOException {
//        System.out.println(File.pathSeparator);
//        System.out.println(File.separator);
        String path = "D:" + File.separator + "hello.txt";
        File f = new File(path);

        /*try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(f.exists())
            f.delete();
        else
            System.out.println("文件不存在");
*/
//        for (File i:f.listFiles()) {
//            System.out.println(i);
//        }
//        System.out.println(f.isFile());
//        printF(f);
//        RandomAccessFile demo = new RandomAccessFile(f,"rw");
//        demo.writeBytes("hello");
//        demo.writeChar('S');
//        demo.close();
//        OutputStream os =new FileOutputStream(f,true);
//        String s = "嘎嘎";
//        os.write(s.getBytes());
//        os.close();

        /*
        InputStream in = new FileInputStream(f);
        byte[] b = new byte[1024];
        int cout = 0;
        int temp = 0;
        while((temp = in.read())!= -1){
            b[cout++] = (byte)temp;
        }

        in.close();
        System.out.println(new String(b));
*/
      /*  Writer out  = new FileWriter(f,true);
        String str = "嘻嘻";
        out.write(str);
        out.close();
*/
    /*  Reader in = new  FileReader(f);
      char[] ch = new char[100];
      int temp =0;
      int count =0;
      while((temp=in.read())!= -1){
          ch[count++] = (char)temp;
      }
        System.out.println(new String(ch,0,count));*/


  /*      Send send = new Send();
        Recive recive = new Recive();
        try {
//管道连接
            send.getOut().connect(recive.getInput());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(send).start();
        new Thread(recive).start();*/
/*        try {

//    System.setOut(new PrintStream(new FileOutputStream(f)));

            System.setIn(new FileInputStream(f));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        int len = System.in.read(buffer);
        System.out.println("hello.txt :" +new String(buffer,0,len));*/


        DataInputStream input = new DataInputStream(new FileInputStream(f));
        char[] ch = new char[100];
        int count = 0;
        char temp;
       /* while ((temp = input.read() != -1 )){

        }*/

       new Callable(){
           @Override
           public Object call() throws Exception {
               return null;
           }
       };
    }

    public static void printF(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                if (files != null) {
                    for (File i : files) {
                        printF(i);
                    }
                }
            } else
                System.out.println(f);
        }
    }

}

class Send implements Runnable {
    private PipedOutputStream out = null;

    public Send() {
        out = new PipedOutputStream();
    }

    public PipedOutputStream getOut() {
        return this.out;
    }

    public void run() {
        String message = "hello , Rollen";
        try {
            out.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 接受消息类
 */
class Recive implements Runnable {
    private PipedInputStream input = null;

    public Recive() {
        this.input = new PipedInputStream();
    }

    public PipedInputStream getInput() {
        return this.input;
    }

    public void run() {
        byte[] b = new byte[1000];
        int len = 0;
        try {
            len = this.input.read(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("接受的内容为 " + (new String(b, 0, len)));
    }
}
class thread1 extends Thread{
    @Override
    public void run() {
        super.run();
    }
}