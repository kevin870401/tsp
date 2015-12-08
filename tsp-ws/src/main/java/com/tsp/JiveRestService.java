package com.tsp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.codehaus.jackson.node.ObjectNode;
import org.springframework.web.client.RestOperations;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JiveRestService {
  private String sparklrPhotoListURL;
  private String sparklrTrustedMessageURL;
  private String jivePeople="https://otpp-2.jiveon.com/api/core/v3/people/email/kevin_gu@otpp.com?fields=name";
  private String testRest1="https://otpp-2.jiveon.com/api/core/v3/announcements?count=1&fields=publishDate,content,subject";
  private RestOperations sparklrRestTemplate;
  private RestOperations trustedClientRestTemplate;

  public ObjectNode getPeople() {
    return this.sparklrRestTemplate.getForObject(URI.create(testRest1), ObjectNode.class);
    //this.sparklrRestTemplate.
}
  
  public List<String> getSparklrPhotoIds() {
      try {
          InputStream photosXML = new ByteArrayInputStream(sparklrRestTemplate.getForObject(
                  URI.create(sparklrPhotoListURL), byte[].class));

          final List<String> photoIds = new ArrayList<String>();
          SAXParserFactory parserFactory = SAXParserFactory.newInstance();
          parserFactory.setValidating(false);
          parserFactory.setXIncludeAware(false);
          parserFactory.setNamespaceAware(false);
          SAXParser parser = parserFactory.newSAXParser();
          parser.parse(photosXML, new DefaultHandler() {
              @Override
              public void startElement(String uri, String localName, String qName, Attributes attributes)
                      throws SAXException {
                  if ("photo".equals(qName)) {
                      photoIds.add(attributes.getValue("id"));
                  }
              }
          });
          return photoIds;
      } catch (IOException e) {
          throw new IllegalStateException(e);
      } catch (SAXException e) {
          throw new IllegalStateException(e);
      } catch (ParserConfigurationException e) {
          throw new IllegalStateException(e);
      }
  }

  public InputStream loadSparklrPhoto(String id) {
      return new ByteArrayInputStream(sparklrRestTemplate.getForObject(
              URI.create(String.format(jivePeople, id)), byte[].class));
  }

  public String getTrustedMessage() {
      return this.trustedClientRestTemplate.getForObject(URI.create(sparklrTrustedMessageURL), String.class);
  }
  
  public void setSparklrTrustedMessageURL(String sparklrTrustedMessageURL) {
      this.sparklrTrustedMessageURL = sparklrTrustedMessageURL;
  }

  public void setSparklrRestTemplate(RestOperations sparklrRestTemplate) {
      this.sparklrRestTemplate = sparklrRestTemplate;
  }

  public void setTrustedClientRestTemplate(RestOperations trustedClientRestTemplate) {
      this.trustedClientRestTemplate = trustedClientRestTemplate;
  }

}
