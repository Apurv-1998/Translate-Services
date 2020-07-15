package com.mobileapplication.exposys.translate.utils;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ConvertListToString {
	
	
	public String convertListToString(List<String> text) {
		String s = "";
		
		for(int i=0;i<text.size()-1;i++)
			s+=text.get(i)+" ";
		s+=text.get(text.size()-1);
		
		return s;
	}
}
