package com.leo.util.qr;


//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.NotFoundException;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author shaolin.liu
 * @description
 * @date 17:23 2018/8/1
 * @modified By:
 */
public class QRCodeKit {
    /**默认编码*/
    private static final String QR_CODE_DEFAULT_CHARSET = "UTF-8";

    private static final int QR_CODE_DEFAULT_HEIGHT = 420;

    private static final int QR_CODE_DEFAULT_WIDTH = 420;

    private static final int BLACK = 0xFFffffff;
    private static final int WHITE = 0xFF000000;


//    public static void main(String[] args) throws IOException, NotFoundException{
//        String data = "hahahahhahah";
//
//        File logoFile = new File(QRCodeKit.class.getClassLoader().getResource("images/icon.png").getFile());
////        BufferedImage image = QRCodeKit.createQRCodeWithLogo(data, logoFile);
//        BufferedImage image = QRCodeKit.createQRCode(data);
//        ImageIO.write(image, "png", new File("C:/data/michael_zxing_logo.png"));
//        System.out.println("done");
//    }
//
//    /**
//     * Create qrcode with default settings
//     * @author zhangwenchao
//     * @param data
//     * @return
//     */
//    public static BufferedImage createQRCode(String data) {
//        return createQRCode(data, QR_CODE_DEFAULT_WIDTH, QR_CODE_DEFAULT_HEIGHT);
//    }
//
//    /**
//     * Create qrcode with default charset
//     * @author zhangwenchao
//     * @param data
//     * @param width
//     * @param height
//     * @return
//     */
//    public static BufferedImage createQRCode(String data, int width, int height) {
//        return createQRCode(data, QR_CODE_DEFAULT_CHARSET, width, height);
//    }
//
//    /**
//     * Create qrcode with specified charset
//     * @author zhangwenchao
//     * @param data
//     * @param charset
//     * @param width
//     * @param height
//     * @return
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static BufferedImage createQRCode(String data, String charset, int width, int height) {
//        Hashtable hint = new Hashtable();
//        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//        hint.put(EncodeHintType.CHARACTER_SET, charset);
//
//        return createQRCode(data, charset, hint, width, height);
//    }
//
//    /**
//     * Create qrcode with specified hint
//     * @author zhangwenchao
//     * @param data 二维码包含的数据
//     * @param charset 编码格式
//     * @param hint hint
//     * @param width 二维码宽度
//     * @param height 二维码高度
//     * @return BufferedImage
//     */
//    private static BufferedImage createQRCode(String data, String charset, Hashtable<EncodeHintType, ?> hint, int width,
//                                             int height) {
//        BitMatrix matrix;
//        try {
//            matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE,
//                    width, height, hint);
//            return toBufferedImage(matrix);
//        } catch (WriterException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
//    private static BufferedImage toBufferedImage(BitMatrix matrix) {
//        int width = matrix.getWidth();
//        int height = matrix.getHeight();
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
//            }
//        }
//        return image;
//    }
//    /**
//     * Create qrcode with default settings and logo
//     * @author zhangwenchao
//     * @param data
//     * @param logoFile
//     * @return
//     */
//    public static BufferedImage createQRCodeWithLogo(String data, File logoFile) {
//        return createQRCodeWithLogo(data, QR_CODE_DEFAULT_WIDTH, QR_CODE_DEFAULT_HEIGHT, logoFile);
//    }
//    /**
//     * Create qrcode with default charset and logo
//     * @author zhangwenchao
//     * @param data
//     * @param width
//     * @param height
//     * @param logoFile
//     * @return
//     */
//    public static BufferedImage createQRCodeWithLogo(String data, int width, int height, File logoFile) {
//        return createQRCodeWithLogo(data, QR_CODE_DEFAULT_CHARSET, width, height, logoFile);
//    }
//    /**
//     * Create qrcode with specified charset and logo
//     * @author zhangwenchao
//     * @param data
//     * @param charset
//     * @param width
//     * @param height
//     * @param logoFile
//     * @return
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static BufferedImage createQRCodeWithLogo(String data, String charset, int width, int height, File logoFile) {
//        Hashtable hint = new Hashtable();
//        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//        hint.put(EncodeHintType.CHARACTER_SET, charset);
//        return createQRCodeWithLogo(data, charset, hint, width, height, logoFile);
//    }
//    /**
//     * Create qrcode with specified hint and logo
//     * @author zhangwenchao
//     * @param data
//     * @param charset
//     * @param hint
//     * @param width
//     * @param height
//     * @param logoFile
//     * @return
//     */
//    public static BufferedImage createQRCodeWithLogo(String data, String charset, Hashtable<EncodeHintType, ?> hint,
//                                                     int width, int height, File logoFile) {
//        try {
//            BufferedImage qrcode = createQRCode(data, charset, hint, width, height);
//            BufferedImage logo = ImageIO.read(logoFile);
//            int deltaHeight = height/5;
//            int deltaWidth = width/5;
//
//            BufferedImage combined = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = (Graphics2D) combined.getGraphics();
//            g.drawImage(qrcode, 0, 0, null);
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//            g.drawImage(logo, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
//
//            return combined;
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * Return base64 for images
//     * @author zhangwenchao
//     * @param image
//     * @return
//     */
//    public static String getImageBase64String(BufferedImage image) {
//        String result = null;
//        try {
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            OutputStream b64 = new Base64OutputStream(os);
//            ImageIO.write(image, "png", b64);
//            result = os.toString("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//        return result;
//    }
//
//    /**
//     * Decode the base64Image data to images
//     * @author zhangwenchao
//     * @param base64ImageString
//     * @param file
//     */
//    public static void convertBase64StringToImage(String base64ImageString, File file) {
//        FileOutputStream os;
//        try {
//            Base64 d = new Base64();
//            byte[] bs = d.decode(base64ImageString);
//            os = new FileOutputStream(file.getAbsolutePath());
//            os.write(bs);
//            os.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
}
