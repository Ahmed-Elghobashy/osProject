import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class main{
    private static Hashtable<String,String> hashtable=new Hashtable<String,String>();

    private static Object[] memory = new Object[1024];


    //



    //PCB , [name,value] , String
    //line of code 0-10000 , variables 1000-40000 , PCB 4000-1000000
    //["asd","Ascfsa,................   0-10000
    //a-121 m1201432 ,120421,  10000-12412421
    // p1 ,p2 ,p3             12412421-124214214124
    // ]


    public static void add(String[] line) {
        Double x;
        Double y;
        if (hashtable.get(line[1]) != null)
            x = Double.parseDouble(hashtable.get(line[1]));
        else
            x = Double.parseDouble(line[1]);
        if (hashtable.get(line[2]) != null)
            y = Double.parseDouble(hashtable.get(line[2]));
        else
            y = Double.parseDouble(line[2]);
        Double ans = x + y;

        hashtable.replace(line[1], ans + "");

    }

    public static String readfile(String[] line) {
        String s = "";
        String fileName = hashtable.get(line[1]);
        if(fileName==null){
            fileName=line[1];
        }
        String path = "src/"+fileName;
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            String row;

            while ((row = csvReader.readLine()) != null) {
                s += row;

            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return s;
    }

    public static void writefile(String[] line) {
        String fileName= hashtable.get(line[1]);
        String data = hashtable.get(line[2]);
        if(fileName==null){
            fileName=line[1];
        }
        if(data==null){
            data=line[2];
        }
        String path = "src/"+fileName;
        try {
            @SuppressWarnings("resource")

            FileWriter fw = new FileWriter(path, true);
            BufferedWriter csvWriter = new BufferedWriter(fw);

            csvWriter.write(data.toString());
            csvWriter.flush();

            csvWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("error");
            e.printStackTrace();
        }

    }
    public static void assign(String[] line) {
        String valueToBeAssigned;
        String variableToAssignTo = line[1];
        if (line[2].equals("input")) {
            valueToBeAssigned = input();
        } else if (line[2].equals("readFile")) {
            String fileToBeRead = line[3];
            String[] lineNew = new String[2];
            lineNew[0]=line[2];
            lineNew[1]=line[3];

            valueToBeAssigned = readfile(lineNew);

        } else {
            valueToBeAssigned = line[2];
            hashtable.put(variableToAssignTo, valueToBeAssigned);
        }
        hashtable.put(variableToAssignTo, valueToBeAssigned);
    }

    public static String input() {
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }

    static void print(String[] arr)
    {
        String value=hashtable.get(arr[1]);
        if(value==null)
            value=arr[1];
        System.out.println(value);
    }

    public static void interpreter(String filePath){
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath)); //change file source to run your program
            String row;
            String[] text;
            while ((row = csvReader.readLine()) != null) {
                text=row.split(" ");

                switch(text[0]){
                    case"assign":assign(text);break;
                    case"print":print(text);break;
                    case"readFile":readfile(text);break;
                    case"writeFile":writefile(text);break;
                    case"add": add(text);break;
                    default:System.out.println("invalid input");
                }

            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        for (int i = 1; i <4 ; i++) {
            interpreter("src/Program"+" "+i+".txt");
        }

    }
}
