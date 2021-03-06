<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : accountListView.xsl
    Created on : July 10, 2017, 9:40 AM
    Author     : Dells
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:template match="accounts">
        <div class="table-wrapper">
            <div class="table" id="table">
                <div class="row header">
                    <div class="cell">
                        Username
                    </div>
                    <div class="cell">
                        Email
                    </div>
                    <div class="cell">
                        Fullname
                    </div>
                    <div class="cell">
                        Role
                    </div>
                    <div class="cell">
                        Action
                    </div>
                </div>
                <xsl:for-each select="account[position()&lt;10]">          
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
                            <xsl:value-of select=".//roleID//name"/>
                        </div>
                        <div class="cell">
                            <xsl:variable name="roleID" select="string(.//roleID/roleID)" />
                            <xsl:if test="$roleID='1'">
                                <a href="{$link}">
                                    <button type="button" class="btn btn-orange" 
                                            name="action" value="DeleteAccount">Xóa</button>
                                </a>
                            </xsl:if>
                        </div>
                    </div>
            
                </xsl:for-each>
       
            </div> <!--End table div-->
        </div> <!--End table wrapper div-->
    
    </xsl:template>
</xsl:stylesheet>


