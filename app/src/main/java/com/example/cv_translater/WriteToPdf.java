package com.example.cv_translater;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileStore;

public class WriteToPdf {
    public static final String RESULT="";
    public WriteToPdf() {
    }

    public static final String FONT = "./src/main/res/font/freesans.ttf";

    public static PdfPCell createTextCell(String text) throws DocumentException, IOException {
        Font f ,f2;
        f = FontFactory.getFont("Times-Roman",20, Font.NORMAL);
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text,f);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        img.scaleToFit(100,100);
        PdfPCell cell = new PdfPCell(img);
        cell.setFixedHeight(150);
        cell.setVerticalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }

    private  static PdfFont trlanguage;

    public Boolean write(String filepdfadi, String filead, String filesoyad, String fileadres, String filetelefon,String fileemail
            , String fileehliyet, String filemedenidurum, String filedogumyerivetarihi,String resimyolu,String filekolej1
            , String filekolej2, String filederece1, String filederece2,String fileegitimbaslangic1
            , String fileegitimbaslangic2, String fileegitimbitis1, String fileegitimbitis2
            , String filelisans1, String filelisans2, String fileegitimbolum1, String fileegitimbolum2
            ,String filesirket1,String filebaslangic1,String filebitis1
            ,String filesirket2,String filebaslangic2,String filebitis2
            ,String filesirket3,String filebaslangic3, String filebitis3
            ,String filesirket4,String filebaslangic4, String filebitis4
            ,String filesirket5,String filebaslangic5, String filebitis5
            ,String filesirket6,String filebaslangic6, String filebitis6
            ,String filepozisyon1,String filepozisyon2,String filepozisyon3,String filepozisyon4,String filepozisyon5,String filepozisyon6
            ,String filebilgisayar,String filereferans,String filesertifika,String fileyabancidil1
            ,String fileyabancidil2,String fileseviye1,String fileseviye2,String filehobiler1,String filehobiler2)
    {
        try {


            String xx = Environment.getExternalStorageDirectory().getPath();
            String fpath = "/sdcard/" + filepdfadi + ".pdf";
            File file = new File(fpath);
            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer= PdfWriter.getInstance(document,new FileOutputStream(file.getAbsoluteFile()));
            PrintWriter pwriter;
            pwriter=new PrintWriter(System.out,true);
            // step 3
            document.open();
            // step 4
            Font f ,f2 ,f3,f4,f5;

            f4 = FontFactory.getFont("Times-Roman",14,Font.NORMAL);
            f = FontFactory.getFont("Times-Roman",19, Font.NORMAL);
            f2 = FontFactory.getFont("Times-Roman",27,Font.BOLD);
            f3 = FontFactory.getFont("Times-Roman",32,Font.BOLD);
            PdfContentByte cb=writer.getDirectContent();
            Font f1;
            f1=FontFactory.getFont("Calibri", 25,Font.BOLD);
            //ImageData imagedata2 = ImageDataFactory.create(resimyolu);

            // Creating an Image object
            if(resimyolu != null){
                Image image = Image.getInstance(resimyolu);
                image.scaleToFit(320,240);
                //document.add(image);

                Paragraph resimliparagraf = new Paragraph();

                Chunk adsoyad = new Chunk(filead+" "+filesoyad + "\n" + "\n",f3);
                Chunk adres = new Chunk(fileadres +"\n"+ "\n",f4);
                Chunk telefon = new Chunk(filetelefon +"\n",f4);
                Chunk mail = new Chunk(fileemail +"\n"+ "\n",f4);
                resimliparagraf.add(adsoyad);
                resimliparagraf.add(adres);
                resimliparagraf.add(telefon);
                resimliparagraf.add(mail);
                Chunk resim = new Chunk(image,+340,-75);
                resimliparagraf.add(resim);
                document.add(resimliparagraf);

            }

            if(resimyolu == null){
                Paragraph adsoyad=new Paragraph(filead+" "+filesoyad,f3);
                document.add(adsoyad);

                Paragraph adres=new Paragraph(fileadres,f);
                document.add(adres);

                Paragraph telefon=new Paragraph(filetelefon,f);
                document.add(telefon);

                Paragraph mail=new Paragraph(fileemail,f);
                document.add(mail);

                document.add(Chunk.NEWLINE);
            }





            Paragraph kisiselbilgiler = new Paragraph("Persönliche Angaben\n",f2);
            document.add(kisiselbilgiler);

            Paragraph dogumyerivetarihi=new Paragraph(filedogumyerivetarihi,f);
            document.add(dogumyerivetarihi);

            Paragraph medenidurum=new Paragraph(filemedenidurum,f);
            document.add(medenidurum);

            Paragraph ehliyet=new Paragraph("FahrerLaubnis "+fileehliyet,f);
            document.add(ehliyet);

            document.add(Chunk.NEWLINE);

            Paragraph edu=new Paragraph("Bildung",f2);
            document.add(edu);

            Paragraph okul1=new Paragraph(fileegitimbaslangic1+" "+fileegitimbitis1+" "+filekolej1+" "+filelisans1+" "+fileegitimbolum1+" "+filederece1,f);
            document.add(okul1);

            Paragraph okul2=new Paragraph(fileegitimbaslangic2+" "+fileegitimbitis2+" "+filekolej2+" "+filelisans2+" "+fileegitimbolum2+" "+filederece2,f);
            document.add(okul2);

            document.add(Chunk.NEWLINE);

            Paragraph tra=new Paragraph("Arbeitserfahrung",f2);
            document.add(tra);

            Paragraph isbolumu=new Paragraph(filebaslangic1+" "+filebitis1+" "+filesirket1+" "+filepozisyon1,f);
            document.add(isbolumu);

            Paragraph isbolumu2=new Paragraph(filebaslangic2+" "+filebitis2+" "+filesirket2+" "+filepozisyon2,f);
            document.add(isbolumu2);

            Paragraph isbolumu3=new Paragraph(filebaslangic3+" "+filebitis3+" "+filesirket3+" "+filepozisyon3,f);
            document.add(isbolumu3);

            if(!filebaslangic4.equals("") && !filebitis4.equals("") && !filesirket4.equals("") && !filepozisyon4.equals("")){
                Paragraph isbolumu4=new Paragraph(filebaslangic4+" "+filebitis4+" "+filesirket4+" "+filepozisyon4,f);
                document.add(isbolumu4);
            }

            if(!filebaslangic5.equals("") && !filebitis5.equals("") && !filesirket5.equals("") && !filepozisyon5.equals("")){
                Paragraph isbolumu5=new Paragraph(filebaslangic5+" "+filebitis5+" "+filesirket5+" "+filepozisyon5,f);
                document.add(isbolumu5);
            }

            if(!filebaslangic6.equals("") && !filebitis6.equals("") && !filesirket6.equals("") && !filepozisyon6.equals("")){
                Paragraph isbolumu6=new Paragraph(filebaslangic6+" "+filebitis6+" "+filesirket6+" "+filepozisyon6,f);
                document.add(isbolumu6);
            }

            document.add(Chunk.NEWLINE);

            Paragraph dilbilgisi=new Paragraph("Fremdsprache",f2);
            document.add(dilbilgisi);

            Paragraph dilbilgisi1 = new Paragraph(fileyabancidil1+" "+fileseviye1+"\n"+fileyabancidil2+" "+fileseviye2,f);
            document.add(dilbilgisi1);

            document.add(Chunk.NEWLINE);

            Paragraph pcbilgisi = new Paragraph("Computerfähigkeiten",f2);
            document.add(pcbilgisi);
            Paragraph pcbilgisi2 = new Paragraph(filebilgisayar,f);
            document.add(pcbilgisi2);

            document.add(Chunk.NEWLINE);

            Paragraph cert=new Paragraph("Zertifizierungen",f2);
            document.add(cert);
            document.add(new Paragraph(filesertifika,f));

            document.add(Chunk.NEWLINE);

            Paragraph referanslar = new Paragraph("Verweise",f2);
            document.add(referanslar);
            Paragraph referanslar2 = new Paragraph(filereferans,f);
            document.add(referanslar2);
            document.add(Chunk.NEWLINE);

            Paragraph hobiler = new Paragraph("Hobbys",f2);
            document.add(hobiler);
            Paragraph hobi1 = new Paragraph(filehobiler1,f);
            document.add(hobi1);
            Paragraph hobi2 = new Paragraph(filehobiler2,f);
            document.add(hobi2);

            // step 5
            document.close();

            Log.d("Success", "Success");
            return true;
        } catch (IOException e) {
            Log.i("d",e.getMessage());
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            Log.i("e",e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
