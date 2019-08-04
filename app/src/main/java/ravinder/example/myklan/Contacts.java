package ravinder.example.myklan;

public class Contacts {
    private String contactName;
    private String contactNumber;




    public Contacts(String name, String number)
    {
        contactName=name;
        contactNumber=number;

    }

    public String getName()
    {
        return contactName;
    }
    public String getNumber()
    {
        return contactNumber;
    }


}
