<?xml version="1.0" encoding="EUC-KR"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="EUC-KR"/>
	<xsl:template match="list">
		<!-- TODO: Auto-generated template -->
		<html>
			<head>
				<title>å ���</title>
			</head>
			<body>
				���� ��ϵǾ� �ִ� å�� ����� ������ �����ϴ�.
				<ul>
					<xsl:for-each select="book">
						<li>
							<b>
								<xsl:value-of select="title" />
							</b>
							(<xsl:value-of select="price" />) ��)
							<br />
							<i>
								<xsl:value-of select="author" />
							</i>
						</li>
					</xsl:for-each>
				</ul>
			</body>
		</html>
		
	</xsl:template>
</xsl:stylesheet>