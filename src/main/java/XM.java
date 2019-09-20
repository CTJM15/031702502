public class XM {
	String name;
	String adr;
	String ji;
	XM(String address){
		adr = address;
	}
 	
	private void xmcl() {           
		int i;
		for(i=0;i<adr.length();i++) {
			if(adr.charAt(i)=='!')
				break;
		}
		ji=adr.substring(0, i);
		adr=adr.substring(i+1);
		for(i=0;i<adr.length();i++) {
			if(adr.charAt(i)==',')
				break;
		}
		name=adr.substring(0, i);
		adr=adr.substring(i+1, adr.length());
		//adr=??;
		//name=??;
	}
	public String getji()
	{
		xmcl();
		return ji;
		
	}
	public String getname() {
		return name;
	}
	public String getdizhi() {
		return adr;
	}
}
