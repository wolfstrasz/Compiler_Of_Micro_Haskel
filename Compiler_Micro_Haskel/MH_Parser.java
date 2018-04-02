
// File:   MH_Parser.java
// Date:   October 2013, modified October 2016.

// Java template file for parser component of Informatics 2A Assignment 1.
// Students should add a method body for tableEntry, implementing the LL(1) parse table for Micro-Haskell.


import java.io.* ;
 
class MH_Parser extends GenParser implements PARSER {

    String startSymbol() {return "#Prog" ;}
	
  
    // Right hand sides of all productions in grammar:

    static String[] epsilon              = new String[] { } ;
    static String[] Decl_Prog            = new String[] {"#Decl", "#Prog"} ;
    static String[] TypeDecl_TermDecl    = new String[] {"#TypeDecl", "#TermDecl"} ;
    static String[] VAR_has_Type         = new String[] {"VAR", "::", "#Type", ";"} ;
    static String[] Type1_TypeOps        = new String[] {"#Type1", "#TypeOps"} ;
    static String[] arrow_Type           = new String[] {"->", "#Type"} ;
    static String[] Integer              = new String[] {"Integer"} ;
    static String[] Bool                 = new String[] {"Bool"} ;
    static String[] lbr_Type_rbr         = new String[] {"(", "#Type", ")"} ;
    static String[] VAR_Args_eq_Exp      = new String[] {"VAR", "#Args", "=", "#Exp", ";"} ;
    static String[] VAR_Args             = new String[] {"VAR", "#Args"} ;
    static String[] Exp1                 = new String[] {"#Exp1"} ;
    static String[] if_then_else         = new String[] {"if", "#Exp", "then", "#Exp", "else", "#Exp"} ;
    static String[] Exp2_Op1             = new String[] {"#Exp2", "#Op1"} ;
    static String[] eqeq_Exp2            = new String[] {"==", "#Exp2"} ;
    static String[] lteq_Exp2            = new String[] {"<=", "#Exp2"} ;
    static String[] Exp3_Ops2            = new String[] {"#Exp3", "#Ops2"} ;
    static String[] plus_Exp3_Ops2       = new String[] {"+", "#Exp3", "#Ops2"} ;
    static String[] minus_Exp3_Ops2      = new String[] {"-", "#Exp3", "#Ops2"} ;
    static String[] Exp4_Ops3            = new String[] {"#Exp4", "#Ops3"} ;
    static String[] VAR                  = new String[] {"VAR"} ;
    static String[] NUM                  = new String[] {"NUM"} ;
    static String[] BOOLEAN              = new String[] {"BOOLEAN"} ;
    static String[] lbr_Exp_rbr          = new String[] {"(", "#Exp", ")"} ;

    // May add auxiliary methods here if desired
    // int isErrorsFound = 0;
	//private void errorFound(String nonterm, String tokClass)
	//{
	//	if(this.isErrorsFound == 0 )
	//	{
	//		System.err.printf("Document cannot be parsed! Error occured!\n");
	//	}
		
	//	this.isErrorsFound++;
	//	System.err.printf("Parse error found in: %s\n\t%s is expected!\n\tError number: %d \n" , tokClass, nonterm, this.isErrorsFound);
	//}
	
