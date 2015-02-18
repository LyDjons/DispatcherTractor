package com.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by disp.chimc on 22.10.14.
 */


public class XmlConverter {

    public  static ArrayList<Transport> unmarshalling(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(MTSTransport.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MTSTransport mtsTransport = (MTSTransport) jaxbUnmarshaller.unmarshal( new File(path) );
        return  mtsTransport.getTransport();
    }

    public static void marshalling(ArrayList<Transport> transports,String path) throws JAXBException {
        MTSTransport v = new MTSTransport();
        v.setTransport(transports);

        JAXBContext jc = JAXBContext.newInstance(MTSTransport.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      //  StringWriter sw = new StringWriter();
       // m.marshal(v, sw);
        m.marshal(v, new File(path));
    }


}

@XmlRootElement(name = "mtsTransport")
@XmlAccessorType (XmlAccessType.FIELD)
class MTSTransport {
    @XmlElement(name = "transport")
    private ArrayList<Transport> transports;

    public ArrayList<Transport> getTransport() {
        return transports;
    }

    public void setTransport(ArrayList<Transport> transport) {
        this.transports = transport;
    }
}

