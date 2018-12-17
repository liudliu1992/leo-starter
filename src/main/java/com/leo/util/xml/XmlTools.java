package com.leo.util.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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

    public static <T> T readXmlDataToBean(String xmlData, Class<T> clazz) {
        try {
            //加载映射bean类
            jaxbContext = JAXBContext.newInstance(clazz);
            //创建解析
            Unmarshaller um = jaxbContext.createUnmarshaller();
            StreamSource streamSource = new StreamSource(new StringReader(xmlData));
            T root = (T)um.unmarshal(streamSource);
            return root;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
