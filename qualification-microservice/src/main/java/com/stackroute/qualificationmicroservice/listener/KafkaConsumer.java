package com.stackroute.qualificationmicroservice.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.stackroute.qualificationmicroservice.indexermodel.Indexer;
import com.stackroute.qualificationmicroservice.indexermodel.Relationship;
import com.stackroute.qualificationmicroservice.indexermodel.TargetNodeProperty;
import com.stackroute.qualificationmicroservice.model.Qualification;
import com.stackroute.qualificationmicroservice.relationshipmodel.StudiedAtRelationshipProperties;

@Service
public class KafkaConsumer {
	
	 private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
	 public TargetNodeProperty targetNodeProperty = new TargetNodeProperty();
	 public StudiedAtRelationshipProperties studiedAtRelationshipProperties = new StudiedAtRelationshipProperties();
	 
	 public Indexer studiedAtIndexer= new Indexer();
	/* Annotating a method with @KafkaListener makes it listen to the topic , group mentioned .
	 Then it consumes messages and executes the consumeJson function*/
	 
	
     
   /* @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "qualificationKafkaListenerFactory")
    public void consumeJson(Qualification qualification) {
    	  LOG.info("received message='{}'", qualification);
    	  
    }*/
    
    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "projectKafkaListenerFactory")
    public void consumeJson(Qualification qualification) {
    	
    	//Target Node Type
    	studiedAtIndexer.setTargetNodeType("Profile");
    	
    	//Source Node Type
    	studiedAtIndexer.setSourceNodeType("Project");
    	
    	
    	studiedAtIndexer.setSourceNodeProperty(qualification.getQualification());
    	
    	targetNodeProperty.setId(qualification.getProfileId());
    	
    	studiedAtIndexer.setTargetNodeProperty(targetNodeProperty);
    	
    	
    
    	studiedAtRelationshipProperties.setQualification(qualification.getQualification());
    	studiedAtRelationshipProperties.setStream(qualification.getStream());
    	studiedAtRelationshipProperties.setYearOfCompletion(qualification.getYearOfCompletion());
    	studiedAtRelationshipProperties.setYearOfJoining(qualification.getYearOfJoining());
    	
    
    	
    	Relationship studyRelationship = new Relationship(studiedAtRelationshipProperties);
    	studiedAtIndexer.setRelationship(studyRelationship);
    	
    	// Skill Defined
    	
    	
    	
    	
    	//Operations
    	
    	studiedAtIndexer.setOperation(qualification.getMessage());
    	
    	//Printing
    	
        LOG.info("received message='{}'", studiedAtIndexer);
        
    }
}