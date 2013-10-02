/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;



/**
 *
 * @author Alvaro Monsalve
 */
public class convertores {
    
    public static void getImagePDF(String nombre, String fileName
            , String tDocumento) throws FileNotFoundException
            ,DocumentException, BadElementException, MalformedURLException, IOException{
        Document doc = new  Document();
        FileOutputStream ficheroPdfStream = new FileOutputStream(fileName+".pdf");
        PdfWriter.getInstance(doc, ficheroPdfStream).setInitialLeading(20);
        doc.open();
        doc.add(new Paragraph(tDocumento,FontFactory.getFont("Calibri", 22,Font.BOLDITALIC, BaseColor.BLUE)));
        Image imagen = Image.getInstance(fileName);
        imagen.scaleToFit(500,800);
        imagen.setAlignment(Chunk.ALIGN_MIDDLE);
        doc.add(imagen);
        doc.close();
    }
    
    public static String getTextToHtml(String texto){
        return "<html>\n" +
            "<div style=\"width:300;\">"+texto+"</div>\n" +
            "\n" +
            "</html>";
    }
}
