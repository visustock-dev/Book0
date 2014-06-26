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
        //String i = "xml$";
        //String [] splitString = s.split("xml\\$");  //s.split("xml//$");
        
        
        
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
 */


    
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
	    return doc;
} //getDoc()
   
    
    /**
     * getPeople(doc);
     * This method lists the people in the file
     * @Param Document
     */
    
    
    public static String[][] getPeople(Document doc){//Displays the people in the XML file
    	try {

            NodeList listOfPersons = doc.getElementsByTagName("person");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of people : " + totalPersons);
            
            String[][] People = new String[totalPersons][3];
            
            int valid = 0;
            
            
            /*String[] peopleFNameArray;//----Declares an array of the owners First Names
            peopleFNameArray = new String[listOfPersons.getLength()];
            
            String[] peopleLNameArray;//----Declares an array of the owners Last Names
            peopleLNameArray = new String[listOfPersons.getLength()];*/
            
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
                    	//nameValid = 1;
                    	People[s][1] = ((String)(textFNList.item(0)).getNodeValue().trim());//------------------This puts the persons first name into the second index location of the array for that person in the multidimensional array
                    	
                    }
                    else{
                    	People[s][1] = "ERROR";
                    }
                    
                    
                    //People[s][1] = ((String)(textFNList.item(0)).getNodeValue().trim());//------------------This puts the persons first name into the second index location of the array for that person in the multidimensional array
                   // System.out.println( "Name in string at index : " + s + " : " + peopleFNameArray[s]);

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
                    
                    //People[s][2] = ((String)(textLNList.item(0)).getNodeValue().trim());//-----------------------Puts in the last name into the array location 
                  //  System.out.println( "Name in string at index : " + s + " : " + peopleLNameArray[s]);
                    
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
                    //if (s == 0){
                    
                    //RegExDigitChecker(((Node)textIdList.item(0)).getNodeValue().trim() ,  1);
                    //if(RegExDigitChecker(((Node)textIdList.item(0)).getNodeValue().trim() ,  1) == 1){
                    	//System.out.println("ERROR");
                    //}
                    if(s == 0){
                    	for(int z = 0; z<totalPersons; z++){
                    		peopleIDArray[z] = "NoID";
                    		People[z][0] = "NoId";
                    		//peopleIDArray[z] = ((Node)textIdList.item(0)).getNodeValue().trim();
                    		//if(idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), z )==0){
                    		//	System.out.println("Error, duplicate IDs detected");
                    		//}
                    		 
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
                    
                    
                   /* System.out.println(idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s ) + "   " + s);
                    //if(s != 0){
	                    	
	                    //if(s!=0){
	                    		//peopleIDArray[s] = ((Node)textIdList.item(0)).getNodeValue().trim();
	                    	//}
	                    
	                    if (idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s ) == -1){
	                    	
	                    	
	                    	System.out.println( "Id in string at index : " + s + " : " + peopleIDArray[s]);
	                    }
	                    else{
	                    	//if((s)!=idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s )){
	                    		System.out.println("Warning, duplicate ID's found for " + peopleFNameArray[idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s )] + " " + peopleLNameArray[idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s )] + " and " + peopleFNameArray[s] + " " + peopleLNameArray[s]);
	                    	//}
	                    	//else{
	                    	//	System.out.println("SUCCESS!");
	                    	//}
	                    	
	                    }*/

                    	
                   // }
                    
                   // for (int a = 0; a<totalPersons; a++){
                    	//System.out.println("test: "+ peopleIDArray[a]);
                    	//if (peopleIDArray[a] == ((Node)textIdList.item(0)).getNodeValue().trim()){
                    		//System.out.println("Success!");
                    	//}
                    //}
                    
                    
                    
                   

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
            
          
            
            /*for(int o = 0; o<totalPersons; o++){
            	String ID = peopleIDArray[o];
            	 System.out.println(idChecker(peopleIDArray, ID, o ) + "   " + o);
                 //if(s != 0){
	                    	
	                    //if(s!=0){
	                    		//peopleIDArray[s] = ((Node)textIdList.item(0)).getNodeValue().trim();
	                    	//}
	                    
	                    if (idChecker(peopleIDArray, ID, o ) == -1){
	                    	
	                    	
	                    	System.out.println( "Id in string at index : " + o + " : " + peopleIDArray[o]);
	                    }
	                    else{
	                    	//if((s)!=idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s )){
	                    		System.out.println("Warning, duplicate ID's found for " + peopleFNameArray[idChecker(peopleIDArray, ID, o )] + " " + peopleLNameArray[idChecker(peopleIDArray, ID, o )] + " and " + peopleFNameArray[o] + " " + peopleLNameArray[o]);
	                    	//}
	                    	//else{
	                    	//	System.out.println("SUCCESS!");
	                    	//}
	                    	
	                    }
	                    
            }*/


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
                        		//peopleIDArray[z] = ((Node)textIdList.item(0)).getNodeValue().trim();
                        		//if(idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), z )==0){
                        		//	System.out.println("Error, duplicate IDs detected");
                        		//}
                        		 
                        }
                        }
                        
                        if(s!= 0){
                        if(idChecker(petIDArray, ((Node)textFNList.item(0)).getNodeValue().trim(), s )==0){
                			System.out.println("Error, duplicate IDs detected");
                        }
                        }
                        petIDArray[s] = ((Node)textFNList.item(0)).getNodeValue().trim();
                        /*if(s == 0){
                        	for(int z = 0; z<totalDogs; z++){
                        		petIDArray[z] = ((Node)textFNList.item(0)).getNodeValue().trim();
                        }
                        }*/

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
                        
                        //int x;
                        
                        //x = ((Node)textOwnerList.item(0)).getNodeValue().trim();
                        //System.out.println(x);
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
                
                /*for(int o = 0; o<totalDogs; o++){
                	String ID = petIDArray[o];
                	 System.out.println(idChecker(petIDArray, ID, o ) + "   " + o);
                     //if(s != 0){
    	                    	
    	                    //if(s!=0){
    	                    		//peopleIDArray[s] = ((Node)textIdList.item(0)).getNodeValue().trim();
    	                    	//}
    	                    
    	                    if (idChecker(petIDArray, ID, o ) == -1){
    	                    	
    	                    	
    	                    	System.out.println( "Id in string at index : " + o + " : " + petIDArray[o]);
    	                    }
    	                    else{
    	                    	//if((s)!=idChecker(peopleIDArray, ((Node)textIdList.item(0)).getNodeValue().trim(), s )){
    	                    		System.out.println("Warning, duplicate pet IDs found");
    	                    	//System.out.println("Warning, duplicate ID's found for " + petFNameArray[idChecker(peopleIDArray, ID, o )] + " " + peopleLNameArray[idChecker(peopleIDArray, ID, o )] + " and " + peopleFNameArray[o] + " " + peopleLNameArray[o]);
    	                    	//}
    	                    	//else{
    	                    	//	System.out.println("SUCCESS!");
    	                    	//}
    	                    	
    	                    }
    	                    
                }*/
                


            }
            
            }catch (Throwable t) {
            t.printStackTrace ();
            }
            //System.exit (0);
            }
            
    
    /**
     * @owner(int b, Document doc);
     * This method prints the names of the owner of a pet based on the owner id from the person and the pet
     */
    
    public static void owner(int b, Document doc, String[][] People){//Displays who owns the dog or cat, using a person from the people element
    	//int s = b-1;
    	int p = -1;
    	
    	//try {
    	
    	NodeList listOfPersons = doc.getElementsByTagName("person");
        int totalPersons = listOfPersons.getLength();

            //NodeList listOfPersons = doc.getElementsByTagName("person");
            //int totalPersons = listOfPersons.getLength();
            //System.out.println("Total no of people : " + totalPersons);
            
            //String[] peopleFNameArray;//----Declares an array of the owners First Names
            //peopleFNameArray = new String[listOfPersons.getLength()];
            
            //String[] peopleLNameArray;//----Declares an array of the owners Last Names
            //peopleLNameArray = new String[listOfPersons.getLength()];
            //peopleArray = new String[10];
            

            


                /*Node firstPersonNode = listOfPersons.item(s);
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
                    //NodeList idList = firstPersonElement.getElementsByTagName("id");
                    //Element idElement = (Element)idList.item(0);
                    
                   // NodeList textIdList = idElement.getChildNodes();
                   // System.out.println("Id : " + 
                   // ((Node)textIdList.item(0)).getNodeValue().trim());

                    //----
                   // NodeList ageList = firstPersonElement.getElementsByTagName("age");
                    //Element ageElement = (Element)ageList.item(0);

                    //NodeList textAgeList = ageElement.getChildNodes();
                   // System.out.println("Age : " + 
                     //      ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------*/
        int owner = -1;
        int success = 0;
        /*for(int a = 0; a<totalPersons; a++){
        	//People[i][0] = peopleIDArray[b]; 
        	System.out.println(People[a][0]+"  " + People[a][1] + " " + People[a][2]);
        }*/
        //return People;
        //System.out.println(String.valueOf(b));
        //String number = String.valueOf(b);
        
        //System.out.println(number);
        
        //int y = -1;
        //Integer.parseInt(People[i][0]);
        
        //number = number + 1;
        //System.out.println(number);
        
        int ID = -1;
    	for(int i = 0; i<totalPersons; i++){
    		if(People[i][0] != "NoID"){
    			 ID = Integer.parseInt(People[i][0]);
    		}
    		else{
    			 ID = -1;
    		}
    		
    		if(ID == b){
    		//if(People[i][0] == number){
    			
    			owner = i;
    			i = (totalPersons);
    			i++;
    			success = 1;
    			
    		}	
    		//System.out.println("ID:    " + People[i][0] );
    	}
    	
    	
    	
    	
    	
    	
    	if(success == 1){
    		System.out.println("Owner: " + People[owner][1] + " " + People[owner][2]);
    	}
    	else{
    		System.out.println("No owner found with ID: " + b);
    	}
            		
            		
            		
            		
                    //System.out.println( "Owner : " + peopleFNameArray[s] + " " + peopleLNameArray[s]);


                //}//end of if clause


            


        //}catch (Throwable t) {
        //t.printStackTrace ();
        //}
        //System.exit (0);
    	
    	
    }
    
    public static int idChecker(String[] personIDArray, String ID, int Loc){
    	
    	//String[] peopleIDArray;
    	//peopleIDArray = new String[personIDArray.length];
    	int a = 0;
    	int location = 0;
    	int x = 0;
    	String IDX;
    	//System.out.println(loc);
    	
    	//if (a ==0&&location == 0){
    		//System.out.println("Success");
    	//}
    	
    	
    	/*for (int x = 0; x < personIDArray.length; x++){
    		
    		IDX = personIDArray[x];
    		if (x != loc){
    			
    		
	    		if (IDX == ID){
	    			a = 1;
	    			location = x;
	    			
	    			
	    			//if (x == loc){
	    				//a = 0;
	    			//}
	    			//else{
	    				x = 10;
	    			//}
	    			
	    		}
    		}
    	}*/
    	
    	//for (String s : VALUES) if (s.equals("MYVALUE")) return true;
    	//int loc = Integer.parseInt(Loc);
    	if(Arrays.asList(personIDArray).contains(ID)){
    		return 0;
    		//System.out.println("CHECK LENGTH " + personIDArray.length);
    		/*for(x = 0; x < personIDArray.length; x++ ){
    			//System.out.println("CHECK LOOP " + x);
    			//System.out.println("CHECK LOOP ID IDX " + ID + "    " + personIDArray[x]);
    			
    			if (x != Loc){
    				if(personIDArray[x] == ID){
    					//System.out.println("CHECK" + x);
    					return x;
    				}
    			}
    			
    			
    			
    			
    			/*
    			System.out.println("CHECK LOOP " + x);
    			System.out.println("CHECK LOOP ID IDX " + ID + "    " + personIDArray[x]);
    			IDX = personIDArray[x];
    			if (IDX == ID){
    				
    				//if(x != Loc){
    					//x= x-1;
    					//return x;
    				//}
    				if (x==Loc){
    					System.out.println("CHECK" + x);
    					return -1;
    			}
    				else{
    					System.out.println("CHECK8");
    					return x;
    				}
    			}
    			*/
    		//}
    		//return 1;
    	//}
    	}
    	else{
    		return -1;
    	}
    	
    	
    	/*if (a == 1){
    		System.out.println("Warning, duplicate ID's found in file.");
    		return location;
    	}
    	else{
    		System.out.println("ERROR");
    		return -1;
    	}*/
    	
    	
    }
    
    /**
     * Checker(s)
     * This method checks to make sure that the file specified by the args exists before executing the rest of the program
     * @Param String
     */
    
    public static int Checker(String s) {
    	 
  	  File f = new File(s);
   
  	  if(f.exists()){
  		  System.out.println("File existed");
  		  return 0;
  	  }else{
  		  System.out.println("File not found!");
  		//System.exit(1);
  		  return 1;
  	  }
   
    }
    
   public static int RegExDigitChecker(String x, int type){
	   if(type == 1){//This type will be used to check the ID's 
		   //String strWithNumber = "This string has a 1 number";
		   //if(x.matches(".*\\w.*")){//String contains a letter
		       //System.out.println("ERROR, IDs may not contain a letter");
		  // }
		   /*else*/ if(x.matches(".*\\d.*")){//String does not contain a letter and contains a number
		       System.out.println("ID is valid");
		       return 0;
		   }
		   else{//ID does not contain a number or a letter
			   System.out.println("ERROR---------------------------");
			   return 1;
		   }
	   }
		   else if(type == 0){//This type will be used to check names
			   //String strWithoutNumber = "This string does not have a number";
			   if(x.matches(".*\\d.*")){//Contains a number
			       System.out.println("ERROR, names may not contain numbers");
			       return 1;
			   } else{
			       System.out.println("Name is valid");
			       return 0;
			   }
		   }








		   /*//if(x.matches(".*\\D.*")){//ID contains a non digit
		   if(x.matches(".*[a-zA-Z]*")){
	           //System.out.println("ERROR, the specified");
			   System.out.println("ERROR, ID must contain only a number.");
			   return 1;
	       } else{//ID does not contain a non digit
	           
	           return 0;
	       }
	   }
	   else if(type == 0){//This type will be used to check the names
		   if(x.matches(".*\\d.*")){//Name contains a number
	           //System.out.println("ERROR, the specified");
			   System.out.println("ERROR, names cannot contain a number.");
			   return 1;
	       } else{//Name does not contain a number
	           //System.out.println("ERROR, names cannot contain a number.");
	           return 0;
	       }
	   }
	   
	   return 0;*/
	   return 0;
   }

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

}