import java.util.List;


public class Address {
	String address;
	String province;
	String province2;
	String city;
	String area;
	String town;
	String street;
	String hao;
	String detail;
		Address(String address){
			this.address=address;
		}
		public String get_area(){
			areas();
			return area;
		}
	
		public String get_city(){
			citys();
			return city;
		}
		
		public String get_province(){
			provinces();
			return province;
		}
		
	
		public String get_town(){
			towns();
			return town;
		}
			
		public String get_street(){
			streets();
			return street;
		}
		
		public String get_haos(){
			haos();
			return hao;
		}
		public String get_detail(){
			details();
			return detail;
		}
		private void provinces(){

			int i =0;
			boolean ok=false;
			for(;i<address.length();i++){
				if(address.charAt(i)=='省'){
					ok=true;
					break;
				}
			}
			if(ok==true){               
				province = address.substring(0,i);
				address = address.substring(i+1);
			}else{
				boolean find = false;
				LocalUtil lu =  new LocalUtil ();
				List<String> list = 	lu.getProvinces("中国");
				String[] strlist={""};
				for(i=0; i<list.size(); i++){
					
						strlist =address.split(list.get(i),2);
						
						if(strlist[0].equals(address)){
							
						}else{
							
							find=true;
							break;
						}
				}
				if(find==true){
					province=list.get(i);
					address=strlist[1];
				}else {
					province="";
				}
			}
			
		}
		private void citys(){
			if(province.equals("北京")  ||province.equals("重庆")  ||  province.equals("上海") ||province.equals("天津")  ){
				city=province;
			}
			else {
			int i = 0 ;
			int j =0;
			boolean find=false;
			String ti=" ";
			if(province.equals("")) {
			
				LocalUtil lu =  new LocalUtil();
				List<String> list1 =lu.getProvinces("中国");
				
				for(j=0;j<list1.size();j++) {
					ti=list1.get(j);
					List<String> list =lu.getCities("中国",ti);
					String[] strlist={""};
					for(i=0; i<list.size(); i++){
						strlist =address.split(list.get(i),2);
						if(strlist[0].equals(address)){
						}else{
							province2=list1.get(j);
							find=true;
							break;
						}
					}
					if(find==true){
						
						city=list.get(i);
						if(strlist[1].charAt(0)=='市'){
							address=strlist[1].substring(1);
						}else{
							address=strlist[1];
						}
						break;
					}
					else {
						city="";
					}
				}
				
			}else {
				LocalUtil lu =  new LocalUtil();
				List<String> list =lu.getCities("中国",province);
				String[] strlist={""};
				for(i=0; i<list.size(); i++){
					strlist =address.split(list.get(i),2);
					if(strlist[0].equals(address)){
					}else{
						find=true;
						break;
					}
				}
				if(find==true){
					city=list.get(i);
					if(strlist[1].charAt(0)=='市'){
						address=strlist[1].substring(1);
					}else{
						address=strlist[1];
					}
				}
				else {
					city="";
				}
			}
				
			}
		}
		private void areas(){
			int i = 0 ;
			boolean find=false;
			int k =0;
				LocalUtil lu =  new LocalUtil();
				List<String> list ;
				List<String> list1 ;
				if(province.equals("")) {
					list=lu.getqus("中国",province2,city);
					
				}else {
					 list=lu.getqus("中国",province,city);
				}
				if(city.equals("")) {
					list1=lu.getCities("中国", province);
					for(int j =0;j<list1.size();j++) {
						String[] strlist={""};
						list=lu.getqus("中国",province,list1.get(j));
						for(i=0; i<list.size(); i++){
							 k =0;
							while(list.get(i).charAt(k)==address.charAt(k)) {
								k++;
							}
						if(k>=2) {
							find=true;
								break;
						}			
						}
						if(find==true){
							area=list.get(i);
							address=address.substring(k);
							break;
						}
						else {
							area="";
						}
					}
				}else {
					String[] strlist={""};
					for(i=0; i<list.size(); i++){
						strlist =address.split(list.get(i),2);
						if(strlist[0].equals(address)){
						}else{
							find=true;
							break;
						}
					}
					if(find==true){
						area=list.get(i);
						if(strlist[1].charAt(0)=='市'){
							address=strlist[1].substring(1);
						}else{
							address=strlist[1];
						}
					}
					else {
						area="";
					}
				}
				
			
		}
		
		private void towns(){
			int i = 0 ;
			boolean find=false;
			for(i = 0;i < address.length() ; i++){
				if((address.charAt(i)=='街' &&address.charAt(i+1)=='道')||address.charAt(i)=='镇' || address.charAt(i)=='乡'){
					find=true;
					break; 
				}
			}
			if(find == true){
				if(address.charAt(i+1)=='道'){
					town=address.substring(0,i+2);
					address=address.substring(i+2);
				}else{
					town = address.substring(0,i+1);
				address=address.substring(i+1);
				}
				

			}else{
				town="";
			}
		}
		
		private void streets(){
			int i = 0 ;
			boolean find=false;
			for(i = 0;i < address.length() ; i++){
				if(address.charAt(i)=='街'||address.charAt(i)=='路'   || address.charAt(i)=='巷'){
					find=true;
					break;
				}
			}
			if(find == true){
				street = address.substring(0,i+1);
				address=address.substring(i+1);

			}else{
				street="";
			}
		}
		public String get_address(){
			return address;
		}
		private void haos(){
			int i = 0 ;
			boolean find=false;
			for(i = 0;i < address.length() ; i++){
				if(address.charAt(i)=='号'){
					find=true;
					break;
				}
			}
			if(find == true){
				hao = address.substring(0,i+1);
				address=address.substring(i+1);

			}else{
				hao="";
			}
		}
		private void details(){
			
			detail = address;
		}
}
