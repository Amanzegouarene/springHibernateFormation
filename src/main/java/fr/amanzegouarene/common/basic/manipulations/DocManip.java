package fr.amanzegouarene.common.basic.manipulations;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by amanzego on 07/09/2017.
 */
public class DocManip {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le chemin complet vers le dossier contenant les fichiers");
        String docsLocation = sc.nextLine();
        // Lancement de la fusion.
        String cheminDocObtenu = null;
        try {
            cheminDocObtenu = mergePdfDocs(docsLocation);
        } catch (IOException e) {
            System.out.println("Erreur lors de la récup et merge des docs: "+e.getMessage());
        }
        System.out.println("Documents fusionnés: "+cheminDocObtenu);
    }

    private static String mergePdfDocs(String docsLocation) throws IOException {
        // Lecture du dossier contenant les fichiers avec java.io.File
        File dossier = new File(docsLocation);
        String outFile = docsLocation+"\\merged.pdf";
        if(!dossier.isDirectory()){
            System.out.println("Ce n'est pas un dossier: "+docsLocation);
        }else{
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
            for (File file : dossier.listFiles()) {
                if(file.getName().endsWith(".pdf")){
                    pdfMergerUtility.addSource(file);
                }
            }
            pdfMergerUtility.setDestinationFileName(outFile);
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        }
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        return outFile;
    }
}
