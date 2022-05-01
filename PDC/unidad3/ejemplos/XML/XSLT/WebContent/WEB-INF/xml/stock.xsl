<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet version="1.0" 
           xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
           xmlns:x="http://www.w3schools.com" >
    <xsl:output method="html"/>
    <xsl:param name="titulo" />
    <xsl:template match="/">
        <html>
            <head>
                <title>Ejemplo XSLT</title>
                <link rel="stylesheet" type="text/css" href="./css/style.css"/>
            </head>
            <body>
                <h3><xsl:value-of select="$titulo"/></h3>
                <table>
                    <colgroup>
                        <col width="300px"/>
                    </colgroup>
                    <thead>
                         <tr>
                             <td>Nombre</td>
                         </tr>
                    </thead>
                    <tbody>
                    <xsl:for-each select="x:productos/x:producto">
                    	<xsl:if test="x:cant = 0">
                            <tr>
                                <td><xsl:value-of select="x:nom"/></td>
                            </tr>
                        </xsl:if>    
                    </xsl:for-each>
                    </tbody>
                </table>      
                <br/><br/>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
