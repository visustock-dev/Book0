import java.io.File;

import org.w3c.dom.*;


import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class ReadAndPrintXMLFile{
public static void main (String argv []){
    getPeople();
    System.out.println("------------");
    getDogs();
    System.out.println("------------");
    getCats();
    }//end of main
    public static void readXML(){//Original base function ---- not used
    	
    	    try {

    	            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    	            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    	            Document doc = docBuilder.parse (new File("book1.xml"));

    	            // normalize text representation
    	            doc.getDocumentElement ().normalize ();
    	            System.out.println ("Root element of the doc is " + 
    	                 doc.getDocumentElement().getNodeName());


    	            NodeList listOfPersons = doc.getElementsByTagName("person");
    	            int totalPersons = listOfPersons.getLength();
    	            System.out.println("Total no of people : " + totalPersons);

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

    	                    //-------
    	                    NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
    	                    Element lastNameElement = (Element)lastNameList.item(0);

    	                    NodeList textLNList = lastNameElement.getChildNodes();
    	                    System.out.println("Last Name : " + 
    	                           ((Node)textLNList.item(0)).getNodeValue().trim());

    	                    //----
    	                    NodeList ageList = firstPersonElement.getElementsByTagName("age");
    	                    Element ageElement = (Element)ageList.item(0);

    	                    NodeList textAgeList = ageElement.getChildNodes();
    	                    System.out.println("Age : " + 
    	                           ((Node)textAgeList.item(0)).getNodeValue().trim());

    	                    //------


    	                }//end of if clause


    	            }//end of for loop with s var


    	        }catch (SAXParseException err) {
    	        System.out.println ("** Parsing error" + ", line " 
    	             + err.getLineNumber () + ", uri " + err.getSystemId ());
    	        System.out.println(" " + err.getMessage ());

    	        }catch (SAXException e) {
    	        Exception x = e.getException ();
    	        ((x == null) ? e : x).printStackTrace ();

    	        }catch (Throwable t) {
    	        t.printStackTrace ();
    	        }
    	        //System.exit (0);

    	
    }
    
    public static void getPeople(){//Displays the people in the XML file
    	try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("pets.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
                 //doc.getDocumentElement().getNodeName());


            NodeList listOfPersons = doc.getElementsByTagName("person");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of people : " + totalPersons);
            
            String[] peopleFNameArray;//----Declares an array of the owners First Names
            peopleFNameArray = new String[listOfPersons.getLength()];
            
            String[] peopleLNameArray;//----Declares an array of the owners Last Names
            peopleLNameArray = new String[listOfPersons.getLength()];
            //peopleArray = new String[10];
            

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


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    	
    }
    
    public static void getDogsALT(){//alternate form of getDogs method ---- not used
    	try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("pets.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
                 //doc.getDocumentElement().getNodeName());


            NodeList listOfDogs = doc.getElementsByTagName("dog");
            int totalPersons = listOfDogs.getLength();
            System.out.println("Total no of dogs : " + totalPersons);

            for(int s=0; s<listOfDogs.getLength() ; s++){


                Node firstDogNode = listOfDogs.item(s);
                if(firstDogNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstDogElement = (Element)firstDogNode;

                    //-------
                    NodeList firstNameList = firstDogElement.getElementsByTagName("id");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("ID : " + 
                           ((Node)textFNList.item(0)).getNodeValue().trim());

                    //-------
                    NodeList dogNameList = firstDogElement.getElementsByTagName("name");
                    Element dogNameElement = (Element)dogNameList.item(0);

                    NodeList textLNList = dogNameElement.getChildNodes();
                    System.out.println("Name : " + 
                           ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ownerList = firstDogElement.getElementsByTagName("ownerId");
                    Element ageElement = (Element)ownerList.item(0);

                    NodeList textAgeList = ageElement.getChildNodes();
                    System.out.println("Owner ID : " + 
                           ((Node)textAgeList.item(0)).getNodeValue().trim());
                    
                    //System.out.println( "Name in string at index : " + s + " : " + peopleFNameArray[s]);

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    }
    
    public static void getCatsALT(){//Alternate form of getCats method ---- not used
    	try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("pets.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
                 //doc.getDocumentElement().getNodeName());


            NodeList listOfCats = doc.getElementsByTagName("cat");
            int totalPersons = listOfCats.getLength();
            System.out.println("Total no of cats : " + totalPersons);

            for(int s=0; s<listOfCats.getLength() ; s++){


                Node firstCatNode = listOfCats.item(s);
                if(firstCatNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstCatElement = (Element)firstCatNode;

                    //-------
                    NodeList firstNameList = firstCatElement.getElementsByTagName("id");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("ID : " + 
                           ((Node)textFNList.item(0)).getNodeValue().trim());

                    //-------
                    NodeList catNameList = firstCatElement.getElementsByTagName("name");
                    Element catNameElement = (Element)catNameList.item(0);

                    NodeList textLNList = catNameElement.getChildNodes();
                    System.out.println("Name : " + 
                           ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ownerList = firstCatElement.getElementsByTagName("ownerId");
                    Element ownerElement = (Element)ownerList.item(0);

                    NodeList textAgeList = ownerElement.getChildNodes();
                    System.out.println("Owner ID : " + 
                           ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    }

    public static void getDogs(){//Displays dogs in the XML file
    	try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("pets.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
                 //doc.getDocumentElement().getNodeName());


            NodeList listOfDogs = doc.getElementsByTagName("dog");
            int totalDogs = listOfDogs.getLength();
            System.out.println("Total no of dogs : " + totalDogs);

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
                    
                    owner(x);

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    }
    
    public static void getCats(){//Displays cats in the XML file
    	try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("pets.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
                 //doc.getDocumentElement().getNodeName());


            NodeList listOfCats = doc.getElementsByTagName("cat");
            int totalCats = listOfCats.getLength();
            System.out.println("Total no of cats : " + totalCats);

            for(int s=0; s<listOfCats.getLength() ; s++){


                Node catNode = listOfCats.item(s);
                if(catNode.getNodeType() == Node.ELEMENT_NODE){


                    Element catElement = (Element)catNode;

                    //-------
                    NodeList catIdList = catElement.getElementsByTagName("id");
                    Element catIdElement = (Element)catIdList.item(0);

                    NodeList textFNList = catIdElement.getChildNodes();
                    System.out.println("ID : " + 
                           ((Node)textFNList.item(0)).getNodeValue().trim());

                    //-------
                    NodeList catNameList = catElement.getElementsByTagName("name");
                    Element catNameElement = (Element)catNameList.item(0);

                    NodeList textLNList = catNameElement.getChildNodes();
                    System.out.println("Name : " + 
                           ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ownerList = catElement.getElementsByTagName("ownerId");
                    Element ownerElement = (Element)ownerList.item(0);

                    NodeList textOwnerList = ownerElement.getChildNodes();
                    System.out.println("Owner ID : " + 
                           ((Node)textOwnerList.item(0)).getNodeValue().trim());
                    
                    int x = Integer.parseInt(textOwnerList.item(0).getNodeValue());
                    owner(x);

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    }
    
    public static void owner(int b){//Displays who owns the dog or cat, using a person from the people element
    	int s = b-1;
    	
    	try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (new File("pets.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            //System.out.println ("Root element of the doc is " + 
                 //doc.getDocumentElement().getNodeName());


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


            


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
    	
    	
    }


}