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
import org.jw.service.dao.DataAccessObject;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class EntityIO<T> {
    private final List<Field> fields;
    private final Class entityClass;
    
    public static <T> EntityIO<T> create(T source, T target, Class<T>entityClass) {
        return new EntityIO<>(source, target, entityClass);
    }
    
    public static <T> EntityIO<T> create(Class<T> entityClass) {
        return new EntityIO<>(entityClass);
    }
    
    private T source;
    private T target;
    
    private EntityIO(T source, T target, Class<T> entityClass){
        this.source = source;
        this.target = target;
        this.entityClass = entityClass;        
        this.fields = getAnnotatedFields(instantiateEntity(entityClass));        
        read();
    }
    
    private EntityIO(Class<T> entityClass){
        this.entityClass = entityClass;
        this.fields = getAnnotatedFields(instantiateEntity(entityClass));
        this.source = instantiateEntity(entityClass);
        this.target = instantiateEntity(entityClass);
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
                Object targetValue = new PropertyDescriptor(field.getName(), entityClass).getReadMethod().invoke(target);
                Object invoke = (new PropertyDescriptor(field.getName(), entityClass)).getWriteMethod().invoke(source, targetValue);
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
                Object invoke = (new PropertyDescriptor(field.getName(), entityClass)).getWriteMethod().invoke(target, sourceValue);
            } catch (    IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(EntityIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private T instantiateEntity(Class<T> classEntity){
        try {
            return classEntity.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    /**
     * @return the source
     */
    public T getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(T source) {
        this.source = source;
    }

    /**
     * @return the target
     */
    public T getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(T target) {
        this.target = target;
    }
}
