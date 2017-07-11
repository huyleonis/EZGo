<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : tourSummary.xsl
    Created on : July 10, 2017, 7:20 PM
    Author     : hp
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="no" encoding="UTF-8" version="1.0"/>
    <xsl:template match="/">
        <tours>
            <xsl:for-each select="tour">
                <xsl:element name="id"><xsl:value-of select=".//id"/></xsl:element>
                <xsl:element name="img-link"><xsl:value-of select=".//img-link"/></xsl:element>
                <xsl:element name="name"><xsl:value-of select=".//name"/></xsl:element>
                <xsl:element name="link"><xsl:value-of select=".//link"/></xsl:element>
                <xsl:element name="departure"><xsl:value-of select=".//departure"/></xsl:element>
                <xsl:element name="current-price"><xsl:value-of select=".//current-price"/></xsl:element>
                <xsl:element name="currency"><xsl:value-of select=".//currency"/></xsl:element>
                <xsl:element name="old-price"><xsl:value-of select=".//old-price"/></xsl:element>
                <xsl:element name="agendaId"><xsl:value-of select=".//agendaId"/></xsl:element>
                <xsl:element name="agenda"><xsl:value-of select=".//agenda"/></xsl:element>                
                <xsl:element name="regionType"><xsl:value-of select=".//regionType"/></xsl:element>                
            </xsl:for-each>
        </tours>        
    </xsl:template>

</xsl:stylesheet>
