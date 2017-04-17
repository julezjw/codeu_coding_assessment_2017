// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.io.IOException;
import java.util.*;

final class MyJSONParser implements JSONParser {

  @Override
  public JSON parse(String in) throws IOException  { //throws IOException

	  JSON json = new MyJSON(); 
	  
	    int index = 0; 
	    int i = 0; 
	    int end, nextindex; 
	    String str = in; 
	    String character, key, value; 
	    while (i < in.length()){ 
	    	character = in.substring(i, i+1); 
	    	if (character.equals("{")){
	    		//the start of an object 

	    		end = in.indexOf("}", -1); 
	    		//finding the end of the entire object 
	    		
	    		str = in.substring(i+1);
	    		
	    		while (index<str.length()){
	    	  		character = str.substring(index, index+1); 
	    	  		if (character.equals(" ")){
	    	  			//for white spaces, continue on 
	    	  			index++; 
	    	  		}
	    	  		if (character.equals("}")) 
	    	  			break; 
	    	  		
	    	  		else if (character.equals("\"")){
	    		  		//once we get to a "
	    	  			str = str.substring(index+1); 
	    	  			index = str.indexOf("\""); //index of the next "
	    	  			key = str.substring(0,index); //the name key
	    	  			str = str.substring(index+1);
	    	  			index = 0;
	    	  			nextindex = str.indexOf("\""); //index of the next "

	    	  			if (!str.contains(":")){
	    	  				throw new IOException("This is not a valid JSON Object");  
	    	  			}
	    	  			
	    	  			String check = str.substring(index,nextindex); 
	    	  			if (check.contains("{")){
	    	  				//if the value is an object, identified by the fact that 
	    	  				//there is a closed brace before the next quotes, 
	    	  				//Then we will make an object of it 
	    	  				int endvalue = str.indexOf("}");
	    	  				String val = str.substring(0,endvalue+1);
	    	  				JSON valueObject = parse(val); 
	    	  				json.setObject(key, valueObject); 
	    	  				str = str.substring(endvalue+1); 
	    	  			}
	    	  			
	    	  			else { 
	    	  				//if the value is just a string 
		    	  			str = str.substring(nextindex+1);
		    	  			index = str.indexOf("\""); //index of the next "
		    	  			value = str.substring(0,index); 
		    	  	  		json.setString(key, value);
	    	  			}
	    	  	  		
	    	  	  		
	    	  	  		if (str.contains(",")){
	    	  	  			index = str.indexOf(",");
	    	  	  			str = str.substring(index+1); 
	    	  	  			index = 0;
	    	  	  		}
	    	  	  		else {
	    	  	  			in = "";
	    	  	  			break;
	    	  	  		}
	    		  	}
	    		}

	    	}

	    	i++; 
	    }
	    return json; 
	  }
  



}
