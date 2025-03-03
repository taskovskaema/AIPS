public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        CBHT<String, Integer> pozitivni=new CBHT<>(2*n);
        CBHT<String, Integer> negativni=new CBHT<>(2*n);
        // ne mi treba klasa Person oti ne mi treba da mi vrati podatoci tuku broj na kolku pacienti se vkupno

        for (int i = 0; i < n; i++) {
            String opstina=input.next();
            String prezime=input.next();
            String sostojba=input.next();

            if(sostojba.equals("positive")){
                if(pozitivni.search(opstina)!=null){
                    pozitivni.search(opstina).element.value++;
                }else{
                    pozitivni.insert(opstina,1);
                }
            }else if(sostojba.equals("negative")){
                if(negativni.search(opstina)!=null){
                    negativni.search(opstina).element.value++;
                }else{
                    negativni.insert(opstina,1);
                }
            }
        }

        String baranaOpshtina=input.next();

        float poz= pozitivni.search(baranaOpshtina).element.value;
        float neg= negativni.search(baranaOpshtina).element.value;

        float rizikFaktor=poz/(neg+poz);

        System.out.println(rizikFaktor);

    }
}
