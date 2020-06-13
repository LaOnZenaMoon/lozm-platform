package lozm.util;

import lombok.extern.slf4j.Slf4j;
import lozm.object.vo.image.ImageVo;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
public class ImageUtils {

    final static private int WIDTH = 140;
    final static private int HEIGHT = 140;

    static public void createImageFile(ImageVo imageVo) throws IOException {
        OutputStream stream = null;
        BufferedOutputStream bos =null;

        try {
            File imageFile = new File(imageVo.getImageFilePath());
            byte[] imageByte = DatatypeConverter.parseBase64Binary(imageVo.getImageData());
            stream = new FileOutputStream(imageFile);
            bos = new BufferedOutputStream(stream);
            bos.write(imageByte);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if ( bos != null ) {
                bos.close();
            }
            if ( stream != null ) {
                stream.close();
            }
        }
    }

    static public String setResizeImageFile(ImageVo imageVo) throws IOException {
        BufferedImage imageOrigin = ImageIO.read(imageVo.getImageFile());
        BufferedImage imageResize = setResizeImageFile(imageOrigin);

        File imageResizeFile = new File(imageVo.getImageFilePath());

        ImageIO.write(imageResize, imageVo.getImageFileType(), imageResizeFile);

        return imageVo.getImageName();
    }

    static private BufferedImage setResizeImageFile(BufferedImage imageOrigin) {
        Image imageTemp = imageOrigin.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        BufferedImage imageResize = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = imageResize.createGraphics();
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        graphics.setComposite(alcom);
        graphics.drawImage(imageTemp, 0, 0, null);
        graphics.dispose();

        return imageResize;
    }

    static public void setRotateImageFile(ImageVo imageVo) throws IOException {
        BufferedImage imageOrigin = ImageIO.read(imageVo.getImageFile());
        int rotation = imageVo.getImageRotation();

        switch (rotation) {
            case 1:
                imageOrigin = Scalr.rotate(imageOrigin, Scalr.Rotation.CW_90);
                break;
            case 2:
                imageOrigin = Scalr.rotate(imageOrigin, Scalr.Rotation.CW_180);
                break;
            case 3:
                imageOrigin = Scalr.rotate(imageOrigin, Scalr.Rotation.CW_270);
                break;
        }

        ImageIO.write(imageOrigin, imageVo.getImageFileType(), imageVo.getImageFile());
    }

}
