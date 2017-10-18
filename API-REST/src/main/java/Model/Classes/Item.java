package Model.Classes;

public class Item {

    //Start of Attributes

     int Id;
     String Name;
     String AttributeName;
     int AttributeValue;

    //End of Attributes
    //Start of Getters And Setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAttributeName() {
        return AttributeName;
    }

    public void setAttributeName(String attributeName) {
        AttributeName = attributeName;
    }

    public int getAttributeValue() {
        return AttributeValue;
    }

    public void setAttributeValue(int attributeValue) {
        AttributeValue = attributeValue;
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
