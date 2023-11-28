package com.project.utils;

import com.itextpdf.text.pdf.BarcodeQRCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
public class QRCodeUtil {

    public static String QRGenerate(String content) {
        try {
            BarcodeQRCode qrCode = new BarcodeQRCode(content, 240, 240, null);
            Image image = qrCode.createAwtImage(Color.BLACK, Color.WHITE);

            BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getWidth(null), BufferedImage.TYPE_INT_RGB);
            buffImg.getGraphics().drawImage(image, 0, 0, null);
            buffImg.getGraphics().dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "jpeg", baos);

            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            log.info("Error on generate QRcode, content: " + content);
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
