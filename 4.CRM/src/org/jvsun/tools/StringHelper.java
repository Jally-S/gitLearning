package org.jvsun.tools;

public class StringHelper {
	public static boolean equals( String one , String theOther) {  
        return equals(one, theOther,true,false);  
    }
	public static boolean equals( String one , String theOther , boolean escapeSpace , boolean ignoreCase) {  
		
        if( one == null || theOther == null ){  
            return false ;  
        }  
          
        if( escapeSpace ){  
            one = one.trim();  
            theOther = theOther.trim();  
        }  
          
        return ignoreCase ? one.equalsIgnoreCase( theOther ) : one.equals( theOther ) ;  
    }  
	public static boolean emptyString( String source ) {  
        return ( source != null ) && source.length() == source.trim().length() ;  
    }
}
