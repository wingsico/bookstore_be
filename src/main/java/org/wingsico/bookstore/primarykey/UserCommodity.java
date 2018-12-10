package org.wingsico.bookstore.primarykey;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCommodity implements Serializable{

    private Integer bookID;
    private Integer userID;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((bookID == null) ? 0 : bookID.hashCode());
        result = PRIME * result + ((userID == null) ? 0 : userID.hashCode());
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

        final UserCommodity other = (UserCommodity) obj;
        if(bookID == null){
            if(other.bookID != null){
                return false;
            }
        }else if(!bookID.equals(other.bookID)){
            return false;
        }
        if(userID == null){
            if(other.userID != null){
                return false;
            }
        }else if(!userID.equals(other.userID)){
            return false;
        }

        return true;
    }
}
