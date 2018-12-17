package com.leo.util.qr;

//import com.google.zxing.*;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author shaolin.liu
 * @description
 * @date 17:53 2018/8/1
 * @modified By:
 */
public class ZxingCode {
    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
//    /**
//     * 生成QRCode二维码
//     */
//    public String encode(ZxingEntity zxing){
//        try {
//            Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
//            /*设置纠错级别(L 7%~M 15%~Q 25%~H 30%),纠错级别越高存储的信息越少*/
//            hints.put(EncodeHintType.ERROR_CORRECTION, zxing.getErrorCorrectionLevel());
//            /*设置编码格式*/
//            hints.put(EncodeHintType.CHARACTER_SET, zxing.getCharacterSet());
//            /*设置边缘空白*/
//            hints.put(EncodeHintType.MARGIN, zxing.getMargin());
//
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(zxing.getContents(),BarcodeFormat.QR_CODE,zxing.getWidth(),zxing.getHeight(),hints);
//
//            File isFile = new File(zxing.getPath());
//            if(!isFile.exists()){
//                isFile.mkdirs();
//            }
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            String fileName = sdf.format(new Date()) + "." + zxing.getFormat();
//            String url = zxing.getPath() + fileName;
//            writeToFile(bitMatrix,zxing.getFormat(),new File(url),zxing.isFlag(),zxing.getLogoPath());
//            return url;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    /**
//     * 生成二维码图片
//     * @param bitMatrix
//     * @param format 图片格式
//     * @param file	生成二维码图片位置
//     * @param isLogo 是否要加logo图
//     * @param logoPath logo图片地址
//     * @throws IOException
//     */
//    public static void writeToFile(BitMatrix bitMatrix,String format,File file,boolean isLogo,String logoPath) throws IOException {
//        BufferedImage bi = toBufferedImageContents(bitMatrix);
//        if(isLogo){
//            int width_4 = bitMatrix.getWidth() / 5;
//            int width_8 = width_4;
//            int height_4 = bitMatrix.getHeight() / 5;
//            int height_8 = width_4 ;
//            /*返回由指定矩形区域定义的子图像*/
//            BufferedImage bi2 = bi.getSubimage(width_4 + width_8, height_4 + height_8, width_4, height_4);
//            /*获取一个绘图工具笔*/
//            Graphics2D g2 = bi2.createGraphics();
//            /*读取logo图片信息*/
//            //实例化一个Image对象。
//            Image img = ImageIO.read(new File(logoPath));
//            /*当前图片的宽与高*/
//            int currentImgWidth = img.getWidth(null);
//            int currentImgHeight = img.getHeight(null);
//            /*处理图片的宽与高*/
//            int resultImgWidth = 0;
//            int resultImgHeight = 0;
//            if(currentImgWidth != width_4){
//                resultImgWidth = width_4;
//            }
//            if(currentImgHeight != width_4){
//                resultImgHeight = width_4;
//            }
//            /*绘制图片*/
//            g2.drawImage(img,0,0, resultImgWidth,resultImgHeight,null);
//            g2.dispose();
//            bi.flush();
//        }
//        insertInfoBack(bi,format,file);
//    }
//
//    private static void insertInfoBack(BufferedImage bi,String format,File file){
//        InputStream imagein = null;
//        ImageOutputStream imOut = null;
//        try {
//            imagein = new FileInputStream(QRCodeKit.class.getClassLoader().getResource("images/qr_back.jpg").getPath());
//            BufferedImage image = ImageIO.read(imagein);
//            Graphics g = image.getGraphics();
//            // 生成的二维码设置的较小，这里等比放大了二维码。也可在zxing中设置二维码生成的大小
////            BufferedImage squreImage = resizeImage(image2, 2);
//            g.drawImage(bi, 330, 600, bi.getWidth(), bi.getHeight(), null);
//            ByteArrayOutputStream bs = new ByteArrayOutputStream();
//            imOut = ImageIO.createImageOutputStream(bs);
//            ImageIO.write(image, format, file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                imagein.close();
//                imOut.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 生成二维码内容
//     * @param bitMatrix
//     * @return
//     */
//    public static BufferedImage toBufferedImageContents(BitMatrix bitMatrix){
//        int width = bitMatrix.getWidth();
//        int height = bitMatrix.getHeight();
//        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//        for(int x=0;x<width;x++){
//            for(int y=0;y<height;y++){
//                image.setRGB(x, y, bitMatrix.get(x, y) == true ? BLACK : WHITE);
//            }
//        }
//        return image;
//    }
//    public static String getQrImg() {
//        ZxingEntity zxing = new ZxingEntity();
//        zxing.setContents("http://www.baidu.com这是百度的地址");
//        zxing.setCharacterSet("UTF-8");
//        zxing.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
//        zxing.setFlag(true);
//        zxing.setFormat("png");
//        zxing.setMargin(0);
//        zxing.setWidth(420);
//        zxing.setHeight(420);
//        zxing.setPath("c:/data/");
//        zxing.setLogoPath(QRCodeKit.class.getClassLoader().getResource("images/icon.png").getPath());
//        ZxingCode code = new ZxingCode();
//		return code.encode(zxing);
//    }
//    public static void main(String[] args){
//        ZxingEntity zxing = new ZxingEntity();
//        zxing.setContents("kldksjkldsjafkljdg这是百度的地址");
//        zxing.setCharacterSet("UTF-8");
//        zxing.setErrorCorrectionLevel(ErrorCorrectionLevel.H);
//        zxing.setFlag(true);
//        zxing.setFormat("png");
//        zxing.setMargin(0);
//        zxing.setWidth(420);
//        zxing.setHeight(420);
//        zxing.setPath("c:/data/");
//        zxing.setLogoPath(QRCodeKit.class.getClassLoader().getResource("images/icon.png").getPath());
//        ZxingCode code = new ZxingCode();
//        code.encode(zxing);
//    }
}
