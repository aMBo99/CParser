import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> extractRelevantLines(String filename) {
        List<String> res = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!(line.isEmpty() || line.charAt(0) == '#')) {
                    res.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<StructMetaData> extractMetaData(String filename) {
        List<StructMetaData> res = new ArrayList<>();

        boolean inside = false;
        String name = "";
        List<RowInfo> rows = new ArrayList<>();

        List<String> relevantLines = extractRelevantLines(filename);
        for (String line : relevantLines) {
            if (line.startsWith("struct")) {
                name = line.split("\\s+")[1];
            }

            else if (line.startsWith("{"))
                inside = true;

            else if (line.startsWith("}")) {
                inside = false;
                res.add(new StructMetaData(name, rows));
                name = "";
                rows = new ArrayList<>();
            }

            else if (inside) {
                line = line.replaceAll(",", "");
                line = line.replaceAll(";", "");
                List<String> split = List.of(line.trim().split("\\s+"));

                String varName = split.get(1);
                String size = varName.replaceAll("[^0-9]", "") + " bits";
                String varType = split.get(0);
                int min = Integer.parseInt(split.get(4));
                int max = Integer.parseInt(split.get(6));

                RowInfo currRow = new RowInfo(split.get(0), split.get(1), getVarCount(split.get(1)), Integer.parseInt(split.get(4)), Integer.parseInt(split.get(6)));

                //RowInfo row = new RowInfo(varType, varName, getVarCount(varName), min, max, size, getIsSigned(varType));

                rows.add(currRow);
            }
        }
        return res;
    }

    int getVarCount(String varName) {
        boolean insideSquareBrackets = false;
        int i = 0;
        String numstr = "";

        while (i < varName.length()) {
            if (varName.charAt(i) == '[')
                insideSquareBrackets = true;
            else if (varName.charAt(i) == ']')
                insideSquareBrackets = false;
            else if (insideSquareBrackets) {
                char ch = varName.charAt(i);
                numstr += ('0' <= ch && ch <= '9') ? ch : "";
            }
            ++i;
        }
        return (numstr.isEmpty()) ? 1 : Integer.parseInt(numstr);
    }

    boolean getIsSigned(String varType) {
        return !varType.startsWith("u");

    }

}
