/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namesurferdp;

/**
 *
 * @author student
 */
public class QueueDP <E>{
    private int numitems = 0;
    private int MAX_ITEMS = 10;
    private E data[] = (E[]) new Object[MAX_ITEMS];
    private int qfront = 0;
    private int qrear = 0;
    
    public void enQueue(E item) throws ListFullException{
        if(!isFull()){
            data[qrear] = item;
            numitems++;
            qrear=(qrear+1)%MAX_ITEMS;
        }
        else
            throw new ListFullException("ERROR! Queue full");
    }
    
    public E deQueue() throws ListEmptyException{
        if(!isEmpty()){
            E temp = data[qfront];
            numitems--;
            qfront = (qfront+1)%MAX_ITEMS;
            return temp;
        }
        else
            throw new ListEmptyException("ERROR! Queue empty");
    }
    
    public E peek() throws ListEmptyException{
        if(!isEmpty()){
            return data[0];
        }
        else
            throw new ListEmptyException("ERROR! Queue empty");
    }
    
    public boolean isEmpty(){
        return (numitems==0);
    }
    
    public boolean isFull(){
        return (numitems==MAX_ITEMS);
    }
    public int size(){
        return numitems;
    }
    
}
