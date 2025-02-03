import java.util.Arrays;
import java.util.Scanner;

//*istite klasi*//

public class Main {

    public static String spoj(char[] niza){
        Arrays.sort(niza);
        String spoen = "";
        for (int i = 0; i < niza.length; i++) {
            spoen+=niza[i];
        }

        return spoen;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        CBHT<String, Integer> anagrami = new CBHT<>(2 * n);

        for (int i = 0; i < n; i++) {
            String zbor = input.next();

            char[] zz=zbor.toCharArray();
            String sortiran=spoj(zz);
            //za razlicna golemina na bukvi samo + 
            // String lowerCase = sortiran.toLowerCase();

            if (anagrami.search(sortiran) != null) {
                anagrami.search(sortiran).element.value++;
            } else {
                anagrami.insert(sortiran, 1);
            }
        }

        String str = input.next();
        char[] baranZbor = str.toCharArray();
        str=spoj(baranZbor);

        if(anagrami.search(str)!=null){
            System.out.println(anagrami.search(str).element.value);
        }else{
            System.out.println("null");
        }

    }
}


