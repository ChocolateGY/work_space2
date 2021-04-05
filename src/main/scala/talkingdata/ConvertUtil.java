package talkingdata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kang Xu
 * @Create 2019-12-12 15:14
 * @Descripation: 调用AESUtil解密，并重新输出格式
 */


public class ConvertUtil {
    public static void main(String input, String outPut, String key) {
        //多文件路径
//        String input = "C:\\XuKang\\A_work\\2019_12\\1210_大有-数据合作_邱宇\\Test\\in";
//        String outPut = "C:\\XuKang\\A_work\\2019_12\\1210_大有-数据合作_邱宇\\Test\\out";
//        String key = "TD@201912";

        //保存文件名
        List<String> files = new ArrayList<String>();
        //将文件名填充到数组
        File file = new File(input);
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                files.add(fileName);
            }
        }
        // 遍历数组，创建多线程任务
        // 跟据分好片的文件个数创建线程数
        for (int i = 0; i < files.size(); i++) {
            String file_name = files.get(i);
//            System.out.println("文件：" + file_name);
            String file_in = input + "\\" + file_name;
            String file_out = outPut + "\\" + file_name;
            System.out.println("正在处理文件：" + file_name);
            readTool(file_in, file_out, key);
            System.out.println("文件：" + file_name + "\t处理完成");
        }

    }

    public static String aes(String input, String password) {
        String key = AesUtil.parseByte2HexStr(AesUtil.getKey(password));
        String jiemi = AesUtil.decryptAES(input, key);

        return jiemi;
    }


    public static void readTool(String input_path, String output_path, String key) {

        try {
            //结果文件
            File result = new File(output_path);
            PrintStream resultPs = new PrintStream(new FileOutputStream(result, true));


            FileReader reader = new FileReader(input_path);
            BufferedReader br = new BufferedReader(reader);

            //读取每一行的数据
            String str = null;
            while ((str = br.readLine()) != null) {
//                System.out.println(str);
                String count = str.split("\t")[0];
                String aesinfo = str.split("\t")[1];
                String md5 = aes(aesinfo, key);

                resultPs.println(count + "," + md5);
            }

            br.close();
            reader.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }


}
