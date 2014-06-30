/* 
 * The MIT License
 *
 * Copyright 2014 Halcyon <support@planetcrypto.com A2A1A088>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.planetcrypto.bitcoin;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;
 
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

/**
 *
 * @author Halcyon <support@planetcrypto.com A2A1A088>
 */
public class PlanetCryptoBitcoinCoinbase {
    private static final PlanetCryptoBitcoinMinerAPICommands key_parser = new PlanetCryptoBitcoinMinerAPICommands();
/*
    public static void main(String[] args) {
    
        new BFGMinerAPICoinbase().getContent();
    }
*/ 
    public String getContent(String _url, String _key) {
        String https_url = _url;
        URL url;
        try {
 
	    url = new URL(https_url);
	    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
 
	    //dumpl all cert info
	    //print_https_cert(con);
 
	    //dump all the content
	    String content = print_content(con);
            String key = key_parser.getKeys(content, _key);
            //System.out.println(key);
            return key;
        } catch (MalformedURLException e) {
	    e.printStackTrace();
            return null;
        } catch (IOException e) {
	    e.printStackTrace();
            return null;
        } 
    }
 
    private void print_https_cert(HttpsURLConnection con){
 
        if(con!=null){
 
            try {
                
                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");
 
                Certificate[] certs = con.getServerCertificates();
                for(Certificate cert : certs){
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }
 
            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e){
            e.printStackTrace();
            } 
        } 
   }
 
    private String print_content(HttpsURLConnection con){
        StringBuffer sb = new StringBuffer();
        
	if(con!=null){
 
            try {
 
                //System.out.println("****** Content of the URL ********");			
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
                String input;
                while ((input = br.readLine()) != null){
                    sb.append(input);
                    //System.out.println(input);                
                }
                br.close();
                return sb.toString();

 
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } 
        }
        return null;
    }    
}
