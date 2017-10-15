package Model.Classes;

public class Item {

    //Start of Attributes

    private int Id;
    private String Name;
    private String AttributeName;
    private int AttributeValue;

    //End of Attributes
    //Start of Getters And Setters

    public  int GetId(){
      return this.Id;
    }
    public  String GetName(){
        return this.Name;
    }
    public  String GetAttributeName(){
        return this.AttributeName;
    }
    public  int GetAttributeValue(){
        return this.AttributeValue;
    }
    public void SetId(int Id){
        this.Id= Id;
    }
    public void SetName(String Name){
        this.Name= Name;
    }
    public void SetAttributeName(String AttributeName){
        this.AttributeName= AttributeName;
    }
    public void SetAttributeValue( int AttributeValue){
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
