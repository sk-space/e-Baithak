package com.khwopa.ebaithak;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadController {
	

	@RequestMapping(value="/download", method=RequestMethod.GET)
	public String downloadReport() throws Exception{
		
			
			String webPage = "http://google.com";
			System.out.println(webPage);
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String File_To_Convert = sb.toString();

			System.out.println("*** BEGIN ***");
			System.out.println(File_To_Convert);
			System.out.println("*** END ***");
//			
//
////			String k = "<html><body> This is my Project </body></html>";
//		    OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"));
//		    Document document = new Document();
//		    PdfWriter.getInstance(document, file);
//		    document.open();
//		    HTMLWorker htmlWorker = new HTMLWorker((DocListener) document);
//		    htmlWorker.parse(new StringReader(File_To_Convert));
//		    document.close();
//		    file.close();
		
//		String File_To_Convert = "C:\\Users\\SPACE\\Documents\\workspace-sts-3.9.1.RELEASE\\eBaithak\\src\\main\\webapp\\WEB-INF\\views\\report.jsp";
//        String url = new File(File_To_Convert).toURI().toURL().toString();
//        System.out.println(""+url);
//        String HTML_TO_PDF = "D:\\ConvertedFile.pdf";
//        OutputStream os = new FileOutputStream(HTML_TO_PDF);       
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocument(url);      
//        renderer.layout();
//        renderer.createPDF(os);        
//        os.close();
		
//		InputStream in = new URL( "http://jakarta.apache.org" ).openStream();
//		String File_To_Convert = getUrlAsString("https://www.google.com");
//		String File_To_Convert = "test.htm";
        
		  return "redirect:/baithak";
	} 
	
	
	
}
