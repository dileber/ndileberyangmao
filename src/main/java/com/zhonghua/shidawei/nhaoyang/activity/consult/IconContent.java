package com.zhonghua.shidawei.nhaoyang.activity.consult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidawei on 16/8/18.
 */
public class IconContent {

    public static final List<String> icon = new ArrayList<>();
    
    public static List<String> getIcon(){
        int unicodeJoy = 0x1F601;
        int m = 0x1F64F;
        while (true){
            String emojiString = new String(Character.toChars(unicodeJoy++));
            icon.add(emojiString);
            if(unicodeJoy==m){
                break;
            }

        }

//        icon.add("\\ue01b");icon.add("\\ue15a");icon.add("\\ue159");icon.add("\\ue432");icon.add("\\ue430");icon.add("\\ue431");
//        icon.add("\\ue42f");icon.add("\\ue01e");icon.add("\\ue039");icon.add("\\ue435");icon.add("\\ue01f");icon.add("\\ue125");icon.add("\\ue03a");
//        icon.add("\\ue14e");icon.add("\\ue252");icon.add("\\ue137");icon.add("\\ue209");icon.add("\\ue154");icon.add("\\ue133");icon.add("\\ue150");
//        icon.add("\\ue320");icon.add("\\ue123");icon.add("\\ue132");icon.add("\\ue143");icon.add("\\ue50b");icon.add("\\ue514");icon.add("\\ue513");
//        icon.add("\\ue50c");icon.add("\\ue50d");icon.add("\\ue511");icon.add("\\ue50f");icon.add("\\ue512");icon.add("\\ue510");icon.add("\\ue50e");
//        icon.add("\\ue21c");icon.add("\\ue21d");icon.add("\\ue21e");icon.add("\\ue21f");icon.add("\\ue220");icon.add("\\ue221");icon.add("\\ue222");
//        icon.add("\\ue223");icon.add("\\ue224");icon.add("\\ue225");icon.add("\\ue210");icon.add("\\ue232");icon.add("\\ue233");icon.add("\\ue235");
//        icon.add("\\ue234");icon.add("\\ue236");icon.add("\\ue237");icon.add("\\ue238");icon.add("\\ue239");icon.add("\\ue23b");icon.add("\\ue23a");
//        icon.add("\\ue23d");icon.add("\\ue23c");icon.add("\\ue24d");icon.add("\\ue212");icon.add("\\ue24c");icon.add("\\ue213");icon.add("\\ue214");
//        icon.add("\\ue507");icon.add("\\ue203");icon.add("\\ue20b");icon.add("\\ue22a");icon.add("\\ue22b");icon.add("\\ue226");icon.add("\\ue227");
//        icon.add("\\ue22c");icon.add("\\ue22d");icon.add("\\ue215");icon.add("\\ue216");icon.add("\\ue217");icon.add("\\ue218");icon.add("\\ue228");
//        icon.add("\\ue151");icon.add("\\ue138");icon.add("\\ue139");icon.add("\\ue13a");icon.add("\\ue208");icon.add("\\ue14f");icon.add("\\ue20a");
//        icon.add("\\ue434");icon.add("\\ue309");icon.add("\\ue315");icon.add("\\ue30d");icon.add("\\ue207");icon.add("\\ue229");icon.add("\\ue206");
//        icon.add("\\ue205");icon.add("\\ue204");icon.add("\\ue12e");icon.add("\\ue250");icon.add("\\ue251");icon.add("\\ue14a");icon.add("\\ue149");
//        icon.add("\\ue23f");icon.add("\\ue240");icon.add("\\ue241");icon.add("\\ue242");icon.add("\\ue243");icon.add("\\ue244");icon.add("\\ue245");
//        icon.add("\\ue246");icon.add("\\ue247");icon.add("\\ue248");icon.add("\\ue249");icon.add("\\ue24a");icon.add("\\ue24b");icon.add("\\ue23e");
//        icon.add("\\ue532");icon.add("\\ue533");icon.add("\\ue534");icon.add("\\ue535");icon.add("\\ue21a");icon.add("\\ue219");icon.add("\\ue21b");
//        icon.add("\\ue02f");icon.add("\\ue024");icon.add("\\ue025");icon.add("\\ue026");icon.add("\\ue027");icon.add("\\ue028");icon.add("\\ue029");
//        icon.add("\\ue02a");icon.add("\\ue02b");icon.add("\\ue02c");icon.add("\\ue02d");icon.add("\\ue02e");icon.add("\\ue332");icon.add("\\ue333");
//        icon.add("\\ue24e");icon.add("\\ue24f");icon.add("\\ue537");
//
        return icon;
    }
    

//    class IconItem{
//        private String icon;
//
//        public String getIcon() {
//            return icon;
//        }
//
//        public void setIcon(String icon) {
//            this.icon = icon;
//        }
//    }

}
