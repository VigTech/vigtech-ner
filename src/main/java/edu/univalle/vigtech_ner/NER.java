/*
 * To change this license header, choose License Headers in Project PropertiesManager.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.univalle.vigtech_ner;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.univalle.vigtech_ner.utils.Entity;
import edu.univalle.vigtech_ner.utils.PropertiesManager;

/**
 *
 * @author olaya
 */
public class NER {
	
	public ArrayList<Entity> getEntities(String serializedClassifier, String text) {
        // TODO Auto-generated method stub
		
		
		   
		if(serializedClassifier=="")
          serializedClassifier = PropertiesManager.getInstance().getProperty("default.classifier");
		
		System.out.println("Holaaaa: "+serializedClassifier);
		
		File path = new File(Thread.currentThread().getContextClassLoader().getResource(serializedClassifier).getFile());
		
		serializedClassifier = path.getAbsolutePath();
		
		
		System.out.println(path);
		
		ArrayList<Entity> entities = new ArrayList<Entity>();
           
            
            AbstractSequenceClassifier<CoreLabel> classifier = null;
			try {
				classifier = CRFClassifier.getClassifier(serializedClassifier);
			} catch (ClassCastException | ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
            /* For either a file to annotate or for the hardcoded text example,
               this demo file shows two ways to process the output, for teaching
               purposes.  For the file, it shows both how to run NER on a String
               and how to run it on a whole file.  For the hard-coded String,
               it shows how to run it on a single sentence, and how to do this
               and produce an inline XML output format.
            */

            String fileContents = text;
            List<List<CoreLabel>> out = classifier.classify(fileContents);
            
            String chunk="";
            String lastClass = "O";
            for (List<CoreLabel> sentence : out) {
              for (CoreLabel word : sentence) {
                  if (word.get(CoreAnnotations.AnswerAnnotation.class).equals("O")){
                      if (!lastClass.equals("O")){
                          Entity ent = new Entity();
                          ent.setEntity(chunk);
                          ent.setEntityClass(lastClass);
                          entities.add(ent);

                          chunk="";
                          lastClass = word.get(CoreAnnotations.AnswerAnnotation.class);
                      }
                  }else{
                      chunk+= " "+word;
                      lastClass = word.get(CoreAnnotations.AnswerAnnotation.class);
                  }
                   
              }
            }
             
           
          	
         

          
             
            return entities;  
    }
}
