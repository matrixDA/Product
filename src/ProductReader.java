import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Product> productOData = new ArrayList<>();

        char DQ = '\u0022';

        final int FIELDS_LENGTH = 4;

        String id, name, description;
        double cost = 0;

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    line++;
                }
                reader.close();

                String[] fields;
                for(String l:lines)
                {
                    fields = l.split(",");
                    if(fields.length == FIELDS_LENGTH)
                    {

                        id = fields[0].trim();
                        name = fields[1].trim();
                        description = fields[2].trim();
                        cost = Double.parseDouble(fields[3].trim());

                        productOData.add(new Product(id, name, description, cost));
                    }
                    else
                    {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println();
                    }
                }
            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }

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
        }  // end of TRY

        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

