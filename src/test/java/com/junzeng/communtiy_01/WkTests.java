package com.junzeng.communtiy_01;

import java.io.IOException;

public class WkTests {
    public static void main(String[] args) {
        String cmd = "F:/Environment/wkhtmltopdf/bin/wkhtmltoimage --quality 75  https://www.nowcoder.com f:/work/data/wk-images/4.png";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
