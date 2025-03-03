class Lekovi{
    public String ime;
    public String sostojba;
    public int cena;
    public int brParcinja;

    public Lekovi(String ime, String sostojba, int cena, int brParcinja) {
        this.ime = ime;
        this.sostojba = sostojba;
        this.cena = cena;
        this.brParcinja = brParcinja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lekovi lekovi = (Lekovi) o;
        return sostojba == lekovi.sostojba && cena == lekovi.cena && brParcinja == lekovi.brParcinja && Objects.equals(ime, lekovi.ime);
    }

    @Override
    public int hashCode() {
        int hash =(100*(100*(100*0+ime.charAt(2))+ime.charAt(1))+ime.charAt(0))%656565;
        return hash;
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n= input.nextInt();
        
        CBHT<String,Lekovi> informaci=new CBHT<>(2*n);

        for (int i = 0; i < n; i++) {
            String ime= input.next().toUpperCase();
            int sostojba= input.nextInt();
            int cena= input.nextInt();
            int brParcinja= input.nextInt();
            
           String sost;

            if(sostojba==1){
               sost="POS";
               Lekovi lekovi=new Lekovi(ime,sost,cena,brParcinja);
                informaci.insert(ime,lekovi);

            }else if(sostojba==0){
                sost="NEG";
                Lekovi lekovi=new Lekovi(ime,sost,cena,brParcinja);
                informaci.insert(ime,lekovi);
                
            }
        }

        String baranLek;
        int parcinja;

        while(true){
            baranLek=input.next().toUpperCase();

            if(baranLek.equals("END")){
                break;
            }

            parcinja=input.nextInt();

            if(informaci.search(baranLek)==null){
                System.out.println("No such drug");
            }else{
                System.out.println(informaci.search(baranLek).element.value.ime+" "+informaci.search(baranLek).element.value.sostojba+" "+informaci.search(baranLek).element.value.cena+" "+informaci.search(baranLek).element.value.brParcinja);
                if(informaci.search(baranLek).element.value.brParcinja>parcinja){
                    informaci.search(baranLek).element.value.brParcinja-=parcinja;
                    System.out.println("Order made");
                }else{
                    System.out.println("No drugs available");
                }
            }
        }
    }
}
