package com.mph.ghost.ghost1.aframework.util;

import java.util.Collection;

public class CollectionUtils {
	public static boolean isEmpty(Collection<? extends Object> collection){
		if (collection==null) {
			return true;
		}
		if(collection.isEmpty()){
			return true;
		}
		return false;
	}
}
