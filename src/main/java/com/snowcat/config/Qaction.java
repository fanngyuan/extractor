//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.07 at 04:59:22 PM CST 
//


package com.snowcat.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}qsource"/>
 *         &lt;element ref="{}target"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "qsource",
    "target"
})
@XmlRootElement(name = "qaction")
public class Qaction {

    @XmlElement(required = true)
    protected Qsource qsource;
    @XmlElement(required = true)
    protected Target target;

    /**
     * Gets the value of the qsource property.
     * 
     * @return
     *     possible object is
     *     {@link com.snowcat.config.Qsource }
     *     
     */
    public Qsource getQsource() {
        return qsource;
    }

    /**
     * Sets the value of the qsource property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.snowcat.config.Qsource }
     *     
     */
    public void setQsource(Qsource value) {
        this.qsource = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link com.snowcat.config.Target }
     *     
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.snowcat.config.Target }
     *     
     */
    public void setTarget(Target value) {
        this.target = value;
    }

}
