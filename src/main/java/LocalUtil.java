import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class LocalUtil {


		private static final List<String> COUNTRY_REGION = new ArrayList<String>();
		private static LocalUtil localutil;
		private SAXReader reader;
		private Document document;
		private Element rootElement;
		
		
		public LocalUtil(){
			
			reader = new SAXReader();
			try {
				document = reader.read(LocalUtil.class.getResourceAsStream("LocList.xml"));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			rootElement =  document.getRootElement();	
			
			Iterator it =  rootElement.elementIterator();
			Element ele = null;
			while(it.hasNext()){
				ele = (Element)it.next();
				COUNTRY_REGION.add(ele.attributeValue("Name"));
			}
		}
		
	
		public List<String> getCountry(){
			return COUNTRY_REGION;
		}
		

		private List<Element> provinces(String countryName){
			Iterator it =  rootElement.elementIterator();
			List<Element> provinces = new ArrayList<Element>();
			Element ele = null;
			while(it.hasNext()){
				ele = (Element)it.next();
				COUNTRY_REGION.add(ele.attributeValue("Name"));
				if(ele.attributeValue("Name").equals(countryName)){
					provinces = ele.elements();
					break;
				}
			}
			return provinces;
		}
		
	
		public List<String> getProvinces(String countryName){
			List<Element> tmp = this.provinces(countryName);
			List<String> list = new ArrayList<String>();
			for(int i=0; i<tmp.size(); i++){
				list.add(tmp.get(i).attributeValue("Name"));
			}
			return list;
		}
		
	
		private List<Element> cities(String countryName, String provinceName){
			List<Element> provinces =  this.provinces(countryName);
			List<Element> cities = new ArrayList<Element>();
			if(provinces==null || provinces.size()==0){		
				return cities;
			}
			
			for(int i=0; i<provinces.size(); i++){
				if(provinces.get(i).attributeValue("Name").equals(provinceName)){
					cities = provinces.get(i).elements();
					break;
				}
			}
			return cities;
		}
		private List<Element> qus(String countryName,String provinceName,String cityname){
			
			List<Element> cities =  this.cities(countryName, provinceName);
			List<Element> qus = new ArrayList<Element>();
			for(int i=0; i<cities.size(); i++){
				if(cities.get(i).attributeValue("Name").equals(cityname)){
					qus = cities.get(i).elements();
					break;
				}
			}
			return qus;
		}
		
		public List<String> getqus(String countryName,String provinceName,String cityname){
			List<Element> tmp =  this.qus(countryName, provinceName, cityname);
			List<String> qus = new ArrayList<String>();
			for(int i=0; i<tmp.size(); i++){
				qus.add(tmp.get(i).attributeValue("Name"));
			}
			return qus;
		}

		public List<String> getCities(String countryName, String provinceName){
			List<Element> tmp =  this.cities(countryName, provinceName);
			List<String> cities = new ArrayList<String>();
			for(int i=0; i<tmp.size(); i++){
				cities.add(tmp.get(i).attributeValue("Name"));
			}
			return cities;
		}
		
		
		
		public static LocalUtil getInstance(){
			if(localutil==null){
				localutil = new LocalUtil();
			}
			return localutil;
		}


}
