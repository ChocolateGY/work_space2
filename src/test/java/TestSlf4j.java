import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TestSlf4j {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestSlf4j.class);
//        String age = "1213";
        Integer oldWeight;
        Integer newWeight;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter old weight");
        oldWeight = sc.nextInt();

        System.out.println("Enter new weight");
        newWeight = sc.nextInt();



        logger.info("---{}---{}",oldWeight,newWeight);
        logger.info("---"+(oldWeight-newWeight));


    }


}
