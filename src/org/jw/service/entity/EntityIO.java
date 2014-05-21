/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

import java.beans.IntrospectionException;
import javax.persistence.Column;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson
 */
public class EntityIO<T> {
    private List<Field> fields;
    private Class entityClass;
    
    public static <T> EntityIO<T> create(T source, T destination, Class entityClass) {
        return new EntityIO<>(source, destination, entityClass);
    }
    public T source;
    public T destination;
    
    private EntityIO(T source, T destination, Class entityClass){
        this.source = source;
        this.destination = destination;
        this.entityClass = entityClass;
        this.fields = getAnnotatedFields(source);
        read();
    }
    
    private List<Field> getAnnotatedFields(T entity){
        List<Field> annotatedFields = new ArrayList<>();
        Field[] allFields = entity.getClass().getDeclaredFields();        
        for(int i = 0; i < allFields.length; i++){
            Field field = allFields[i];
            if(field.isAnnotationPresent(Column.class)){
                annotatedFields.add(field);                
            }    
        }
        
        return annotatedFields;
    }
    
    
    public void read(){
        for(Field field : fields){
            try {
                if(field.getName().trim().equalsIgnoreCase("id"))continue;
                Object destinationValue = new PropertyDescriptor(field.getName(), entityClass).getReadMethod().invoke(destination);
                Object invoke = (new PropertyDescriptor(field.getName(), entityClass)).getWriteMethod().invoke(source, destinationValue);
            } catch (    IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(EntityIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void write(){
        for(Field field : fields){
            if(field.getName().trim().equalsIgnoreCase("id"))continue;
            try {
                Object sourceValue = new PropertyDescriptor(field.getName(), entityClass).getReadMethod().invoke(source);
                Object invoke = (new PropertyDescriptor(field.getName(), entityClass)).getWriteMethod().invoke(destination, sourceValue);
            } catch (    IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(EntityIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public T getDestinationEntity(){ return this.destination; }
}
