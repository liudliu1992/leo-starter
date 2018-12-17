package com.leo.util.xml;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * xml工具类
 *
 * @author liusl
 * @version 1.0.0
 * @date 2018/12/17-14:44
 */
public class XmlTools {
    private static JAXBContext jaxbContext;

    /**
     * 通过xml文件地址将xml文件解析为bean
     *
     * @param xmlPath 文件地址
     * @param clazz   类型
     * @return T
     */
    public static <T> T readXmlToBean(String xmlPath, Class<T> clazz) {
        StringBuffer buffer = null;
        try {
            //读入xml文件流
            InputStream is = new FileInputStream(new File(xmlPath));
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            return readXmlDataToBean(buffer.toString(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过xml字符串将xml解析为bean
     *
     * @param xmlData xml数据
     * @param clazz   类型
     * @return T
     */
    public static <T> T readXmlDataToBean(String xmlData, Class<T> clazz) {
        try {
            //加载映射bean类
            jaxbContext = JAXBContext.newInstance(clazz);
            //创建解析
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xmlData));
            return (T) um.unmarshal(streamSource);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseBeanToXml(Object data) {
        try {
            StringWriter sw = new StringWriter();
            jaxbContext = JAXBContext.newInstance(data.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            if (marshaller == null) {
                return StringUtils.EMPTY;
            }
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(data, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new RuntimeException("obj toXML failed", e);
        }

    }
}
