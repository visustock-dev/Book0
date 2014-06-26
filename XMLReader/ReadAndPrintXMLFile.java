import java.io.File;

import org.w3c.dom.*;

import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

public class ReadAndPrintXMLFile{
public static void main (String[] args){

	for (String s: args) {//File will run for each string entered in the cmd separated by white space
        System.out.println(s);

        
        
        
        if(Checker(s) == 0){
        int fileValid = -1;
        
        if(s.matches(".*\\.xml.*$")){
        	System.out.println("file is type XML MATCHES SUCCESS");
        	fileValid = 0;
        }
        else{
        	System.out.println("MATCHES DID NOT DETECT XML");
        	fileValid = 1;
        }
        
    if(fileValid == 0){
    System.out.println("------------");
    
    
    
    Document doc = getDoc(s);
    
    
    
    String[][] People = getPeople(doc);
    
    System.out.println("------------");
    
    
    
    getPets(doc, "dog", People);
    System.out.println("------------");
    getPets(doc, "cat", People);
    }
    
	}
        System.out.println("------------");
	}
    }//end of main

/**
 * getDoc(s)
 * This method opens the specified file and creates a document variable to be used by the other methods
 * @Param String
 * @Returns Document
 */


    
    public static Document getDoc(String s){//Creates a document variable from the file input
    	Document doc = null;
	    try {

	            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	            doc = docBuilder.parse (new File(s));
	            doc.getDocumentElement ().normalize ();
	            System.out.println ("Root element of the doc is " + 
	                 doc.getDocumentElement().getNodeName());
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
	       
	    return doc;
} //getDoc()
   
    
    /**
     * getPeople(doc);
     * This method lists the people in the file
     * @Param Document
     * @Returns String[][]
     */
    
    
    public static String[][] getPeople(Document doc){//Displays the people in the XML file
    	try {

            NodeList listOfPersons = doc.getElementsByTagName("person");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of people : " + totalPersons);
            
            String[][] People = new String[totalPersons][3];
            
            int valid = 0;  
            String[] peopleIDArray;//----Declares an array of the owners First Names
            peopleIDArray = new String[listOfPersons.getLength()];
            
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
                    
                    if(RegExDigitChecker(((Node)textFNList.item(0)).getNodeValue().trim() ,  0)==0){
                    	
                    	People[s][1] = ((String)(textFNList.item(0)).getNodeValue().trim());//------------------This puts the persons first name into the second index location of the array for that person in the multidimensional array
                    	
                    }
                    else{
                    	People[s][1] = "ERROR";
                    }
                    
                    
                    
                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textLNList = lastNameElement.getChildNodes();
                    System.out.println("Last Name : " + 
                           ((Node)textLNList.item(0)).getNodeValue().trim());
                    
                    if(RegExDigitChecker(((Node)textLNList.item(0)).getNodeValue().trim() ,  0) == 0){
                    	People[s][2] = ((String)(textLNList.item(0)).getNodeValue().trim());//-----------------------Puts in the last name into the array location 
                    }
                    else{
                    	People[s][2] = "ERROR";
                    }
                    
                    
                    
                    //----
                    NodeList idList = firstPersonElement.getElementsByTagName("id");
                    Element idElement = (Element)idList.item(0);
                    
                    
                    
                    NodeList textIdList = idElement.getChildNodes();
                    System.out.println("Id : " + 
                    ((Node)textIdList.item(0)).getNodeValue().trim());
                    valid = 0;
                    try{
                        double d= Double.valueOf(((Node)textIdList.item(0)).getNodeValue().trim());
                        if (d==(int)d&&d>0){
                        	//ID is an integer above 0
                        	valid = 0;
                        }else{//ID is not an integer above 0
                        	System.out.println("ERROR, all IDs must be integer values");
                            System.out.println("Please change this id: "+ d);
                            valid = 1;
                        }
                    }catch(Exception e){
                    	valid = 1;
                        System.out.println("ERROR, IDs must be integers only");
                    }
                   
                    if(s == 0){
                    	for(int z = 0; z<totalPersons; z++){
                    		peopleIDArray[z] = "NoID";
                    		People[z][0] = "NoId";
                    		
                    		 
                    }
                    }
                    
                    if(s!= 0){
                    if(idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s )==0){
            			System.out.println("Error, duplicate IDs detected");
            			valid = 1;
                    }
                    }
                    if(valid==0){
                    	peopleIDArray[s] = ((Node)textIdList.item(0)).getNodeValue().trim();
                    }
                    
                    
                   
                    
                    
                   

	                    //----
	                    NodeList ageList = firstPersonElement.getElementsByTagName("age");
	                    Element ageElement = (Element)ageList.item(0);

	                    NodeList textAgeList = ageElement.getChildNodes();
	                    System.out.println("Age : " + 
	                           ((Node)textAgeList.item(0)).getNodeValue().trim());
                   // }

                    //------


                }//end of if clause


            }//end of for loop with s var
            
