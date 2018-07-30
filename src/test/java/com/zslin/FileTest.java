package com.zslin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by zsl on 2018/7/25.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileTest {

    @Test
    public void test01() throws Exception {
        File dir = new File("E:\\temp\\umitest\\dist");
        readFile(dir);
    }

    private void readFile(File f) throws Exception {
        if(f.isFile()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line ;
            while((line=br.readLine())!=null) {
                if(line.indexOf("8888")>=0) {
                    System.out.println(f.getName()+":::"+line);
                }
            }
        } else {
            File [] files = f.listFiles();
            for(File file : files) {
                readFile(file);
            }
        }
    }
}
