# MOOCTextEditor   

The project **MOOCTextEditor or Smart Text Editor** was designed and developed as part of the Coursera course Data Structure and Performance that provides functionality of autocomplete, flagging misspelled words and spelling auto-correct. Each module of the course required implementing backend code for the text editor where front-end a runnable JavaFX program and certain graders and tester classes was provided by the UCSD to test and demonstrate the implementations.   

**MODULE 1:**   
  This module covered **working with Strings**. The programming assignment for this module was to generate the Flesch Readability Score of a text. The Flesch Readability Score is a measure of the reading complexity of text. Files introduced in this module:   
document.Document.java   
document.BasicDocument.java   

**Module 2:**   
  This module covered **Efficiency Analysis and Benchmarking** i.e. optimizing the implementation of the Document class that was implemented in Module 1, and then measure how much faster the new implementation is. Files introduced in this module:   
document.EfficientDocument.java   

**Module 3:**   
  This module covered implementing and testing a **Linked List** using **JUnit**. Files introduced in this module:   
textgen.MyLinkedList*.java   
textgen.MarkovTextGenerator.java    
textgen.MarkovTextGenerator*.java   

**Module 4:**   
  This module covered working with **Trees including Binary Search Trees and Tries**. The programming assignment for this module was adding the ability to flag misspelled words as well as perform auto-complete suggestions for the user's text as they type. Files introduced in this module:   
spelling.SpellingSuggest.java   
spelling.AutoComplete.java    
spelling.Dictionary.java   
spelling.Dictionary*.java   
spelling.AutoCompleteDictionaryTrie.java   
spelling.TrieNode.java    

**Module 5:**   
  This module covered **Hash Maps and edit distance**. The programming assignment for this module was to implement the highly practical feature of giving someone suggestions for how to correct a misspelled word. Files introduced in this module:   
spelling.WordPath.java   
spelling.NearbyWords.java   
spelling.WPTree.java   

----------------------------------------------------------------------------------------------------------------------------------------
   
## SETUP:   
Importing Project into eclipse:    
	1. Create a new Java Project in your workspace   
	2. Import the starter files:   
	  File -> Import -> Select "File System" -> Next -> Browse and set root directory to folder contents of zip were extracted to -> Finish   
  3. To use Java FX libraries which is used for GUI provided by UCSD in Eclipse you must install the e(fx)clipse extension for Eclipse. 

**Notes:**
* Make sure to use Java 8. The RichTextFX API (richtextfx-fat-0.6.10.jar) that is included in the MOOCTextEditor project does not work with Java 9.   
* Feel free to use another IDE or manually compile and run your programs.   