            for(int i = 0; i<totalPersons; i++){
            	People[i][0] = peopleIDArray[i]; 
            }
            
            for(int b = 0; b<totalPersons; b++){
            	//People[i][0] = peopleIDArray[b]; 
            	System.out.println(People[b][0]+"  " + People[b][1] + " " + People[b][2]);
            }
            return People;
            
          
            
            


        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);
		return null;
    	
    }
    
    
    /**
     * getPets(doc, "<petType>");
     * This method lists the pets and their owners from the file
     * @Param Document
     * @Param String
     * @Param String[][]
     */
    
    
    public static void getPets(Document doc, String petType, String[][] People){//Displays dogs in the XML file
    	
    	try {
    		
    		

            NodeList listOfDogs = doc.getElementsByTagName(petType);
            int totalDogs = listOfDogs.getLength();
            if (totalDogs == 0){
            	System.out.println("No " + petType + "s in this file!");
            }
            else {
            	System.out.println("Total no of " + petType + "s: " + totalDogs);
            	
            	String[] petIDArray;//----Declares an array of the pets IDs
            	petIDArray = new String[listOfDogs.getLength()];

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
                        
                        try{
                            double d= Double.valueOf(((Node)textFNList.item(0)).getNodeValue().trim());
                            if (d==(int)d&&d>0){
                            	//ID is an integer above 0
                            }else{//ID is not an integer above 0
                            	System.out.println("ERROR, all IDs must be integer values");
                                System.out.println("Please change this id: "+d);
                            }
                        }catch(Exception e){
                        	System.out.println("ERROR, IDs must be integers only");
                        }
                        
                        if(s == 0){
                        	for(int z = 0; z<totalDogs; z++){
                        		petIDArray[z] = "BLANK";

                        		 
                        }
                        }
                        
                        if(s!= 0){
                        if(idChecker(petIDArray, ((Node)textFNList.item(0)).getNodeValue().trim(), s )==0){
                			System.out.println("Error, duplicate IDs detected");
                        }
                        }
                        petIDArray[s] = ((Node)textFNList.item(0)).getNodeValue().trim();
                        

                        //-------
                        NodeList dogNameList = dogElement.getElementsByTagName("name");
                        Element dogNameElement = (Element)dogNameList.item(0);

                        NodeList textLNList = dogNameElement.getChildNodes();
                        System.out.println("Name : " + 
                               ((Node)textLNList.item(0)).getNodeValue().trim());
                        
                        
                        
                        RegExDigitChecker(((Node)textLNList.item(0)).getNodeValue().trim() ,  0);

                        //----
                        NodeList ownerList = dogElement.getElementsByTagName("ownerId");
                        Element ownerElement = (Element)ownerList.item(0);

                        NodeList textOwnerList = ownerElement.getChildNodes();
                        System.out.println("Owner ID : " + 
                               ((Node)textOwnerList.item(0)).getNodeValue().trim());
                        
                        int test = 0;
                        
                        try{
                            double d= Double.valueOf(((Node)textOwnerList.item(0)).getNodeValue().trim());
                            if (d==(int)d&&d>0){
                            	test = 0;
                            	//ID is an integer above 0
                            }else{//ID is not an integer above 0
                            	System.out.println("ERROR, all IDs must be integer values");
                                System.out.println("Please change this id: "+d);
                                test = 1;
                            }
                        }catch(Exception e){
                        	test = 1;
                        	System.out.println("ERROR, IDs must be integers only");
                        }
                        
                        
                        if(test==0){
                        	int x = Integer.parseInt(textOwnerList.item(0).getNodeValue());
                        	owner(x, doc, People);
                        	
                        }
                        else{
                        	System.out.println("Owner cannot be found due to non integer id for owner id");
                        }
                        

                        //------


                    }//end of if clause


                }//end of for loop with s var
                
                
                


            }
            
            }catch (Throwable t) {
            t.printStackTrace ();
            }
            //System.exit (0);
            }
            
    
    /**
     * owner()
     * This method prints the names of the owner of a pet based on the owner id from the person and the pet
     * @Param integer
     * @Param Document
     * @Param String[][]
     */
    
    public static void owner(int b, Document doc, String[][] People){//Displays who owns the dog or cat, using a person from the people element
    	
    	
    	
    	
    	
    	NodeList listOfPersons = doc.getElementsByTagName("person");
        int totalPersons = listOfPersons.getLength();

           


        int owner = -1;
        int success = 0;
       
        
        int ID = -1;
    	for(int i = 0; i<totalPersons; i++){
    		if(People[i][0] != "NoID"){
    			 ID = Integer.parseInt(People[i][0]);
    		}
    		else{
    			 ID = -1;
    		}
    		
    		if(ID == b){
    		
    			
    			owner = i;
    			i = (totalPersons);
    			i++;
    			success = 1;
    			
    		}	
    		
    	}
    	
    	
    	
    	
    	
    	
    	if(success == 1){
    		System.out.println("Owner: " + People[owner][1] + " " + People[owner][2]);
    	}
    	else{
    		System.out.println("No owner found with ID: " + b);
    	}
            		
            		
            		
            		
                  
    	
    }
    
    
    /**
     * idChecker()
     * This checks to make sure there are no duplicate IDs by checking if an ID has been entered into the array before
     * @param String[]
     * @param String
     * @param integer
     * @returns integer
     */
    public static int idChecker(String[] personIDArray, String ID, int Loc){
    	
    	
    	
    	
    	if(Arrays.asList(personIDArray).contains(ID)){
    		return 0;
    		
    	}
    	else{
    		return -1;
    	}
    	
    	
    	
    	
    	
    }
    
    /**
     * Checker(s)
     * This method checks to make sure that the file specified by the args exists before executing the rest of the program
     * @Param String
     * @Returns integer
     */
    
    public static int Checker(String s) {
    	 
  	  File f = new File(s);
   
  	  if(f.exists()){
  		  System.out.println("File existed");
  		  return 0;
  	  }else{
  		  System.out.println("File not found!");
  		
  		  return 1;
  	  }
   
    }
    
    /**
     * RegExDigitChecker()
     * This method checks to make sure that a name contains no numbers or an id contains no letters
     * @param String
     * @Param integer
     * @returns integer
     */
    
   public static int RegExDigitChecker(String x, int type){
	   if(type == 1){//This type will be used to check the ID's 
		   
		    if(x.matches(".*\\d.*")){//String does not contain a letter and contains a number
		       System.out.println("ID is valid");
		       return 0;
		   }
		   else{//ID does not contain a number or a letter
			   System.out.println("ERROR---------------------------");
			   return 1;
		   }
	   }
		   else if(type == 0){//This type will be used to check names
			   
			   if(x.matches(".*\\d.*")){//Contains a number
			       System.out.println("ERROR, names may not contain numbers");
			       return 1;
			   } else{
			       System.out.println("Name is valid");
			       return 0;
			   }
		   }


	   return 0;
   }

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

}