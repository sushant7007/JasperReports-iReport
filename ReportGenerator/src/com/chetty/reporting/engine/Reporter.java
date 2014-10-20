package com.chetty.reporting.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chetty.reporting.beans.DataBean;
import com.chetty.reporting.business.DataBeanMaker;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Reporter {

	public void create() throws JRException {
		DataBeanMaker dataBeanMaker = new DataBeanMaker();
		ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map<String, Object> parameters = new HashMap<String, Object>();

		File reportPattern = new File("/home/heorhi/test_jasper.jrxml");

		JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"/home/heorhi/TestResult.pdf");
	}

	public static void main(String[] args) throws Exception {
		new Reporter().create();
	}
}
