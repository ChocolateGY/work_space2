package com.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by XdaTk on 2014/12/21.
 * <p/>
 * HTTP请求工具类
 */
public class HTTPSend {
    /**
     * 发送get请求
     *
     * @param url  请求地址
     * @param list 请求参数
     *
     * @return 请求结果
     *
     * @throws IOException
     */
    public static String sendGet(String url, List<HTTPParam> list) throws IOException {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URL httpUrl = null; //HTTP URL类 用这个类来创建连接
        URLConnection connection = null; //创建的http连接
        BufferedReader bufferedReader = null; //接受连接受的参数
        //如果存在参数，我们才需要拼接参数 类似于 localhost/index.html?a=a&b=b
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                buffer.append(list.get(i).getKey()).append("=").append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));
                //如果不是最后一个参数，不需要添加&
                if ((i + 1) < list.size()) {
                    buffer.append("&");
                }
            }
            url = url + "?" + buffer.toString();
        }
        //创建URL
        httpUrl = new URL(url);
        //建立连接
        connection = httpUrl.openConnection();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        connection.connect();
        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }
 
    /**
     * 发送Post请求
     *
     * @param url  请求地址
     * @param list 请求参数
     *
     * @return 请求结果
     *
     * @throws IOException
     */
    public static String sendPost(String url, List<HTTPParam> list) throws IOException {
        StringBuffer buffer = new StringBuffer(); //用来拼接参数
        StringBuffer result = new StringBuffer(); //用来接受返回值
        URL httpUrl = null; //HTTP URL类 用这个类来创建连接
        URLConnection connection = null; //创建的http连接
        PrintWriter printWriter = null;
        BufferedReader bufferedReader; //接受连接受的参数
        //创建URL
        httpUrl = new URL(url);
        //建立连接
        connection = httpUrl.openConnection();
        connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        printWriter = new PrintWriter(connection.getOutputStream());
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                buffer.append(list.get(i).getKey()).append("=").append(URLEncoder.encode(list.get(i).getValue(), "utf-8"));
                //如果不是最后一个参数，不需要添加&
                if ((i + 1) < list.size()) {
                    buffer.append("&");
                }
            }
        }
        printWriter.print(buffer.toString());
        printWriter.flush();
        connection.connect();
        //接受连接返回参数
        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        return result.toString();
    }

    public static void main(String[] args) {
        try {
            String encoding="GBK";
            File file=new File("D:\\TalkingData\\2017-03-29\\macValidate20170327.txt");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                BufferedWriter out = new BufferedWriter(new FileWriter("D:\\TalkingData\\2017-03-29\\macValidateResult.txt"));

                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String a = sendGet(lineTxt, new List<HTTPParam>() {
                        @Override
                        public int size() {
                            return 0;
                        }

                        @Override
                        public boolean isEmpty() {
                            return false;
                        }

                        @Override
                        public boolean contains(Object o) {
                            return false;
                        }

                        @Override
                        public Iterator<HTTPParam> iterator() {
                            return null;
                        }

                        @Override
                        public Object[] toArray() {
                            return new Object[0];
                        }

                        @Override
                        public <T> T[] toArray(T[] a) {
                            return null;
                        }

                        @Override
                        public boolean add(HTTPParam httpParam) {
                            return false;
                        }

                        @Override
                        public boolean remove(Object o) {
                            return false;
                        }

                        @Override
                        public boolean containsAll(Collection<?> c) {
                            return false;
                        }

                        @Override
                        public boolean addAll(Collection<? extends HTTPParam> c) {
                            return false;
                        }

                        @Override
                        public boolean addAll(int index, Collection<? extends HTTPParam> c) {
                            return false;
                        }

                        @Override
                        public boolean removeAll(Collection<?> c) {
                            return false;
                        }

                        @Override
                        public boolean retainAll(Collection<?> c) {
                            return false;
                        }

                        @Override
                        public void clear() {

                        }

                        @Override
                        public HTTPParam get(int index) {
                            return null;
                        }

                        @Override
                        public HTTPParam set(int index, HTTPParam element) {
                            return null;
                        }

                        @Override
                        public void add(int index, HTTPParam element) {

                        }

                        @Override
                        public HTTPParam remove(int index) {
                            return null;
                        }

                        @Override
                        public int indexOf(Object o) {
                            return 0;
                        }

                        @Override
                        public int lastIndexOf(Object o) {
                            return 0;
                        }

                        @Override
                        public ListIterator<HTTPParam> listIterator() {
                            return null;
                        }

                        @Override
                        public ListIterator<HTTPParam> listIterator(int index) {
                            return null;
                        }

                        @Override
                        public List<HTTPParam> subList(int fromIndex, int toIndex) {
                            return null;
                        }
                    });
                    System.out.println(lineTxt);
                    out.write(lineTxt.split("=")[1]+"\t"+a); // \r\n即为换行
                    out.newLine();
                    out.flush(); // 把缓存区内容压入文件
                }
                out.close(); // 最后记得关闭文件
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
}