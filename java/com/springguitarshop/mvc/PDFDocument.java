package com.springguitarshop.mvc;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Table;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.springguitarshop.domain.Guitar;

public class PDFDocument extends AbstractPdfView{
	@Override
	protected void buildPdfDocument(Map model, com.lowagie.text.Document document,
			com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//get guitar from model
		Guitar guitar=(Guitar) model.get("guitar");
		//get Image
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "images" + File.separator+"guitars"+File.separator+guitar.getId()+".jpg");
		Image image=Image.getInstance(dir.toString());
		image.scalePercent(10);		 
		//create table
		PdfPTable table = new PdfPTable(2);
		//add cells
		//headers
		table.addCell("photo");
		table.addCell("info");
		//image
		table.addCell(image);
		//info		
		table.addCell("Guitar name: "+guitar.getName()+"\n"+
					  "Guitar owner: "+guitar.getUser().getName()+"\n"+
					  "Guitar owner telephone: "+guitar.getUser().getPhone()+"\n"+
					  "Guitar type: "+guitar.getType()+"\n"+
					  "Guitar price: "+guitar.getPrice()+"\n"+
					  "Guitar description: "+guitar.getDescription());
		
		//add to document
		document.add(table);
		
	}

}
