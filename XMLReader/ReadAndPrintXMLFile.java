import java.io.File;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class ReadAndPrintXMLFile{
public static void main (String argv []){
    //readXML();
    getPeople();
    System.out.println("------------");
    getDogs();
    System.out.println("------------");
    //getDogsALT();
    //System.out.println("------------");
    getCats();
    //getCatsALT();
    }//end of main
    public static void readXML(){
    	
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
    
    public static void getPeople(){
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
    
    public static void getDogsALT(){
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
    
    public static void getCatsALT(){
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

    public static void getDogs(){
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
    
    public static void getCats(){
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


}