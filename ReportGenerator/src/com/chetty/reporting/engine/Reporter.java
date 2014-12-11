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

	String pathForSaving = "D:\\Free time\\TestResult.pdf";
	String pathForPattern = "D:\\Free time\\workspace\\JasperReports-iReport\\ReportGenerator\\test_jasper.jrxml";
	//String pathForSaving = "/home/heorhi/TestResult.pdf"
	//String pathForPattern = "/home/heorhi/test_jasper.jrxml";
	public void create() throws JRException {
		DataBeanMaker dataBeanMaker = new DataBeanMaker();
		ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataBeanList);

		Map<String, Object> parameters = new HashMap<String, Object>();

		File reportPattern = new File(pathForPattern);

		JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, beanColDataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				pathForSaving);
		

	}

	public static void main(String[] args) {
		System.out.println("Начало генерации отчёта");
		try {
			new Reporter().create();
			System.out.println("Генерация отчёта завершена");
		} catch (JRException e) {
			System.out.println("Во время генерации возникла ошибка: "+e);
		}
		
	}
}
