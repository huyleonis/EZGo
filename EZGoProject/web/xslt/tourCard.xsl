<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : tourCard.xsl
    Created on : June 23, 2017, 8:14 PM
    Author     : hp
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:exsl="http://exslt.org/common"
                xmlns:fn="http://www.w3.org/2005/xpath-functions">
    <xsl:output method="html" indent="yes"/>
    <xsl:decimal-format name="vnd" decimal-separator="," grouping-separator="."/>    
    <xsl:template match="tours">        
        <xsl:param name="accId"/>
        <xsl:param name="favList"/>
        
        <div class="container tour-view">
            
            <xsl:for-each select="tour[position()&lt;16]">
                <xsl:variable name="link">process?action=TourDetail&amp;id=<xsl:value-of select=".//id"/></xsl:variable>
                <div class="tour-card">
                    <img src="{.//img-link}" title="{.//name}" width="100%"/>
                    <xsl:if test="$accId != ''">
                        <xsl:if test="$favList != ''">                            
                            <xsl:variable name="tourId" select=".//id"/>                            
                            <br/>
                            <xsl:if test="contains($favList, $tourId)">
                                <img src="img/star1.png" width="80" height="80" 
                                    class="favorite" title="Thêm vào danh sách yêu thích" style="opacity:1;"
                                        onclick="toggleFavorite('{.//id}', this)" status="On"/>
                            </xsl:if>
                            <xsl:if test="not(contains($favList, $tourId))">
                                <img src="img/star.png" width="80" height="80" 
                                    class="favorite" title="Thêm vào danh sách yêu thích" 
                                        onclick="toggleFavorite('{.//id}', this)" status="Off"/>
                            </xsl:if>
                        </xsl:if>  
                            
                        <xsl:if  test="$favList = ''">
                            <img src="img/star.png" width="80" height="80" 
                                    class="favorite" title="Thêm vào danh sách yêu thích" 
                                        onclick="toggleFavorite('{.//id}', this)" status="Off"/>
                        </xsl:if>                      
                    </xsl:if>
                    
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
