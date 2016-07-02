<?xml version="1.0" encoding="UTF-8"?>
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="report1" language="groovy" pageWidth="842" pageHeight="595" orientation="Landscape" columnWidth="802" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" uuid="f224ec12-d70d-409a-873e-0fb139e018d1">
	<property name="ireport.zoom" value="1.0"/>
	<property name="ireport.x" value="0"/>
	<property name="ireport.y" value="0"/>
	<style name="Crosstab Data Text" hAlign="Center"/>
	<parameter name="fechaInicio" class="java.util.Date">
		<defaultValueExpression><![CDATA[]]></defaultValueExpression>
	</parameter>
	<parameter name="fechaFinal" class="java.util.Date">
		<defaultValueExpression><![CDATA[]]></defaultValueExpression>
	</parameter>
	<queryString>
		<![CDATA[SELECT
     evento.`id` AS evento_id,
     evento.`fecha` AS evento_fecha,
     evento.`id_tarea` AS evento_id_tarea,
     evento.`id_trabajador` AS evento_id_trabajador,
     tareas.`id_tarea` AS tareas_id_tarea,
     tareas.`nombre` AS tareas_nombre,
     tareas.`abreviatura` AS tareas_abreviatura,
     trabajadores.`id_trabajador` AS trabajadores_id_trabajador,
     trabajadores.`nombre` AS trabajadores_nombre,
     trabajadores.`correo` AS trabajadores_correo
FROM
     `tareas` tareas INNER JOIN `evento` evento ON tareas.`id_tarea` = evento.`id_tarea`
     INNER JOIN `trabajadores` trabajadores ON evento.`id_trabajador` = trabajadores.`id_trabajador`

WHERE fecha BETWEEN $P{fechaInicio} and $P{fechaFinal}]]>
	</queryString>
	<field name="evento_id" class="java.lang.Integer"/>
	<field name="evento_fecha" class="java.sql.Date"/>
	<field name="evento_id_tarea" class="java.lang.Integer"/>
	<field name="evento_id_trabajador" class="java.lang.Integer"/>
	<field name="tareas_id_tarea" class="java.lang.Integer"/>
	<field name="tareas_nombre" class="java.lang.String"/>
	<field name="tareas_abreviatura" class="java.lang.String"/>
	<field name="trabajadores_id_trabajador" class="java.lang.Integer"/>
	<field name="trabajadores_nombre" class="java.lang.String"/>
	<field name="trabajadores_correo" class="java.lang.String"/>
	<background>
		<band splitType="Stretch"/>
	</background>
	<title>
		<band height="79" splitType="Stretch"/>
	</title>
	<pageHeader>
		<band height="35" splitType="Stretch"/>
	</pageHeader>
	<columnHeader>
		<band height="61" splitType="Stretch"/>
	</columnHeader>
	<detail>
		<band height="125" splitType="Stretch"/>
	</detail>
	<columnFooter>
		<band height="45" splitType="Stretch"/>
	</columnFooter>
	<pageFooter>
		<band height="54" splitType="Stretch"/>
	</pageFooter>
	<summary>
		<band height="42" splitType="Stretch">
			<crosstab>
				<reportElement x="0" y="0" width="802" height="42" uuid="e2b4cf47-2062-4673-b455-c9e90fa7597f"/>
				<rowGroup name="trabajadores_nombre" width="70" totalPosition="End">
					<bucket class="java.lang.String">
						<bucketExpression><![CDATA[$F{trabajadores_nombre}]]></bucketExpression>
					</bucket>
					<crosstabRowHeader>
						<cellContents backcolor="#F5F5DC" mode="Opaque">
							<box>
								<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textField>
								<reportElement style="Crosstab Data Text" x="0" y="0" width="70" height="25" uuid="ed33faef-353a-4f91-b84c-ec4680465375"/>
								<textFieldExpression><![CDATA[$V{trabajadores_nombre}]]></textFieldExpression>
							</textField>
						</cellContents>
					</crosstabRowHeader>
					<crosstabTotalRowHeader>
						<cellContents backcolor="#FFFFBF" mode="Opaque">
							<box>
								<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<staticText>
								<reportElement x="0" y="0" width="70" height="25" uuid="da3b2dbd-526b-454a-a0b8-bd36d475d08a"/>
								<textElement textAlignment="Center" verticalAlignment="Middle"/>
								<text><![CDATA[Total trabajadores_nombre]]></text>
							</staticText>
						</cellContents>
					</crosstabTotalRowHeader>
				</rowGroup>
				<columnGroup name="tareas_nombre" height="30" totalPosition="End">
					<bucket class="java.lang.String">
						<bucketExpression><![CDATA[$F{tareas_nombre}]]></bucketExpression>
					</bucket>
					<crosstabColumnHeader>
						<cellContents backcolor="#F5F5DC" mode="Opaque">
							<box>
								<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<textField>
								<reportElement style="Crosstab Data Text" x="0" y="0" width="50" height="30" uuid="083a0f48-53ed-4c56-bf3d-9aef8fdee643"/>
								<textFieldExpression><![CDATA[$V{tareas_nombre}]]></textFieldExpression>
							</textField>
						</cellContents>
					</crosstabColumnHeader>
					<crosstabTotalColumnHeader>
						<cellContents backcolor="#FFFFBF" mode="Opaque">
							<box>
								<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
							</box>
							<staticText>
								<reportElement x="0" y="0" width="50" height="30" uuid="191ef996-b5f2-4bfb-a646-df055ccea0a9"/>
								<textElement textAlignment="Center" verticalAlignment="Middle"/>
								<text><![CDATA[Total tareas_nombre]]></text>
							</staticText>
						</cellContents>
					</crosstabTotalColumnHeader>
				</columnGroup>
				<measure name="tareas_nombreMeasure" class="java.lang.Integer" calculation="Count">
					<measureExpression><![CDATA[$F{tareas_nombre}]]></measureExpression>
				</measure>
				<crosstabCell width="50" height="25">
					<cellContents>
						<box>
							<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
						</box>
						<textField>
							<reportElement style="Crosstab Data Text" x="0" y="0" width="50" height="25" uuid="81912534-9b51-46ae-8f89-433374d37f93"/>
							<textFieldExpression><![CDATA[$V{tareas_nombreMeasure}]]></textFieldExpression>
						</textField>
					</cellContents>
				</crosstabCell>
				<crosstabCell height="25" rowTotalGroup="trabajadores_nombre">
					<cellContents backcolor="#FFFFBF" mode="Opaque">
						<box>
							<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
						</box>
						<textField>
							<reportElement style="Crosstab Data Text" x="0" y="0" width="50" height="25" uuid="bb38dbef-f0e0-441e-a65a-7096013507c4"/>
							<textFieldExpression><![CDATA[$V{tareas_nombreMeasure}]]></textFieldExpression>
						</textField>
					</cellContents>
				</crosstabCell>
				<crosstabCell width="50" columnTotalGroup="tareas_nombre">
					<cellContents backcolor="#FFFFBF" mode="Opaque">
						<box>
							<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
						</box>
						<textField>
							<reportElement style="Crosstab Data Text" x="0" y="0" width="50" height="25" uuid="ca490340-db7b-42c3-a975-338de56bb2d3"/>
							<textFieldExpression><![CDATA[$V{tareas_nombreMeasure}]]></textFieldExpression>
						</textField>
					</cellContents>
				</crosstabCell>
				<crosstabCell rowTotalGroup="trabajadores_nombre" columnTotalGroup="tareas_nombre">
					<cellContents backcolor="#FFFFBF" mode="Opaque">
						<box>
							<pen lineWidth="0.5" lineStyle="Solid" lineColor="#000000"/>
						</box>
						<textField>
							<reportElement style="Crosstab Data Text" x="0" y="0" width="50" height="25" uuid="186a9750-aa63-4ae9-b60f-2619fe53cd99"/>
							<textFieldExpression><![CDATA[$V{tareas_nombreMeasure}]]></textFieldExpression>
						</textField>
					</cellContents>
				</crosstabCell>
			</crosstab>
		</band>
	</summary>
</jasperReport>
