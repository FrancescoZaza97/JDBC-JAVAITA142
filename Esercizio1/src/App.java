import bean.Quotidiani;
import dao.QuotidianiDAO;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner Text = new Scanner(System.in);
        Scanner Number = new Scanner(System.in);
        Scanner Number2 = new Scanner(System.in);
        String nome;
        int valore;
        double marameo;
        QuotidianiDAO mioQuotidiano = new QuotidianiDAO();
        

        System.out.println("inserisci il nome del quotidiano: ");
        nome = Text.nextLine();
        System.out.println("inserisci il prezzo del quotidiano: ");
        marameo = Number.nextDouble();
        System.out.println("inserisci il valore dell'agio del quotidiano: ");
        valore = Number2.nextInt();
        Quotidiani puppa = new Quotidiani(nome,marameo,valore);
        mioQuotidiano.insertQuotidiani(puppa);
        
    }
}
