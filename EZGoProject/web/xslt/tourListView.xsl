<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : tourListView.xsl
    Created on : July 11, 2017, 8:04 PM
    Author     : Dells
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:exsl="http://exslt.org/common"
                xmlns:fn="http://www.w3.org/2005/xpath-functions">
    
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:template match="tours">
        <div class="table-wrapper">
            <div class="table" id="table">
                <div class="row header">
                    <div class="cell">
                        ID
                    </div>
                    <div class="cell">
                        Tên
                    </div>
                    <div class="cell">
                        Giá
                    </div>
                    <div class="cell">
                        Xuất phát
                    </div>
                    <div class="cell">
                        Công ty
                    </div>
                </div>
                <xsl:for-each select="tour[position()]">          
                    <xsl:variable name="link">process?action=TourDetail&amp;id=<xsl:value-of select=".//id"/></xsl:variable>
                    <div class="row">
                        <div class="cell">
                            <a href="$link">
                                <xsl:value-of select=".//id"/>
                            </a>
                        </div>
                        <div class="cell">
                            <xsl:value-of select=".//name"/>
                        </div>
                        <div class="cell">
                            <xsl:value-of select=".//current-price"/>
                        </div>
                        <div class="cell">
                            <xsl:value-of select=".//departure"/>
                        </div>
                        <div class="cell">
                            <xsl:value-of select=".//agendaID"/>
                        </div>
                    </div>
            
                </xsl:for-each>
       
            </div> <!--End table div-->
        </div> <!--End table wrapper div-->
    
    </xsl:template>
</xsl:stylesheet>
