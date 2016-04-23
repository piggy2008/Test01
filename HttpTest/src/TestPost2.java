import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author <a href="mailto:just_szl@hotmail.com"> Geray</a>
 * @version 1.0,2012-6-12 
 */
public class TestPost2 {

	//file1��file2��ͬһ���ļ����� filepath�Ǹ��ļ���ָ����·��	
	public void SubmitPost(String url,String filename1,String filename2, String filepath){
		
		HttpClient httpclient = new DefaultHttpClient();
		
		try {
	
			HttpPost httppost = new HttpPost(url);
			
		    FileBody bin = new FileBody(new File(filepath + File.separator + filename1));
			  
		    FileBody bin2 = new FileBody(new File(filepath + File.separator + filename2));
		    
		    StringBody comment = new StringBody(filename1);

		    MultipartEntity reqEntity = new MultipartEntity();
		    
		    reqEntity.addPart("file1", bin);//file1Ϊ�����̨��File upload;����    
		     reqEntity.addPart("file2", bin2);//file2Ϊ�����̨��File upload;����
		     reqEntity.addPart("filename1", comment);//filename1Ϊ�����̨����ͨ����;����	
		    httppost.setEntity(reqEntity);
		    
		    HttpResponse response = httpclient.execute(httppost);
		    
			    
		    int statusCode = response.getStatusLine().getStatusCode();
		    
			    
			if(statusCode == HttpStatus.SC_OK){
			    	
				System.out.println("������������Ӧ.....");
				
		    	HttpEntity resEntity = response.getEntity();
			    
		    	
		    	System.out.println(EntityUtils.toString(resEntity));//httpclient�Դ��Ĺ������ȡ��������
			    
		    	
		    	
		    	System.out.println(resEntity.getContent());   

		    	EntityUtils.consume(resEntity);
		    }
			    
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			    try { 
			    	httpclient.getConnectionManager().shutdown(); 
			    } catch (Exception ignore) {
			    	
			    }
			}
		}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestPost2 httpPostArgumentTest2 = new TestPost2();
		
		httpPostArgumentTest2.SubmitPost("http://127.0.0.1:8080/demo/receiveData.do",
				"test.xml","test.zip","D://test");
	}
	
}
