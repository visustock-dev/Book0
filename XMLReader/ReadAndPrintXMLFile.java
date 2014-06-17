import java.io.File;

import org.w3c.dom.*;

import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class ReadAndPrintXMLFile{
public static void main (String[] args){
	
	for (String s: args) {
        System.out.println(s);
    Checker(s);
    System.out.println("------------");
    Document doc = getDoc(s);
    getPeople(doc);
    System.out.println("------------");
    getPets(doc, "dog");
    System.out.println("------------");
    getPets(doc, "cat");
	}
    }//end of main
    
    public static Document getDoc(String s){//Creates a document variable from the file input
    	Document doc = null;
	    try {

	            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	            //doc = docBuilder.parse (new File("pets.xml"));
	            doc = docBuilder.parse (new File(s));

	            // normalize text representation
	            doc.getDocumentElement ().normalize ();
	            System.out.println ("Root element of the doc is " + 
	                 doc.getDocumentElement().getNodeName());

	        }catch (Throwable t) {
	        t.printStackTrace ();
	        }
	        //System.exit (0);
	    return doc;
} //getDoc()
   
    
    public static void getPeople(Document doc){//Displays the people in the XML file
    	try {

            NodeList listOfPersons = doc.getElementsByTagName("person");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of people : " + totalPersons);
            
            String[] peopleFNameArray;//----Declares an array of the owners First Names
            peopleFNameArray = new String[listOfPersons.getLength()];
            
            String[] peopleLNameArray;//----Declares an array of the owners Last Names
            peopleLNameArray = new String[listOfPersons.getLength()];
            
            for(int s=0; s<listOfPersons.getLength() ; s++){


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("First Name : " + 
                           ((Node)textFNList.item(0)).getNodeValue().trim());
                    
                    peopleFNameArray[s] = ((String)(textFNList.item(0)).getNodeValue().trim());
                   // System.out.println( "Name in string at index : " + s + " : " + peopleFNameArray[s]);

                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textLNList = lastNameElement.getChildNodes();
                    System.out.println("Last Name : " + 
                           ((Node)textLNList.item(0)).getNodeValue().trim());
                    
                    peopleLNameArray[s] = ((String)(textLNList.item(0)).getNodeValue().trim());
                  //  System.out.println( "Name in string at index : " + s + " : " + peopleLNameArray[s]);
                    
                    //----
                    NodeList idList = firstPersonElement.getElementsByTagName("id");
                    Element idElement = (Element)idList.item(0);
                    
                    NodeList textIdList = idElement.getChildNodes();
                    System.out.println("Id : " + 
                    ((Node)textIdList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ageList = firstPersonElement.getElementsByTagName("age");
                    Element ageElement = (Element)ageList.item(0);

                    NodeList textAgeList = ageElement.getChildNodes();
                    System.out.println("Age : " + 
                           ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    	
    }
    
    public static void getPets(Document doc, String petType){//Displays dogs in the XML file
    	try {

            NodeList listOfDogs = doc.getElementsByTagName(petType);
            int totalDogs = listOfDogs.getLength();
            System.out.println("Total no of " + petType + "s: " + totalDogs);

            for(int s=0; s<listOfDogs.getLength() ; s++){


                Node dogNode = listOfDogs.item(s);
                if(dogNode.getNodeType() == Node.ELEMENT_NODE){


                    Element dogElement = (Element)dogNode;

                    //-------
                    NodeList dogIdList = dogElement.getElementsByTagName("id");
                    Element dogIdElement = (Element)dogIdList.item(0);

                    NodeList textFNList = dogIdElement.getChildNodes();
                    System.out.println("ID : " + 
                           ((Node)textFNList.item(0)).getNodeValue().trim());

                    //-------
                    NodeList dogNameList = dogElement.getElementsByTagName("name");
                    Element dogNameElement = (Element)dogNameList.item(0);

                    NodeList textLNList = dogNameElement.getChildNodes();
                    System.out.println("Name : " + 
                           ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ownerList = dogElement.getElementsByTagName("ownerId");
                    Element ownerElement = (Element)ownerList.item(0);

                    NodeList textOwnerList = ownerElement.getChildNodes();
                    System.out.println("Owner ID : " + 
                           ((Node)textOwnerList.item(0)).getNodeValue().trim());
                    //int x;
                    int x = Integer.parseInt(textOwnerList.item(0).getNodeValue());
                    //x = ((Node)textOwnerList.item(0)).getNodeValue().trim();
                    //System.out.println(x);
                    
                    owner(x, doc);

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    }
        
    public static void owner(int b, Document doc){//Displays who owns the dog or cat, using a person from the people element
    	int s = b-1;
    	
    	try {

            NodeList listOfPersons = doc.getElementsByTagName("person");
            int totalPersons = listOfPersons.getLength();
            //System.out.println("Total no of people : " + totalPersons);
            
            String[] peopleFNameArray;//----Declares an array of the owners First Names
            peopleFNameArray = new String[listOfPersons.getLength()];
            
            String[] peopleLNameArray;//----Declares an array of the owners Last Names
            peopleLNameArray = new String[listOfPersons.getLength()];
            //peopleArray = new String[10];
            

            


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                   // System.out.println("First Name : " + 
                      //     ((Node)textFNList.item(0)).getNodeValue().trim());
                    
                    peopleFNameArray[s] = ((String)(textFNList.item(0)).getNodeValue().trim());
                    

                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textLNList = lastNameElement.getChildNodes();
                   // System.out.println("Last Name : " + 
                    //       ((Node)textLNList.item(0)).getNodeValue().trim());
                    
                    peopleLNameArray[s] = ((String)(textLNList.item(0)).getNodeValue().trim());
                   // System.out.println( "Name in string at index : " + s + " : " + peopleLNameArray[s]);
                    
                    //----
                    NodeList idList = firstPersonElement.getElementsByTagName("id");
                    Element idElement = (Element)idList.item(0);
                    
                    NodeList textIdList = idElement.getChildNodes();
                   // System.out.println("Id : " + 
                   // ((Node)textIdList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ageList = firstPersonElement.getElementsByTagName("age");
                    Element ageElement = (Element)ageList.item(0);

                    NodeList textAgeList = ageElement.getChildNodes();
                   // System.out.println("Age : " + 
                     //      ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------
                    System.out.println( "Owner : " + peopleFNameArray[s] + " " + peopleLNameArray[s]);


                }//end of if clause


            


        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    	
    	
    }
    public static void Checker(String s) {
    	 
  	  File f = new File(s);
   
  	  if(f.exists()){
  		  System.out.println("File existed");
  	  }else{
  		  System.out.println("File not found!");
  		System.exit(1);
  	  }
   
    }


}