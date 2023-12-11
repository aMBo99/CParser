import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
//        Parser p = new Parser();
//
//        //List<StructMetaData> xo = p.extractMetaData("header.h");
//        //StructMetaData smd = xo.get(1);
//        //for (StructMetaData smd : xo)
//        //    smd.print();
//
//        //List<RowValue> asd = new ArrayList<>();
//
//        //asd.add(new Int(5));
//        //asd.add(new IntArr(new int[]{1,2,3}));
//
//        //StructMetaData smdtest = p.extractMetaData("header.h").get(1);
//        //smdtest.rows.get(0).rowValue = new Int(4);
//        //smdtest.rows.get(1).rowValue = new IntArr(new int[]{1, 2, 3});
//
//        //smdtest.inputValuesFromConsole();
//        //smdtest.print();
//
//        ObjectMapper parser = new XmlMapper();
//        parser.enable(SerializationFeature.INDENT_OUTPUT);
//
//        //String xml = parser.writeValueAsString(smdtest);
//        //parser.writeValue(new File("out.xml"), smdtest);
//
//        StructMetaData fromFile = parser.readValue(new File("out.xml"), new TypeReference<StructMetaData>() {});
//        //fromFile.print();
//
//        StructMetaData fromFile2 = parser.convertValue(fromFile, StructMetaData.class);
//
//        fromFile2.print();
//
//        Int xml4ec = (Int)fromFile2.rows.get(0).rowValue;
//        //System.out.println(xml4ec.value);
//
//        //System.out.println(xml);
//        StructMetaData.toXML("header.h", "out", 1);
        StructMetaData.fromXML("out.xml");
    }
}