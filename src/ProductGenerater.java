import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductGenerater
{
    public static void main(String[] args)
    {
        ArrayList<Product> productOData = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        String ID = "";
        String name = "";
        String description = "";

        double cost = 0;
        char DQ = '\u0022';



        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID of the product");
            name = SafeInput.getNonZeroLenString(in, "Enter the name of the product");
            description = SafeInput.getNonZeroLenString(in, "Enter the description of the product");
            cost = SafeInput.getDouble(in, "Enter the price of the product");

            productOData.add(new Product(ID, name, description, cost));

            done = SafeInput.getYNConfirm(in, "Are you done entering Product data?");


        } while (!done);
        System.out.println();

        for (Product p: productOData) {
            System.out.println(p.toCSVRecord());
        }

        System.out.println();
        System.out.println("<Product>");
        for (Product p: productOData) {
            System.out.println(p.toXMLRecord());
        }
        System.out.println("</Product>");
        System.out.println();

        System.out.println("{" + DQ + "Product" + DQ + ":[");
        for (Product p: productOData) {
            System.out.println(p.toJSONRecord());
        }
        System.out.println("]}");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(Product rec : productOData)
            {
                writer.write(rec.toCSVRecord());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
