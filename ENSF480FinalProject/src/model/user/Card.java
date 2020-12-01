package model.user;

import java.sql.Date;

public class Card {
    private String nameOnCard;
    private String cardNumber;
    private String secNum;
    private Date expiryDate;
    
    public Card(String name, String num, String cvs, Date expiryDate) {
        nameOnCard = name;
        cardNumber = num;
        secNum = cvs;
        this.expiryDate = expiryDate;
    }
    
    public String getNameOnCard() {
    	return this.nameOnCard;
    }
    
    public String getCardNumber() {
    	return this.cardNumber;
    }
    
    public String getSecNum() {
    	return this.secNum;
    }
    
    public Date getExpiryDate() {
    	return this.expiryDate;
    }
    
    @Override
    public String toString() {
        return nameOnCard + " " + cardNumber + " " + secNum;
    }
}
