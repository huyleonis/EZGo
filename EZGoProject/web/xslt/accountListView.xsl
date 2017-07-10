<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : accountListView.xsl
    Created on : July 10, 2017, 9:40 AM
    Author     : Dells
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:exsl="http://exslt.org/common"
                xmlns:fn="http://www.w3.org/2005/xpath-functions">
    <xsl:output method="html"/>

    <xsl:template match="accounts"/>
    <xsl:for-each select="account[position()&lt;16]">
        <form action="process" method="POST">
            <xsl:variable name="link">process?action=DeleteAccount&amp;accountID=<xsl:value-of select=".//accountID"/></xsl:variable>
            <div class="row">
                <div class="cell">
                    <xsl:value-of select=".//username"/>
                </div>
                <div class="cell">
                    <xsl:value-of select=".//email"/>
                </div>
                <div class="cell">
                    <xsl:value-of select=".//fullname"/>
                </div>
                <div class="cell">
                    <xsl:value-of select=".//roleID"/>
                </div>
                <div class="cell">
                    <a href="{$link}">
                    <button type="button" class="btn btn-orange" 
                            name="action" value="DeleteAccount">Xóa</button>
                    </a>
                </div>
            </div>
        </form>
    </xsl:for-each>
    

</xsl:stylesheet>