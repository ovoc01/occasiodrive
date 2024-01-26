package com.cloud.ventevoiture.model.services.file;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.springframework.stereotype.Service;

@Service
public class FileUploadService {


   public byte[] compressBase64Image(String base64Image) throws UnsupportedEncodingException, IOException{
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Deflater compresser = new Deflater(Deflater.BEST_COMPRESSION,true);
      DeflaterOutputStream dos = new DeflaterOutputStream(baos,compresser);
      dos.write(base64Image.getBytes("UTF-8"));
      dos.close();
      return baos.toByteArray();
   }


   public String decompressBase64Image(byte[] compressedImage) throws UnsupportedEncodingException, IOException{
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      Deflater decompresser = new Deflater(Deflater.BEST_COMPRESSION,true);
      DeflaterOutputStream dos = new DeflaterOutputStream(baos,decompresser);
      dos.write(compressedImage);
      dos.close();
      return baos.toString("UTF-8");
   }
}
