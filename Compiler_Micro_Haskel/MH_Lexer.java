
// File:   MH_Lexer.java
// Date:   October 2013, modified October 2016.

// Java template file for lexer component of Informatics 2A Assignment 1.
// Concerns lexical classes and lexer for the language MH (`Micro-Haskell').

import java.io.* ;

class MH_Lexer extends GenLexer implements LEX_TOKEN_STREAM {

static class VarAcceptor extends GenAcceptor implements DFA {
    // add code here
    public String lexClass() {return "VAR" ;} ;
    public int numberOfStates() {return 3 ;} ;
    
    int nextState(int state, char c)
    {
    	switch(state)
    	{
    	case 0: if(CharTypes.isSmall(c)) return 1; else return 2;
    	case 1: if(CharTypes.isSmall(c) ||
    				CharTypes.isLarge(c) ||
    				CharTypes.isDigit(c) ||
    				c == '\'') return 1; else return 2;
    	default: return 2;
    	}
    }
    
    boolean accepting(int state){return state==1;}
    int deadState(){return 2;}
}

static class NumAcceptor extends GenAcceptor implements DFA {
    // add code here
    public String lexClass() {return "NUM" ;} ;
    public int numberOfStates() {return 4 ;} ;
    
    int nextState(int state, char c)
    {
    	switch(state)
    	{
    	case 0: if(c == '0')return 1;
    			else if(CharTypes.isDigit(c))return 2;
    			else return 3;
    	case 1: return 3;
    	case 2: if(CharTypes.isDigit(c))return 2; else return 3;
    	default: return 3;
    	}
    }
    
    boolean accepting(int state){return (state == 1 || state == 2);}
    int deadState(){return 3;}	
}

static class BooleanAcceptor extends GenAcceptor implements DFA {
	// add code here
    public String lexClass() {return "BOOLEAN" ;} ;
    public int numberOfStates() {return 9;} ;
    
    int nextState(int state, char c)
    {
    	switch(state)
    	{
    	case 0: if(c == 'T')return 1;
    			else if (c == 'F')return 2;
    			else return 8;
    	case 1: if(c == 'r') return 3; else return 8;
    	case 2: if(c == 'a') return 4; else return 8;
    	case 3: if(c == 'u') return 5; else return 8;
    	case 4: if(c == 'l') return 6; else return 8;
    	case 5: if(c == 'e') return 7; else return 8;
    	case 6: if(c == 's') return 5; else return 8;
    	case 7: return 8;
    	default: return 8;
    	}
    }
    
    boolean accepting(int state){return state==7;}
    int deadState(){return 8;}	
}

static class SymAcceptor extends GenAcceptor implements DFA {
    // add code here
    public String lexClass() {return "SYM" ;} ;
    public int numberOfStates() {return 3 ;} ;
    
    int nextState(int state, char c)
    {
    	switch(state)
    	{
    	case 0: if(CharTypes.isSymbolic(c))return 1; else return 2;
    	case 1: if(CharTypes.isSymbolic(c))return 1; else return 2;
    	default: return 2;
    	}
    }
    
    boolean accepting(int state){return state==1;}
    int deadState(){return 2;}	
}

static class WhitespaceAcceptor extends GenAcceptor implements DFA {
    // add code here
    public String lexClass() {return "" ;} ;
    public int numberOfStates() {return 3 ;} ;
    
    int nextState(int state, char c)
    {
    	switch(state)
    	{
    	case 0: if(CharTypes.isWhitespace(c))return 1; else return 2;
    	case 1: if(CharTypes.isWhitespace(c))return 1; else return 2;
    	default: return 2;
    	}
    }
    
    boolean accepting(int state){return state==1;}
    int deadState(){return 2;}	
}

static class CommentAcceptor extends GenAcceptor implements DFA {
    // add code here
    public String lexClass() {return "" ;} ;
    public int numberOfStates() {return 6 ;} ;
    
    int nextState(int state, char c)
    {
    	switch(state)
    	{
    	case 0: if(c == '-')return 1; else return 5;
    	case 1: if(c == '-')return 2; else return 5;
    	case 2: if(c == '-')return 2;
    			else if(!CharTypes.isNewline(c) &&
    					!CharTypes.isSymbolic(c))return 3;
    			else return 5;
    	case 3: if(!CharTypes.isNewline(c)) return 3;
    			else if(CharTypes.isNewline(c)) return 4;
    			else return 5;
    	default: return 5;
    	}
    }
    
    boolean accepting(int state){return state==4;}
    int deadState(){return 5;}	
}

static class TokAcceptor extends GenAcceptor implements DFA {

    String tok ;
    int tokLen ;
    TokAcceptor (String tok) {this.tok = tok ; tokLen = tok.length() ;}

    // add code here
    public String lexClass() {return this.tok;} ;
    public int numberOfStates() {return tokLen+2 ;} ; // States for the string chars + accepting + dead
    
    
    int nextState(int state, char c)
    {
    	if(state<tokLen)
    	{
    		if(c == tok.charAt(state)) return state+1;
    		else return tokLen+1;
    	}
    	else return tokLen+1;
    }
    
    boolean accepting(int state){return state == tokLen;}
    int deadState(){return tokLen+1;}	
}

    // add definition of MH_acceptors here
	static DFA varAcceptor = new VarAcceptor();
	static DFA numAcceptor = new NumAcceptor();
	static DFA booleanAcceptor = new BooleanAcceptor();
	static DFA symAcceptor = new SymAcceptor();
	static DFA whitespaceAcceptor = new WhitespaceAcceptor();
	static DFA commentAcceptor = new CommentAcceptor();
	static DFA integerAcceptor = new TokAcceptor("Integer");
	static DFA ifAcceptor = new TokAcceptor("if");
	static DFA boolAcceptor = new TokAcceptor("Bool");
	static DFA thenAcceptor = new TokAcceptor("then");
	static DFA elseAcceptor = new TokAcceptor("else");
	static DFA openBracketAcceptor = new TokAcceptor("(");
	static DFA closedBracketAcceptor = new TokAcceptor(")");
	static DFA semicolonAcceptor = new TokAcceptor(";");

 static DFA[] MH_acceptors = new DFA[] { 
			integerAcceptor, boolAcceptor, thenAcceptor,
			elseAcceptor, ifAcceptor, varAcceptor,
			numAcceptor, boolAcceptor, symAcceptor,
			openBracketAcceptor,closedBracketAcceptor,
			semicolonAcceptor, whitespaceAcceptor, commentAcceptor };


    MH_Lexer (Reader reader) {
	super(reader,MH_acceptors) ;
    }

}
