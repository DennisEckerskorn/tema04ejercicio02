package com.denniseckerskorn.tema04ejercicio02.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomParser {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private NodeList nodeList;
    private Document document;

    public DomParser() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    /**
     * Carga un archivo XML desde los recursos raw de la aplicación y lo prepara para ser procesado.
     *
     * @param context    El contexto actual de la aplicación.
     * @param resourceID El ID del recurso raw que contiene el archivo XML.
     * @throws IOException  Si hay un error de entrada/salida al leer el archivo.
     * @throws SAXException Si ocurre un error durante el proceso de parseo XML.
     */
    public void loadFile(Context context, int resourceID) throws IOException, SAXException {
        InputStream inputStream = context.getResources().openRawResource(resourceID);
        document = builder.parse(inputStream);
        document.getDocumentElement().normalize();
    }

    /**
     * Devuelve una lista de objetos Country obtenidos de los nodos XML con el nombre de etiqueta especificado.
     *
     * @param tagName Nombre de la etiqueta de los elementos que contienen información de los países.
     * @param context El contexto actual de la aplicación para acceder a los recursos.
     * @return Una lista de objetos Country extraídos del archivo XML.
     */
    public List<Country> getCountries(String tagName, Context context) {
        List<Country> countries = new ArrayList<>();
        nodeList = document.getElementsByTagName(tagName);

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String countryCode = element.getAttribute("countryCode");
                    String countryName = element.getAttribute("countryName");
                    long population = Long.parseLong(element.getAttribute("population"));
                    String capital = element.getAttribute("capital");
                    String isoAlpha3 = element.getAttribute("isoAlpha3");
                    int flagResource = getFlagResourceByIsoAlpha3(countryCode, context);

                    Country country = new Country(countryCode, countryName, population, capital, isoAlpha3, flagResource);
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    /**
     * Busca y devuelve el recurso drawable correspondiente a la bandera del país según el código ISO Alpha-3.
     *
     * @param countryCode El código del país (ISO Alpha-3).
     * @param context     El contexto actual de la aplicación.
     * @return El identificador del recurso drawable que contiene la bandera del país.
     */
    @SuppressLint("DiscouragedApi")
    private int getFlagResourceByIsoAlpha3(String countryCode, Context context) {
        String packageName = context.getPackageName();
        String resourceName = "_" + countryCode.toLowerCase();
        Log.d("CountryAdapter", "Generated resource name: " + resourceName);
        return context.getResources().getIdentifier(resourceName, "drawable", packageName);
    }

    /**
     * Obtiene una lista de nodos de un archivo XML que coinciden con el nombre de la etiqueta especificada.
     *
     * @param tagName El nombre de la etiqueta que se desea buscar en el documento XML.
     * @return Un NodeList que contiene todos los nodos que coinciden con la etiqueta, o null si el documento no existe.
     */
    public NodeList getNodesByTag(String tagName) {
        if (document != null) {
            nodeList = document.getElementsByTagName(tagName);
            return nodeList;
        }
        return null;
    }

    /**
     * Obtiene el contenido de texto de todos los elementos con el nombre de etiqueta especificado.
     *
     * @param tagName El nombre de la etiqueta cuyos contenidos se quieren obtener.
     * @return Una lista de cadenas de texto que contienen el contenido de los elementos encontrados.
     */
    public List<String> getElementContent(String tagName) {
        List<String> contents = new ArrayList<>();
        nodeList = getNodesByTag(tagName);

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    contents.add(element.getTextContent().trim());
                }
            }
        }
        return contents;
    }
}
