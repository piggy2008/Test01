import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpPostFile {
	public static void main(String[] args) throws ClientProtocolException, IOException {  
        post();  
    }  
  
    public static void post() throws ClientProtocolException, IOException {  
        HttpClient httpclient = new DefaultHttpClient();  
        HttpPost post = new HttpPost("http://localhost:8080/action.jsp");  
        FileBody fileBody = new FileBody(new File("C:\\Users\\piggy\\Desktop\\resume_pic\\chengji.jpg"));  
        StringBody stringBody = new StringBody("�ļ�������");  
        MultipartEntity entity = new MultipartEntity();  
        entity.addPart("file", fileBody);  
        entity.addPart("desc", stringBody);  
        post.setEntity(entity);  
        HttpResponse response = httpclient.execute(post);  
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){    
              
            HttpEntity entitys = response.getEntity();  
            if (entity != null) {  
                System.out.println(entity.getContentLength());  
                System.out.println(EntityUtils.toString(entitys));  
            }
        }
        httpclient.getConnectionManager().shutdown();  
    }  
}
