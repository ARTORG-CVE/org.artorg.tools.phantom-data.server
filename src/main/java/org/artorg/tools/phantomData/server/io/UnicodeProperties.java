package org.artorg.tools.phantomData.server.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeProperties extends Properties {
	  private static final long serialVersionUID = 1L;
	  @Override
	  public void store(OutputStream out, String comments) throws IOException {
	      customStore0(new BufferedWriter(new OutputStreamWriter(out, "8859_1")),
	                   comments, true);
	      out.close();
	  }
	  //Override to stop '/' or ':' chars from being replaced by not called 
	  //saveConvert(key, true, escUnicode)
	  private void customStore0(BufferedWriter bw, String comments, boolean escUnicode)
	          throws IOException {
	      bw.write("#" + new Date().toString());
	      bw.newLine();
	      synchronized (this) {
	          for (Enumeration e = keys(); e.hasMoreElements();) {
	              String key = (String) e.nextElement();
	              String val = (String) get(key);
	              // Commented out to stop '/' or ':' chars being replaced
	              //key = saveConvert(key, true, escUnicode);
	              //val = saveConvert(val, false, escUnicode);
	              bw.write(key + "=" + val);
	              bw.newLine();
	          }
	      }
	      bw.flush();
	  }
	  
	  @Override
	  public String getProperty(String key) {
		  String value = super.getProperty(key);
		  if (value == null) throw new NullPointerException("key = " +key);
		  return value;
	  }

	  @Override
	public synchronized void load(InputStream inStream) throws IOException {
		customLoad0(new BufferedReader(new InputStreamReader(inStream)));
		inStream.close();
	}
	
	private void customLoad0(BufferedReader bufferedReader) {
		bufferedReader.lines().forEach(line -> {
			Matcher m = Pattern.compile("(.*)=(.*)").matcher(line);
			if (m.find()) {
				String key = m.group(1);
				String value = m.group(2);
				this.put(key, value);
			}
		});
	}
	
	
}