package org.wingsico.bookstore.primarykey;


import lombok.Data;

import java.io.Serializable;

@Data
public class CartOrder implements Serializable {
    private Integer cartID;
    private Integer orderID;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((cartID == null) ? 0 : cartID.hashCode());
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

        final CartOrder other = (CartOrder) obj;
        if(cartID == null){
            if(other.cartID != null){
                return false;
            }
        }else if(!cartID.equals(other.cartID)){
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
