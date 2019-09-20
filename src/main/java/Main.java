import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;



public class Main {
    String ji;
    String address;
    String name;
    String province;
    String city;
    String area;
    String town;
    String street;
    String hao;
    String detail;
    String telnum;
    public static void main(String[] args) {

        try {
            File f = new File(args[0]);
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));
            BufferedWriter out =new BufferedWriter(new FileWriter(args[1]));
            FileOutputStream fileOutputStream=new FileOutputStream(args[1]);
            OutputStreamWriter outputStreamWriter= new OutputStreamWriter(fileOutputStream,StandardCharsets.UTF_8);
            out = new BufferedWriter(outputStreamWriter);

            String l = null;
            out.write("["+"\r\n");
            int k =0;
            while ((l = r.readLine()) != null) {
                String address = l.replace(".","");
                String split[] = address.split("!");
//	                System.out.println(AddressUtil.addressResolutionVersion2(split[1],1));

                Main rg = new Main();
                rg.input(l);
                XM a1 = new XM(rg.address);
                rg.ji=a1.getji();
                rg.name=a1.getname();
                rg.address=a1.getdizhi();
                Num a2=new Num(rg.address);
                rg.telnum=a2.getnum();
                rg.address=a2.getdzh();
                Address a3 = new Address(rg.address);
                rg.province=a3.get_province();
                rg.city=a3.get_city();
                rg.area=a3.get_area();
                rg.town=a3.get_town();
                rg.street=a3.get_street();
                rg.hao=a3.get_haos();
                rg.detail=a3.get_detail();
                rg.detail =rg.detail.substring(0,rg.detail.length()-1);
                String m=rg.printjson( k );
                k++;
                out.write(m+"\r\n");
            }
            out.write("]"+"\r\n");
            out.close();
            r.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String printjson(int k){
        if(province.equals("北京")  ||province.equals("重庆")  ||  province.equals("上海") ||province.equals("天津")  ||province.equals("")){
            province=province;
        }else {
            province = province+"省";
        }
        if(area==null){
            area="";
        }
        if(city.equals("")) {

        }else {
            city=city+"市";
        }

        String s=" ";
        if(k==0) {
            if(ji.equals("2")){
                s = "{\"姓名\":"+"\""+name+"\""+",\"手机\":"+"\""+telnum+"\""+",\"地址\":[\""+province+"\",\""+city+"\",\""+area+"\",\""+town+"\",\""+street+"\""
                        +",\""+hao+"\",\""+detail+"\"]}";
            }else if(ji.equals("1")){
                s = "{\"姓名\":"+"\""+name+"\""+",\"手机\":"+"\""+telnum+"\""+",\"地址\":[\""+province+"\",\""+city+"\",\""+area+"\",\""+town+"\",\""+street
                        +hao+detail+"\"]}";
            }
        }else {
            if(ji.equals("2")){
                s = ",{\"姓名\":"+"\""+name+"\""+",\"手机\":"+"\""+telnum+"\""+",\"地址\":[\""+province+"\",\""+city+"\",\""+area+"\",\""+town+"\",\""+street+"\""
                        +",\""+hao+"\",\""+detail+"\"]}";
            }else if(ji.equals("1")){
                s = ",{\"姓名\":"+"\""+name+"\""+",\"手机\":"+"\""+telnum+"\""+",\"地址\":[\""+province+"\",\""+city+"\",\""+area+"\",\""+town+"\",\""+street
                        +hao+detail+"\"]}";
            }
        }



        String out = JsonFormart(s);
        return out;

    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
    public static String JsonFormart(String s) {
        int level = 0;

        StringBuffer jsonForMatStr = new StringBuffer();
        for(int index=0;index<s.length();index++)
        {

            char c = s.charAt(index);
//	          System.out.println(s.charAt(index));


            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
//	                System.out.println("123"+jsonForMatStr);
            }

            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }
    private void input(String s){

        address = s;
    }
}