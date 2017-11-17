package fr.amanzegouarene.common.basic.manipulations;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * Created by amanzego on 07/09/2017.
 */
public class DocManipWithNio2 {

    private static final String DESTINATION_MERGED_FILE = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // A tout hasard: C:\Personal_Unsaved\work\MergeLocation
        System.out.println("Veuillez saisir le chemin complet vers le dossier contenant les fichiers");
        String docsLocation = sc.nextLine();
        // Lancement de la fusion.
        String cheminDocObtenu = null;
        try {
            cheminDocObtenu = mergePdfDocs(docsLocation);
            // splitPdfToPages(docsLocation);
        } catch (IOException e) {
            System.out.println("Erreur lors de la récup et merge des docs: "+e.getMessage());
        }
        System.out.println("Documents fusionnés: "+cheminDocObtenu);
    }

    private static void splitPdfToPages(String docsLocation) {
        
    }

    private static String mergePdfDocs(String docsLocation) throws IOException {
        // Lecture du dossier contenant les fichiers avec java.io.File
        Path inDocs = Paths.get(docsLocation);
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        String outFile = docsLocation+"\\merged.pdf";
        try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(inDocs)){
            // PDF conversion ...

            // PDF Merge
            StreamSupport.stream(fileStream.spliterator(), false)
                    .filter(s -> !s.endsWith(".pdf"))
                    .forEach(path -> mergeDocs(path.toString(), pdfMergerUtility));
        }catch (Exception e) {
            System.out.println("Erreur !"+ e.getMessage());
        }
        System.out.println("Set destination merged file :");
        pdfMergerUtility.setDestinationFileName(outFile);
        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

       return outFile;
    }

    private static void convertToPdf(Path path) {
        String newFile="";
        int lastIndex = path.toString().lastIndexOf(".");
        newFile = path.toString().substring(0,lastIndex);
        Path newPath = Paths.get(newFile+".pdf");
        // ... create new pdf according to newPath and convert into it
    }

    private static void mergeDocs(String s, PDFMergerUtility pdfMergerUtility) {
        try {
            if(s!=null && s.endsWith(".pdf"))
                System.out.println(String.format("File %s added to merge list",s));
                pdfMergerUtility.addSource(s);

        } catch (FileNotFoundException e) {
            System.out.println("Erreur lors du merge du fichier: "+ s);
            System.out.println("Trace: "+e.getMessage());
        }
    }
}