    String[] tableEntry (String nonterm, String tokClass) {

        // Add code here
    	switch(nonterm)
    	{
    	case "#Prog":
    		if(tokClass == null)return epsilon;
    		switch(tokClass)
    		{
    		//case null: return epsilon;
    		case "VAR": return Decl_Prog;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    			
    		}
    	case "#Decl":
    		switch(tokClass)
    		{
    		case "VAR": return TypeDecl_TermDecl;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    		}
    	case "#TypeDecl":
    		switch(tokClass)
    		{
    		case "VAR": return VAR_has_Type;
    			default: /* errorFound(nonterm,tokClass);*/ return null;
    		}
    	case "#Type":
    		switch(tokClass)
    		{
    		case "Integer": return Type1_TypeOps;
    		case "Bool": return Type1_TypeOps;
    		case "(": return Type1_TypeOps;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    		}
    	case "#TypeOps":
    		switch(tokClass)
    		{
    		case ")": return epsilon;
    		case ";": return epsilon;
    		case "->": return arrow_Type;
    			default: errorFound(nonterm,tokClass); return null;
    		}
    	case "#Type1":
    		switch(tokClass)
    		{
    		case "Integer": return Integer;
    		case "Bool": return Bool;
    		case "(": return lbr_Type_rbr;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    		}
    	case "#TermDecl":
    		if(tokClass == null) return epsilon;
    		switch(tokClass)
    		{
    		//case null : return epsilon;
    		case "VAR": return VAR_Args_eq_Exp;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    		}
    		
    	case "#Args":
    		switch(tokClass)
    		{
    		case "=": return epsilon;
    		case "VAR": return VAR_Args;
    			default: /* errorFound(nonterm,tokClass);*/ return null;
    		}
    		
    	case "#Exp":
    		switch(tokClass)
    		{
    		case "VAR": return Exp1;
    		case "NUM": return Exp1;
    		case "BOOLEAN": return Exp1;
    		case "(": return Exp1;
    		case "if": return if_then_else;
    			default:/* errorFound(nonterm,tokClass); */return null;
    		}
    	case "#Exp1":
    		switch(tokClass)
    		{
    		case "VAR": return Exp2_Op1;
    		case "NUM": return Exp2_Op1;
    		case "BOOLEAN": return Exp2_Op1;
    		case "(": return Exp2_Op1;
    			default: /*errorFound(nonterm,tokClass); */return null;
    		}

    	case "#Exp2":
    		switch(tokClass)
    		{
    		case "VAR": return Exp3_Ops2;
    		case "NUM": return Exp3_Ops2;
    		case "BOOLEAN": return Exp3_Ops2;
    		case "(": return Exp3_Ops2;
    			default: /*errorFound(nonterm,tokClass); */return null;
    		}
    	case "#Exp3":
    		switch(tokClass)
    		{
    		case "VAR": return Exp4_Ops3;
    		case "NUM": return Exp4_Ops3;
    		case "BOOLEAN": return Exp4_Ops3;
    		case "(": return Exp4_Ops3;
    			default:/* errorFound(nonterm,tokClass); */return null;
    		}
    	case "#Exp4":
    		switch(tokClass)
    		{
    		case "VAR": return VAR;
    		case "NUM": return NUM;
    		case "BOOLEAN": return BOOLEAN;
    		case "(": return lbr_Exp_rbr;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    		}
    	case "#Op1":
    		switch(tokClass)
    		{
    		case "then": return epsilon;
    		case "else": return epsilon;
    		case ";": return epsilon;
    		case ")": return epsilon;
    		case "==": return eqeq_Exp2;
    		case "<=": return lteq_Exp2;
    		
    			default:/* errorFound(nonterm,tokClass);*/return null;
    		}
    	case "#Ops2":
    		switch(tokClass)
    		{
    		case "then": return epsilon;
    		case "else": return epsilon;
    		case ")": return epsilon;
    		case ";": return epsilon;
    		case "==": return epsilon;
    		case "<=": return epsilon;
    		case "+": return plus_Exp3_Ops2;
    		case "-": return minus_Exp3_Ops2;
    			default: /*errorFound(nonterm,tokClass);*/ return null;
    		}
    	case "#Ops3":
    		switch(tokClass)
    		{
    		case "then": return epsilon;
    		case "else": return epsilon;
    		case ")": return epsilon;
    		case ";": return epsilon;
    		case "+": return epsilon;
    		case "-": return epsilon;
    		case "==": return epsilon;
    		case "<=": return epsilon;
    		
    		case "VAR": return Exp4_Ops3;
    		case "NUM": return Exp4_Ops3;
    		case "BOOLEAN": return Exp4_Ops3;
    		case "(" : return Exp4_Ops3;
    			default:/* errorFound(nonterm,tokClass);*/ return null;
    		}
    	
    		default: return null;
    	}


    }
}


// For testing

class MH_ParserDemo {

    static PARSER MH_Parser = new MH_Parser() ;

    public static void main (String[] args) throws Exception {
	Reader reader = new BufferedReader (new FileReader (args[0])) ;
	LEX_TOKEN_STREAM MH_Lexer = 
	    new CheckedSymbolLexer (new MH_Lexer (reader)) ;
	TREE theTree = MH_Parser.parseTokenStream (MH_Lexer) ;
    }
}

