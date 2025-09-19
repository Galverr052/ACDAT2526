package iesmm.ad.t1;
import java.io.File;

public class ListaUnidades {
    public static void main(String[] args) {

        File unidades[] = File.listRoots();

        for (int x = 0; x < unidades.length; x++) {
            System.out.println(unidades[x]);
        }

        System.out.println();

        for (String fileDdirectory : unidades[0].list()) {
            File file = new File(unidades[0].toString() + fileDdirectory);
            if (file.isFile())
                System.out.println(" |- " + fileDdirectory);
            else
                System.out.println(" |+ " + fileDdirectory);
        }
    }
}