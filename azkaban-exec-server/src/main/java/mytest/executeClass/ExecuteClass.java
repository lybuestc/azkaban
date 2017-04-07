package mytest.executeClass;

import mytest.utils.DateFormatUtil;

import java.util.Date;

/**
 * Created by lybuestc on 17/4/6.
 */
public class ExecuteClass {

    public void start(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(DateFormatUtil.dateFormat.format(new Date()) + " 执行start");
    }

    public void jobA(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateFormatUtil.dateFormat.format(new Date()) + " 执行jobA");
    }

    public void jobB(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateFormatUtil.dateFormat.format(new Date()) + "执行jobB");
    }
    public void jobC(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateFormatUtil.dateFormat.format(new Date()) + "执行jobC");
    }
    public void jobD(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateFormatUtil.dateFormat.format(new Date()) + "执行jobD");
    }
}
