package com.cfm.algorithm;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.util.logging.Logger;

/**
 * 后台功能权限接口
 *
 * @author cfm (fangming_chen@sui.com)
 * @version 1.0
 * @since 2021/3/25
 */
public class PdfConvertHtmlUtil {


    /**
     * PDF文档流转Png
     * @param pdfFileInputStream
     * @return BufferedImage
     */
    public static BufferedImage pdfStreamToPng(InputStream pdfFileInputStream){
        PDDocument doc = null;
        PDFRenderer renderer = null;
        StringBuilder base_64= new StringBuilder();
        try {
            doc = PDDocument.load(pdfFileInputStream);
            renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            BufferedImage image = null;
            BufferedImage image1 = null;
            BufferedImage image2 = null;
            int page = pageCount/4;
            for (int i = 0; i < pageCount; i++) {
                System.out.println(i);
                if (image != null) {
                    image = combineBufferedImages(image, renderer.renderImageWithDPI(i, 144,ImageType.RGB));
                    bufferedImageToBase64(image);
                }

                if (i == 0) {
                    image = renderer.renderImageWithDPI(i, 144,ImageType.RGB); // Windows native DPI
                }
                /*BufferedImage  image = renderer.renderImageWithDPI(i, 144, ImageType.BINARY); // Windows native DPI
                String base64 = bufferedImageToBase64(image);
                base_64.append(base64);*/
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图

            }
            return combineBufferedImages(image);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(doc != null){doc.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *BufferedImage拼接处理，添加分割线
     * @param images
     * @return BufferedImage
     */
    public static BufferedImage combineBufferedImages(BufferedImage... images) {
        int height = 0;
        int width = 0;
        for (BufferedImage image : images) {
            //height += Math.max(height, image.getHeight());
            height += image.getHeight();
            width = image.getWidth();
        }
        BufferedImage combo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = combo.createGraphics();
        int x = 0;
        int y = 0;
        for (BufferedImage image : images) {
            //int y = (height - image.getHeight()) / 2;
            g2.setStroke(new BasicStroke(2.0f));// 线条粗细
            g2.setColor(new Color(193, 193, 193));// 线条颜色
            g2.drawLine(x, y, width, y);// 线条起点及终点位置

            g2.drawImage(image, x, y, null);
            //x += image.getWidth();
            y += image.getHeight();

        }
        return combo;
    }
    /**
     * 通过Base64创建HTML文件并输出html文件
     * @param base64
     * @param htmlPath html保存路径
     */
    public static void createHtmlByBase64(String base64,String htmlPath) {
        StringBuilder stringHtml = new StringBuilder();
        PrintStream printStream = null;
        try {
            // 打开文件
            printStream = new PrintStream(new FileOutputStream(htmlPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 输入HTML文件内容
        stringHtml.append("<html><head>");
        stringHtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        stringHtml.append("<title></title>");
        stringHtml.append("</head>");
        stringHtml.append(
                "<body style=\"\r\n" + "    text-align: center;\r\n" + "    background-color: #C1C1C1;\r\n" + "\">");
        stringHtml.append("<img src=\"data:image/png;base64," + base64 + "\" />");
        stringHtml.append("<a name=\"head\" style=\"position:absolute;top:0px;\"></a>");
        //添加锚点用于返回首页
        stringHtml.append("<a style=\"position:fixed;bottom:10px;right:10px\" href=\"#head\">回到首页</a>");
        stringHtml.append("</body></html>");
        try {
            // 将HTML文件内容写入文件中
            printStream.println(stringHtml.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(printStream != null){printStream.close();}
        }

    }

    /**
     * bufferedImage 转为 base64编码
     * @param bufferedImage
     * @return
     */
    public static String bufferedImageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String png_base64 = "";
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);// 写入流中
            byte[] bytes = byteArrayOutputStream.toByteArray();// 转换成字节
            BASE64Encoder encoder = new BASE64Encoder();
            // 转换成base64串 删除 \r\n
            png_base64 = encoder.encodeBuffer(bytes).trim()
                    .replaceAll("\n", "")
                    .replaceAll("\r", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return png_base64;
    }

    public static void main(String[] args) {
        File file = new File("D:\\pdf\\2_《Apache RocketMQ源码解析》226.pdf");
            String htmlPath = "D:\\pdf\\aaaa.html";
        InputStream inputStream = null;
        BufferedImage bufferedImage = null;
        try {
            inputStream = new FileInputStream(file);
            bufferedImage = pdfStreamToPng(inputStream);
            String base64_png = bufferedImageToBase64(bufferedImage);
            //BASE64Encoder encoder = new BASE64Encoder();
            // 转换成base64串 删除 \r\n
            //byte[] bytes = toByteArray(inputStream);
            /*base64_png = encoder.encodeBuffer(bytes).trim()
                    .replaceAll("\n", "")
                    .replaceAll("\r", "");*/
            createHtmlByBase64(base64_png,htmlPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null){inputStream.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024*4];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
