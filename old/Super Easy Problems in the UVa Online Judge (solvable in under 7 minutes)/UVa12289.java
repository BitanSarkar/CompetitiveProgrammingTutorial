import java.util.Scanner;

public class UVa12289 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        while(t-->0){
            String n = sc.nextLine().trim().toLowerCase();
            if(n.length()==5) System.out.println("3");
            else if(n.length()==3) {
                int scoreOne = (n.contains("o")?1:0) + (n.contains("n")?1:0) + (n.contains("e")?1:0);
                int scoreTwo = (n.contains("t")?1:0) + (n.contains("w")?1:0) + (n.contains("o")?1:0);
                System.out.println(scoreOne>scoreTwo?"1":"2");
            }
        };
        sc.close();
    }
}
