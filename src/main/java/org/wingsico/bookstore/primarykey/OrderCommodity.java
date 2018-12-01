package org.wingsico.bookstore.primarykey;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderCommodity implements Serializable{

    private Integer bookID;
    private Integer orderID;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((bookID == null) ? 0 : bookID.hashCode());
        result = PRIME * result + ((orderID == null) ? 0 : orderID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final OrderCommodity other = (OrderCommodity) obj;
        if(bookID == null){
            if(other.bookID != null){
                return false;
            }
        }else if(!bookID.equals(other.bookID)){
            return false;
        }
        if(orderID == null){
            if(other.orderID != null){
                return false;
            }
        }else if(!orderID.equals(other.orderID)){
            return false;
        }

        return true;
    }
}
