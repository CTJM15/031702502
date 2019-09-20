import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Num {
	String adr;
	String num;
	Num(String address){
		adr=address;
	}
	public String quhao() {
	Pattern pattern = Pattern.compile("([1][3-9][\\d]{9})|(0\\d{2,4}-\\d{7,8})");
    Matcher matcher = pattern.matcher(adr);
    StringBuffer bf = new StringBuffer(64);
    while (matcher.find()) {
            bf.append(matcher.group());
        }
    	String[] strlist = adr.split(bf.toString());       
    	adr=strlist[0]+strlist[1];
		return bf.toString();
	}
	public String getnum() {
		return quhao();
	}
	public String getdzh() {
		return adr;
	}
}
