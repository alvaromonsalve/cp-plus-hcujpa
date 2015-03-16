/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;


/**
 *
 * @author Alvaro Monsalve
 */
public class convertores {
    
//    public static void getImagePDF(String nombre, String fileName
//            , String tDocumento) throws FileNotFoundException
//            ,DocumentException, BadElementException, MalformedURLException, IOException{
//        Document doc = new  Document();
//        FileOutputStream ficheroPdfStream = new FileOutputStream(fileName+".pdf");
//        PdfWriter.getInstance(doc, ficheroPdfStream).setInitialLeading(20);
//        doc.open();
//        doc.add(new Paragraph(tDocumento,FontFactory.getFont("Calibri", 22,Font.BOLDITALIC, BaseColor.BLUE)));
//        Image imagen = Image.getInstance(fileName);
//        imagen.scaleToFit(500,800);
//        imagen.setAlignment(Chunk.ALIGN_MIDDLE);
//        doc.add(imagen);
//        doc.close();
//    }
    
    public static String getTextToHtml(String texto){
        return "<html>\n" +
            "<div style=\"width:300;\">"+texto+"</div>\n" +
            "\n" +
            "</html>";
    }
}
