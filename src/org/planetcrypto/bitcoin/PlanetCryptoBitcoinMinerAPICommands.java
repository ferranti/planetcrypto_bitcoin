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

import java.io.*;
import java.net.*;
import java.util.logging.*;
import org.json.simple.parser.*;
import org.json.simple.*;

public class PlanetCryptoBitcoinMinerAPICommands {
    static private final int MAXRECEIVESIZE = 65535;
    //static private Socket socket = null;

 
    public String prettyPrint(String result) throws IOException {
        String value;
	String name;
	String[] sections = result.split("\\|", 0);
        StringBuffer pretty_print = new StringBuffer();
	for (int i = 0; i < sections.length; i++) {
            if (sections[i].trim().length() > 0) {
                String[] data = sections[i].split(",", 0);
                for (int j = 0; j < data.length; j++) {
                    String[] nameval = data[j].split("=", 2);

                    if (j == 0) {
                        if (nameval.length > 1 &&  Character.isDigit(nameval[1].charAt(0))) {
                            name = nameval[0] + nameval[1];
                        } else {
                            name = nameval[0];
                        }
                        //System.out.println("[" + name + "] =>");
                        pretty_print.append("[" + name + "] =>\n");
                        //System.out.println("(");
                        pretty_print.append("(\n");
			}

                    if (nameval.length > 1) {
                        name = nameval[0];
			value = nameval[1];
                    } else {
			name = "" + j;
			value = nameval[0];
                    }

                    //System.out.println("   ["+name+"] => "+value);
                    pretty_print.append("   [" + name + "] => " + value + "\n");
                }
		//System.out.println(")");
                pretty_print.append(")\n");
            }
	}
        return pretty_print.toString();
    }
    private static String process(String cmd, InetSocketAddress ip, int port) throws Exception
    {
        Socket socket = new Socket();
        StringBuffer sb = new StringBuffer();
        char buf[] = new char[MAXRECEIVESIZE];
        int len = 0;
        //System.out.println("Attempting to send: " + cmd + " to: "+ ip.getHostAddress()+":"+port);
        try {
            //socket = new Socket(ip, port);
            socket.connect(ip, 2000);
            //System.out.println("Start Sleep");
            //Thread.sleep(1000);
            //System.out.println("Stop Sleep");
            socket.setSoTimeout(2000);
            //socket.(ip, port);
            //System.out.println(socket.getSoTimeout());
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.print(cmd.toLowerCase().toCharArray());            
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            while (0x80085 > 0) {
                len = isr.read(buf, 0, MAXRECEIVESIZE);
                if (len < 1) {
                    break;
                }
                sb.append(buf, 0, len);
                if (buf[len-1] == '\0') {
                    break;
                }
            }
            //closeAll();
            socket.close();
            socket = null;
        }
        catch (IOException ioe) {
            System.err.println(ioe.toString()+", " + ip.getHostName());
            //closeAll();
            socket.close();
            socket = null;
            return "Failed to get information";
            }
        String result = sb.toString();
        //System.out.println(result);
        return result.replace("\0", "");
        //jsonFactory(result);
        //System.out.println(result);
        }
    private static String API(String command, String _ip, String _port) throws Exception {
        //System.out.println("Sending: " + command + "to: " + _ip+":"+_port);
        //InetAddress ip;
        int port;
        /*
        try {
            ip = InetAddress.getByName(_ip);
        }
        catch (UnknownHostException uhe) {
            //System.err.println("Unknown Host " + _ip + ": " + uhe);
            return "Failed to resolve host";
        } */               
        try {
            port = Integer.parseInt(_port);
        }
        catch (NumberFormatException nfe) {
            //System.err.println("Invalid port " + _port + ": " + nfe);
            return "Invalid Port";
        }
        InetSocketAddress ip = new InetSocketAddress(_ip, port);
        String result = process(command, ip, port);
        return result;
    }
    @SuppressWarnings("unchecked")
    public String parseArgs(String _ip, String _port, String command, Boolean json_response) {
        JSONObject json = new JSONObject();
        String ip = new String();
        String port = new String();
        if (command.length() > 0 && command.trim().length() > 0) {
            String cmd = command.trim();
            int len = cmd.length();
            String param_sep = "|";
            if (cmd.toLowerCase().contains(param_sep)) {
                //System.out.println("Specific command detected, parting string for parameters");
                String _cmd = cmd.substring(0, cmd.indexOf("|"));
                String _parameters = cmd.substring(cmd.indexOf("|")+1, len);
                json.put("parameter", _parameters);
                json.put("command", _cmd);
            
            } else {
            //System.out.println("Setting command to: " + cmd);
            json.put( "command", cmd );
            }
        }
        if (_ip.length() > 1 && _ip.trim().length() > 0) {
            //System.out.println("Setting IP to: " + ip.trim());
            ip = _ip.trim();
        }
        if (_port.length() > 2 && _port.trim().length() > 0) {
            //System.out.println("Setting port to: " + ip.trim());
            port = _port.trim();
        }
        try {
            if (json_response) {
                //System.out.println("Sending: " + json.toString());
                String result = API(json.toString(), ip, port);
                return result;
            } else {
                String result = API(command, ip, port);
                return result;
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanetCryptoBitcoin.class.getName()).log(Level.SEVERE, null, ex);
            return "fail";
        }
    }
    
    public String getKeys(String jsonText, String _key) {
        String key;
        if (_key.equals("btc_to_")) {
            key = _key+"usd";
        } else {
            key = _key;
        }
        JSONParser jsonParser = new JSONParser();
        PlanetCryptoBitcoinJSONKeyFinder finder = new PlanetCryptoBitcoinJSONKeyFinder();
        //System.out.println(jsonText);
        finder.setMatchKey(key);
        if (jsonText.startsWith("Fail")) {
            //System.out.println(jsonText);
            return "Fail";
        }
        try {
            while (!finder.isEnd()) {
                jsonParser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    //System.out.println("found id:" + finder.getValue());
                }
            }
            String value = finder.getValue().toString();
            return value;
        } catch (NullPointerException | ParseException e) {
            e.printStackTrace();
            return "Fail";
        }
    }
}