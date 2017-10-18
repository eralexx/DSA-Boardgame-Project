package Model.Classes;

public class Item {

    //Start of Attributes

     int Id;
     String Name;
     String AttributeName;
     int AttributeValue;

    //End of Attributes
    //Start of Getters And Setters

    public  int getId(){
      return this.Id;
    }
    public  String getName(){
        return this.Name;
    }
    public  String getAttributeName(){
        return this.AttributeName;
    }
    public  int getAttributeValue(){
        return this.AttributeValue;
    }
    public void setId(int Id){
        this.Id= Id;
    }
    public void setName(String Name){
        this.Name= Name;
    }
    public void setAttributeName(String AttributeName){
        this.AttributeName= AttributeName;
    }
    public void setAttributeValue( int AttributeValue){
        this.AttributeValue= AttributeValue;
    }

    //End of Getters And Setters
    //Start of Constructors

    public Item(int Id, String Name, String AttributeName, int AttributeValue){
        this.Id=Id;
        this.Name=Name;
        this.AttributeName=AttributeName;
        this.AttributeValue=AttributeValue;
    }

    //End of Constructors

}
