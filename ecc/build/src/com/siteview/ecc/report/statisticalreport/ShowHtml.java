package com.siteview.ecc.report.statisticalreport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.util.resource.Labels;

import com.siteview.ecc.report.common.ReportFileToolkit;
import com.siteview.ecc.reportserver.Constand;
import com.siteview.ecc.reportserver.DirectoryZip;
import com.siteview.ecc.reportserver.StatsReport;
import com.siteview.ecc.util.Toolkit;

public class ShowHtml extends HttpServlet{
	private String currentPage = "1";
	private String reportGenID = "";
	private String createTimeInMillis = "";
	private int pageCount = 0;
	private String download = null;

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getReportGenID() {
		return reportGenID;
	}

	public void setReportGenID(String reportGenID) {
		this.reportGenID = reportGenID;
	}

	
	public String getCreateTimeInMillis() {
		return createTimeInMillis;
	}

	public void setCreateTimeInMillis(String createTimeInMillis) {
		this.createTimeInMillis = createTimeInMillis;
	}

	public String getContent() {
//		String fileName = Toolkit.getToolkit().formatDate(new Date(Long.parseLong(reportGenID)), "yyyyMMdd_")+ reportGenID;
		String fileName = createTimeInMillis + "_" + reportGenID;
		String htmlurl = StatsReport.getHtmlFolderName(createTimeInMillis,reportGenID)
				+ fileName + currentPage + ".html";
		String path=StatsReport.getCreateFile(createTimeInMillis,reportGenID,"html");
		File file = new File(htmlurl);
		StringBuffer content = new StringBuffer();
		if (!file.exists())
			return Labels.getLabel("FileNotExist");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			pageCount = ReportFileToolkit.listHtmlFiles(new File(StatsReport.getHtmlFolderName(createTimeInMillis,reportGenID)),"html").length;
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "utf-8");
			reader = new BufferedReader(isr);
			String strReadLine = reader.readLine();
			while (strReadLine != null) {
				content.append(strReadLine).append("\n");
				strReadLine = reader.readLine();
			}
		} catch (Exception e) {
			return Labels.getLabel("Read") + file.getName() + Labels.getLabel("ErrorC");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String begin_string = String.format("<a name=\"JR_PAGE_ANCHOR_0_%s\"/>", currentPage);
		int beginflag = content.indexOf(begin_string);
		int trindex = content.indexOf("<tr>",beginflag);
		content.insert(trindex,getScroolBar());
		if(download!=null){
		}
		return content.toString();
	}
	
	  private String getScroolBar()
	  {
		  StringBuffer scroll=new StringBuffer();

		  if(Integer.parseInt(currentPage)>1)
		  {
		  scroll.append("<a style=\"font-size:12px;text-decoration: underline\" href=\"/ecc/main/report/showStatisticReport.jsp");
		  scroll.append("?reportGenID=").append(reportGenID);
		  scroll.append("&currentPage=").append(1);
		  scroll.append("&createTimeInMillis=").append((createTimeInMillis));
		  scroll.append("\">First page</a>&nbsp;");
		  }
		  else
		  {
			  scroll.append("<span style=\"font-size:12px\">First page</span>&nbsp;");
		  }
		  if(Integer.parseInt(currentPage)>1)
		  {
			  scroll.append("<a style=\"font-size:12px;text-decoration: underline\" href=\"/ecc/main/report/showStatisticReport.jsp");
			  scroll.append("?reportGenID=").append(reportGenID);
			  scroll.append("&currentPage=").append((Integer.parseInt(currentPage)-1));
			  scroll.append("&createTimeInMillis=").append((createTimeInMillis));
			  scroll.append("\">Previous page</a>&nbsp;");
		  }else
		  {
			  scroll.append("<span style=\"font-size:12px\">Previous page</span>&nbsp;");
		  }
		  if(Integer.parseInt(currentPage)<pageCount)
		  {
			  scroll.append("<a style=\"font-size:12px;text-decoration: underline\" href=\"/ecc/main/report/showStatisticReport.jsp");
			  scroll.append("?reportGenID=").append(reportGenID);
			  scroll.append("&currentPage=").append((Integer.parseInt(currentPage)+1));
			  scroll.append("&createTimeInMillis=").append((createTimeInMillis));
			  scroll.append("\">Next page</a>&nbsp;");
		  }
		  else
		  {
			  scroll.append("<span style=\"font-size:12px\">Next page</span>&nbsp;");
		  }
		  if(Integer.parseInt(currentPage)<pageCount)
		  { 
		  scroll.append("<a style=\"font-size:12px;text-decoration: underline\" href=\"/ecc/main/report/showStatisticReport.jsp");
		  scroll.append("?reportGenID=").append(reportGenID);
		  scroll.append("&currentPage=").append(pageCount);
		  scroll.append("&createTimeInMillis=").append((createTimeInMillis));
		  scroll.append("\">End of page</a>&nbsp;");
		  }
		  else
		  {
			  scroll.append("<span style=\"font-size:12px\">End of page</span>&nbsp;");
		  }
		  scroll.append("<span style=\"font-size:12px\">Total of:");
		  scroll.append(pageCount);
		  scroll.append("Page</span>&nbsp;");
		  
		  
		  scroll.append("<span style=\"font-size:12px\">Now is:");
		  scroll.append(currentPage);
		  scroll.append("Page</span>&nbsp;");
		  
		  
		  scroll.append("<a style=\"font-size:12px;text-decoration: underline\" href=\"/ecc/main/report/saveStatisticReport");
		  scroll.append("?reportGenID=").append(reportGenID);
		  scroll.append("&currentPage=").append((currentPage));
		  scroll.append("&createTimeInMillis=").append((createTimeInMillis));
		  scroll.append("\">Save</a>&nbsp;");
//		  if(isDownload) downloadFile();
		  return scroll.toString();
	  }
	  
	  public void doGet(HttpServletRequest req , HttpServletResponse rsp){
		  FileInputStream fis = null;
		  OutputStream os  = null;
		  try {
			  reportGenID = req.getParameter("reportGenID");
			  currentPage = req.getParameter("currentPage");
			  download = req.getParameter("download");
			  createTimeInMillis = req.getParameter("createTimeInMillis"); 
//			  String fileName = Toolkit.getToolkit().formatDate(new Date(Long.parseLong(reportGenID)), "yyyyMMdd_")	+ reportGenID;
			  String fileName = createTimeInMillis + "_" + reportGenID;
			  String srcFolder = Constand.statreportsavepath+fileName;
			  File f2 = new File(srcFolder + ".zip");
			  if(!f2.exists()){
				  DirectoryZip zip = new DirectoryZip();
				  zip.zip(srcFolder, srcFolder + ".zip");
			  }
			  fis = new FileInputStream(f2);
			  ((HttpServletResponse)rsp).setContentType("APPLICATION/OCTET-STREAM"); 
			  ((HttpServletResponse)rsp).setHeader("Content-Disposition", "attachment; filename=\"" + "staticReport.zip" + "\"");
			  int size = 0;
			  byte[] buff = new byte[1024];
			  os = rsp.getOutputStream();
			  while((size=fis.read(buff))!=-1){
				  os.write(buff, 0, size);
				  os.flush();
			  }
		  } catch (IOException e) {
			  e.printStackTrace();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }finally{
			  try {
				if( os != null ) os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			  try {
				if ( fis != null ) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
	  }
	  public void doPost(HttpServletRequest req , HttpServletResponse rsp){
		  doGet(req,rsp);
	  }
	  public static void main(String[] args) throws Exception{
		  String src = "E:/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ecc/main/report/statreport/20091111_1257921824875";
		  DirectoryZip zip = new DirectoryZip();
		  zip.zip(src, src + "20091111_1257921824875.zip");
	  }
}
