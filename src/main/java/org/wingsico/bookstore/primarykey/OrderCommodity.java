package org.wingsico.bookstore.primarykey;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderCommodity implements Serializable{

    private Integer commodityID;
    private Integer orderID;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((commodityID == null) ? 0 : commodityID.hashCode());
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
        if(commodityID == null){
            if(other.commodityID != null){
                return false;
            }
        }else if(!commodityID.equals(other.commodityID)){
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
