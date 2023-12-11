import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class RowInfo {
    String varType;
    String varName;
    int varCount;
    int min;
    int max;
    Object rowValue;
//    String varSize;
//    boolean signed;

    public RowInfo() {
    }

    public RowInfo(String varType, String varName, int varCount, int min, int max) {
        this.varType = varType;
        this.varName = varName;
        this.varCount = varCount;
        this.min = min;
        this.max = max;
        rowValue = null;
    }

//    public RowInfo(String varType, String varName, int varCount, int min, int max, String varSize, boolean signed) {
//        this.varType = varType;
//        this.varName = varName;
//        this.varCount = varCount;
//        this.min = min;
//        this.max = max;
//        rowValue = null;
//        this.varSize = varSize;
//        this.signed = signed;
//    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getVarCount() {
        return varCount;
    }

    public String getVarName() {
        return varName;
    }

    public String getVarType() {
        return varType;
    }

    public Object getRowValue() {
        return rowValue;
    }
}

public class StructMetaData {
    String name;
    List<RowInfo> rows;

    public String getName() {
        return name;
    }

    public List<RowInfo> getRows() {
        return rows;
    }

    public StructMetaData() {
    }

    public StructMetaData(String name, List<RowInfo> rows) {
        this.name = name;
        this.rows = rows;
    }

    public void print() {
        System.out.println();
        System.out.println(name);
        System.out.println("{");
        for (RowInfo row : rows) {
            System.out.println(row.varType + " " + row.varName + " " + " /* min: " + row.min + "; max: " + row.max + " */");
            System.out.println(row.rowValue.toString()); // was printValue instead of toString
        }
        System.out.println("}");
    }

    public void inputValuesFromConsole() {
        Scanner in = new Scanner(System.in);

        for (RowInfo row : rows) {
            if (row.varCount == 1) {
                System.out.println("Give me some input between " + row.min + " and " + row.max);
                int num = in.nextInt();

                while (!(row.min <= num && num <= row.max)) {
                    System.out.println("Invalid input");
                    num = in.nextInt();
                }

                row.rowValue = new Int(num);
            }
            else if (row.varCount > 1) {
                int[] arr = new int[row.varCount];
                for (int i = 0; i < row.varCount; ++i) {
                    System.out.println("Give me some input between " + row.min + " and " + row.max);
                    int num = in.nextInt();

                    while (!(row.min <= num && num <= row.max)) {
                        System.out.println("Invalid input");
                        num = in.nextInt();
                    }
                    arr[i] = num;
                }
                row.rowValue = new IntArr(arr);
            }
        }
    }

    public static void toXML(String filename, String output, int index) throws IOException {
        Parser p = new Parser();
        StructMetaData smdtest = p.extractMetaData("header.h").get(index);

        smdtest.inputValuesFromConsole();
        smdtest.print();

        ObjectMapper parser = new XmlMapper();
        parser.enable(SerializationFeature.INDENT_OUTPUT);

        String xml = parser.writeValueAsString(smdtest);
        parser.writeValue(new File(output + ".xml"), smdtest);
        System.out.println();
        System.out.println(xml);
    }

    public static StructMetaData fromXML(String filename) throws IOException {
        ObjectMapper parser = new XmlMapper();
        parser.enable(SerializationFeature.INDENT_OUTPUT);
        StructMetaData fromFile = parser.readValue(new File("out.xml"), new TypeReference<StructMetaData>() {});
        fromFile.print();
        return fromFile;
    }
}
