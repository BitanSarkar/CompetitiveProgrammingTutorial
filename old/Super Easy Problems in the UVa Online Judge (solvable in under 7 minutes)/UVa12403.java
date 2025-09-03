import java.util.Scanner;

public class UVa12403 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        int ctr = 0;
        while(sc.hasNextLine()){
            line = sc.nextLine().trim().toLowerCase();
            if(line.contains("report")) System.out.println(ctr);
            if(line.contains("donate")) ctr+=Integer.parseInt(line.split("\\s+")[1]);
        }
        sc.close();
    }
}
