package com.project.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class FileUtil {
    public static String multipartFileToBase64(MultipartFile file) {
        try {
            byte[] image = Base64.getEncoder().encode(file.getBytes());
            return new String(image);
        } catch (IOException e) {
            log.error("Cannot convert from File to Base64, Error: {}", e.getMessage());
            return null;
        }
    }

    public ByteArrayOutputStream base64ToOutputStream(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64.getBytes());
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        file.writeBytes(bytes);
        return file;
    }

    public ByteArrayInputStream base64ToInputStream(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64.getBytes());
        return new ByteArrayInputStream(bytes);
    }

    public static void mergePDF(List<InputStream> pdfsInputStream, OutputStream outputStream) {
        try {
            //Create document and pdfReader objects.
            Document document = new Document(PageSize.A3,10, 10, 10, 10);

            List<PdfReader> readers = new ArrayList<>();
            for (InputStream inputStream : pdfsInputStream) {
                readers.add(new PdfReader(inputStream));
            }

            // Create writer for the outputStream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            //Open document.
            document.open();

            //Contain the pdf data.
            PdfContentByte pageContentByte = writer.getDirectContent();

            PdfImportedPage pdfImportedPage;
            int currentPdfReaderPage = 1;

            // Iterate and process the reader list.
            for (PdfReader reader : readers) {
                //Create page and add content.
                while (currentPdfReaderPage <= reader.getNumberOfPages()) {
                    document.newPage();
                    pdfImportedPage = writer.getImportedPage(
                            reader,currentPdfReaderPage);
                    float offset = 0;
                    if(pdfImportedPage.getWidth() < document.getPageSize().getWidth()) {
                        offset = (document.getPageSize().getWidth() - pdfImportedPage.getWidth())/2;
                    }
                    pageContentByte.addTemplate(pdfImportedPage, offset, 0);
                    currentPdfReaderPage++;
                }
                currentPdfReaderPage = 1;
            }

            //Close document and outputStream.
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (DocumentException e) {
            log.info("Error on creating Document: {}", e.getMessage());
        } catch (IOException e) {
            log.info("Error: {}", e.getMessage());
        }
    }

    public static String splitPDF(String pathPdf, int fromPage) {
        try {
            Document document = new Document(PageSize.A3);
            PdfReader pdfReader = new PdfReader(pathPdf);
            int endPage = pdfReader.getNumberOfPages();

            // Create writer for the outputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            //Open document
            document.open();

            //Contain the pdf data.
            PdfContentByte pdfContentByte = writer.getDirectContent();
            PdfImportedPage page;

            while(fromPage <= endPage) {
                document.newPage();
                page = writer.getImportedPage(pdfReader, fromPage);
                float offset = 0;
                if(page.getWidth() < document.getPageSize().getWidth()) {
                    offset = (document.getPageSize().getWidth() - page.getWidth())/2;
                }

                pdfContentByte.addTemplate(page, offset, 0);
                fromPage++;
            }

            //Close document and outputStream.
            outputStream.flush();
            document.close();
            outputStream.close();

            byte[] fileBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (DocumentException e) {
            log.info("Error on creating Document: {}", e.getMessage());
        } catch (IOException e) {
            log.info("Error: {}", e.getMessage());
        }
        return null;
    }
}
