import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Helpers {


    public static Scanner scannerToFile(String nomefile) throws FileNotFoundException {
        File f = new File(nomefile);
        Scanner scan = new Scanner(f);

        return scan;

    }

    public static ArrayList<String> leggiRighe(String nomeFile) throws FileNotFoundException {
        Scanner scan = scannerToFile(nomeFile);
        ArrayList<String> righe = new ArrayList<String>();

        while(scan.hasNextLine()){
            String riga = scan.nextLine();
            righe.add(riga);
        }

        scan.close();//Chiudere il file sorgente di input dello scanner

        return righe;

    }



}
