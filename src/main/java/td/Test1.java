package td;

public class Test1 {
    public static void main(String[] args) {
        if (args.length == 0)
            System.out.println("输入参数：0");
        else if (args.length == 1)
            System.out.println("输入参数：1 ==" + args[0]);
        else if (args.length == 2)
            System.out.println("输入参数：2 ==" + args[0] + "===" + args[1]);
        else
            System.out.println("输入参数 > 2");
    }
}
