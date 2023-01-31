import java.util.Objects;

/**
 *
 * @author Dinesh Adhikari
 *
 */

public class Product {
    private String name;
    private String description;
    private String ID;
    private Double cost;

    static private int IDSeed = 1;

    public static void setIDSeed(int IDSeed) {Product.IDSeed = IDSeed;}

    public static int getIDSeed(){return IDSeed;}

    public Product(String ID, String name, String description, Double cost)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Product(String name, String description, Double cost)
    {
        this.ID = this.genID();
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String genID()
    {
        String newID = "" + IDSeed;
        while (newID.length() < 8)
        {
            newID = "0" + newID;
        }
        IDSeed++;
        return newID;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ID='" + ID + '\'' +
                ", cost=" + cost +
                '}';
    }

    /**
     * @return Returns the object data in CSV format
     */
    public String toCSVRecord()
    {
        return ID + ", " + name + ", " + description + ", " + cost;
    }

    /**
     * @return Returns the object data in XML format
     */
    public String toXMLRecord()
    {
        String retString = "";

        retString = "<IDNum>" + this.ID + "</IDNum>";
        retString += "<Name>" + this.name + "</Name>";
        retString += "<Description>" + this.description + "</Description>";
        retString += "<Cost>" + this.cost + "</Cost>";

        return retString;
    }

    /**
     * @return Returns the object data in JSON format
     */
    public String toJSONRecord()
    {
        String retString = "";
        char DQ = '\u0022';  // Assign the double quote char to a variable
        retString =  "{" + DQ + "IDNum" + DQ + ":" + DQ + this.ID + DQ + ",";
        retString += DQ + "Name" + DQ + ":" + DQ + this.name + DQ + ",";
        retString += " " + DQ + "Description"  + DQ + ":" + DQ + this.description + DQ + ",";
        retString += " " + DQ + "Cost" + DQ + ":" + DQ + this.cost +DQ + "}";

        return retString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(ID, product.ID) && Objects.equals(cost, product.cost);
    }


}
