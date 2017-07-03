<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : tourCard.xsl
    Created on : June 23, 2017, 8:14 PM
    Author     : hp
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:decimal-format name="vnd" decimal-separator="," grouping-separator="."/>
    <xsl:template match="tours">        
        <div class="container tour-view">
            
            <xsl:for-each select="tour[position()&lt;16]">
                <xsl:variable name="link">process?action=TourDetail&amp;id=<xsl:value-of select=".//id"/></xsl:variable>
                <div class="tour-card">
                    <img src="{.//img-link}" alt="Thái Lan" width="100%"/>
                    <div class="tour-card-content">
                        <a href="{$link}">
                            <h2>
                                <xsl:value-of select=".//name"/>
                            </h2>
                        </a>
                        <span class="departure">
                            Khởi hành từ <xsl:value-of select=".//departure"/>
                        </span>
                        <hr/>
                        <div class="tour-card-price">
                            Giá 1 khách:  
                            <strong>
                                <span class="amount">
                                    <xsl:value-of select="format-number(.//current-price, '###.###', 'vnd')"/>
                                </span>
                                <span class="curr">
                                    <xsl:value-of select=".//currency"/>
                                </span>
                                <br/>
                                <span class="old-amount">
                                    <xsl:value-of select="format-number(.//old-price,'###.###', 'vnd')"/>
                                </span>
                            </strong>
                        </div>
                        <hr/>
                        <div class="tour-card-descripiton">
                            <p>
                                Cung cấp bởi:                             
                                <a href="index.jsp?p=agenda&amp;id={agendaId}" class="agency">
                                    <xsl:value-of select=".//agenda"/>
                                </a>
                            </p>	
                        </div>						                        
                        <a href="{$link}">
                            <button class="btn btn-red">
                                Xem chi tiết
                            </button>
                        </a>
                    
                    </div>
                </div>
            </xsl:for-each>
        </div>
    </xsl:template>

</xsl:stylesheet>
