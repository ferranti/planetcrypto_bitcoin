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


import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

/**
 *
 * @author halcyon
 */
public class PlanetCryptoNetworkMethods {
    /**
     * 
     * @param eligius_username Eligius username
     * @return JSON String from Eligius API
     */
    public String getHashRate(String eligius_username) {
        StringBuffer json = new StringBuffer();
        String line;
        List<String> info;
        try {
            URL url = new URL("http://eligius.st/~wizkid057/newstats/api.php?cmd=gethashrate" + eligius_username);
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            return json.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "Error";      
        }
    }
    /**
     * ?????????????????????
     * @param _hashrate 
     * @param _interval
     * @return 
     */
    public List<String> getHashRateIntervals(String _hashrate, String _interval) {
        List<String> info = new ArrayList<>();
        try {
            JSONObject hashrates = new JSONObject(_hashrate);
            JSONObject interval = hashrates.getJSONObject("output");
            interval= interval.getJSONObject(_interval);
            info.add(interval.getString("pretty"));
            info.add(interval.getString("share_count"));
            return info;
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    /**
     * 
     * @param eligius_username Eligius username
     * @return JSON string of user statistics from Eligius API
     */
    public String getUserStat(String eligius_username) {
        StringBuffer json = new StringBuffer();
        String line;
        try {
            URL url = new URL("http://eligius.st/~wizkid057/newstats/api.php?cmd=getuserstat&username=" + eligius_username);
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            return json.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "Error";      
        }
    }      
    /**
     * 
     * @param _userstats
     * @param field
     * @return 
     */
    public String getUserStats(String _userstats, String field) {
        List<String> userstats = new ArrayList<>();
        //System.out.println(_userstats);
        try {
            JSONObject userstatistics = new JSONObject(_userstats);
            //System.out.println(userstatistics.toString());
            userstatistics = userstatistics.getJSONObject("output");         
            String temp = Double.toString(userstatistics.getLong(field)*0.00000001);
            if (temp.length() >= 10 ) { 
                temp = temp.substring(0, 10) + " BTC";
            } else {
                temp = temp + " BTC";
            }
            return temp;
        } catch (JSONException e) {
            System.err.println(e.getMessage()+ " " + field + " " + _userstats);
            return null;
        }
    }
    /**
     * 
     * @param uri URL of Eligius user stats
     */
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
/*
    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
*/
}
