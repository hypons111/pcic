package gov.pcc.pwc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public final class PropUtil {

    private PropUtil(){}
    public static String getPropVal(String key) {
        try{
            Properties prop = new Properties();



            File f = new File("/mnt/stsdat/pwc/ECT/ectSetting.properties");
            FileInputStream fis = new FileInputStream(f);
            prop.load(fis);
            return prop.getProperty(key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static boolean getPropValCheck() {
        File f = new File("/mnt/stsdat/pwc/ECT/ectSetting.properties");
        if(f.exists()){
            return true;
        }else{
            return false;
        }
    }
}
