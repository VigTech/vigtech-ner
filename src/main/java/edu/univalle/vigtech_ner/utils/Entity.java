package edu.univalle.vigtech_ner.utils;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entity {
	
	private String entity;
    private String entityClass;
    
    public Entity(){
    	entity="";
    	entityClass="";
    }
    
    public Entity(String entity, String entityClass){
    	this.entity=entity;
    	this.entityClass=entityClass;
    }
    
    
	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	/**
	 * @return the entityClass
	 */
	public String getEntityClass() {
		return entityClass;
	}
	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	
	

}
