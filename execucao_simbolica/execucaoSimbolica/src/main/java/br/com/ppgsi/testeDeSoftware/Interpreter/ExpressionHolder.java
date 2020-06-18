package br.com.ppgsi.testeDeSoftware.Interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author orlando
 */
public class ExpressionHolder {
    private List<Object> state = new ArrayList<>();
    
    public void add(Object obj ){
        this.state.add(obj);
    }
    
    public void remove(Object obj){
        this.state.remove(obj);
    }
    
    public Object retrieveAt( int index ){
        return this.state.get(index);
    }
    
    public boolean isEmpty(){
        return this.state.isEmpty();
    }
    
    public Iterator<Object> iterator(){
        return this.state.iterator();
    }
    
    public boolean isReadyForFinish(){
        return this.state.size() >= 3;
    }
    
    public int size(){
        return this.state.size();
    }
    
    public List<Object> getAll(){
        List<Object> stateCopy = new ArrayList<>();
        Collections.copy(this.state, stateCopy);
        return stateCopy;
    }
    
    public void clear(){
        this.state.clear();
    }
    
    //public <T>T getTyped( Class<T> clazz, )
}
