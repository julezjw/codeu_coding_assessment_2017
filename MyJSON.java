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

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

final class MyJSON implements JSON {
	private HashMap<String,JSON> jsons = new HashMap<String,JSON>();  
	//every JSON object has its names and values stored as a hashmap 
	//if a key has a string type value, it will be stored as a 
	//JSON object with the value stored as the key in a JSON hashmap 
	//and null as its value 

  @Override
  public JSON getObject(String name) {
    if (jsons.containsKey(name)) 
    	return jsons.get(name);
    return null;
  }

  @Override
  public JSON setObject(String name, JSON value) {
	  this.jsons.put(name, value);
	  return this;
  }

  @Override
  public Set<String> getNames(){
	  //gets the names of all values in jsons 
	  return jsons.keySet();
  }
  
  @Override
  public String getString(String name) {
	    if (jsons.containsKey(name)) 
	    	return jsons.get(name).getNames().iterator().next();
	    return null;
  }

  @Override
  public JSON setString(String name, String value) {
	JSON valueobject = new MyJSON(); 
	valueobject.setObject(value, null); 
	
	this.jsons.put(name, valueobject); 
    return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
	  for (JSON object : jsons.values() ){ 
		  if (object.getObject(object.getNames().iterator().next()) != null){ 
			  //only add names if they have object values 
			  names.add(object.getNames().iterator().next()); 
		  }
		  
	  }
  }

  @Override
  public void getStrings(Collection<String> names) {
	  for (JSON object : jsons.values() ){ 
		  if (object.getObject(object.getNames().iterator().next()) == null){ 
			  //only add names if they are strings aka they have null values 
			  names.add(object.getNames().iterator().next()); 
		  }
		  
	  }
  }
}
