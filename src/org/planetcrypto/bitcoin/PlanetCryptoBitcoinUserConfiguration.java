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
import com.sun.webpane.webkit.dom.CSSStyleSheetImpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.*;
import javax.swing.JOptionPane;

/**
 *
 * @author halcyon
 */

public class PlanetCryptoBitcoinUserConfiguration {
    /**
     * Database Connection
     */
    static private Connection c = null;
    /**
     * Database Statement
     */
    static private Statement stmt = null;
    /**
     * Regex for validating IP address(es)
     */
    private static final String IP_PATTERN = 
        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    /**
     * User's home directory for configuration database location
     */
    private static final String configDir = System.getProperty("user.home")+"/.planetcrypto/";
    /**
     * Closes connection to database     * 
     */
    public void closeAll()
    {
        try {
        if (c != null)
        {
	c.close();
	c = null;
	}
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
        }
    }
    /**
     * Initiates connection to database
     * @return void
     */
    private void initDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+configDir+"bfgminerapi_config.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='miners'");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Welcome to Planet Crypto's Bitcoin Mining API!\n" +
                                                    "Please visit the configuration tab to configure\n" +
                                                    "this product to your needs.\n",
                                                    "Welcome!", JOptionPane.INFORMATION_MESSAGE);
                initTables();
            } else {
                stmt.close();
                stmt = null;
            }
        } catch ( ClassNotFoundException | SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }
    /**
     * Initiates database tables if not already initiated
     * @return void
     */    
    private void initTables() {
        if (c == null) {
            initDatabase();
        }
        try {
            stmt = c.createStatement();
            String global_config = "CREATE TABLE global " +
                                   "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                   "refresh_rate     INTEGER NOT NULL," + 
                                   "alarm_sound TEXT," +
                                   "alarm_color TEXT," +
                                   "sound_enabled INTEGER NOT NULL);";
            
            String miners_id_sql = "CREATE TABLE miners " +
                                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name           TEXT             NOT NULL, " +
                                "ip             TEXT             NOT NULL," +
                                "port           TEXT             NOT NULL);";
                     
            String alarms_sql = "CREATE TABLE alarms" +
                                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "miner      TEXT        NOT NULL," +
                                "fan        REAL     ," +
                                "temp       REAL     ," +
                                "hash_rate  REAL     ," +
                                "hash_rate_type TEXT ," +
                                "hw_err     REAL     );";
            
            String eligius_info = "CREATE TABLE eligius_info" +
                                  "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                  "username       TEXT        NOT NULL);";
            
            String coinbase_sql = "CREATE TABLE coin " +
                                  "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                  "email          TEXT        NOT NULL UNIQUE," +
                                  "api_key        TEXT        ," +
                                  "api_secret     TEXT        ," +
                                  "currency       TEXT        NOT NULL);";
                                 
            stmt.executeUpdate(global_config);
            stmt.executeUpdate(miners_id_sql);            
            stmt.executeUpdate(eligius_info);
            stmt.executeUpdate(alarms_sql);
            stmt.executeUpdate(coinbase_sql);
            stmt.close();
            stmt = null;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }
    /**
     * Adds Coinbase users to database
     * @param values HashMap of values to add or modify in database
     * @param email Coinbase username
     * @param exists true if email in database already
     * @return String "Fail" if command fails; "Success" if command succeeds
     */
    public String addCoinBase(LinkedHashMap<String, String> values, String email, boolean exists) {       
        String api_key = "";
        String api_secret = "";
        String currency = "";
        String sql_update;
        ArrayList<String> keys = new ArrayList<>();
        StringBuffer coin_update = new StringBuffer();
        if (c == null) {
            initDatabase();
        }
        if (!exists) {
            try {
                stmt = c.createStatement();
                //c.setAutoCommit(false);
                coin_update = new StringBuffer();
                keys.addAll(values.keySet());         
                coin_update.append("INSERT INTO coin (email, api_key, api_secret, currency) VALUES(");                  
                for (int i = 0; i < values.size(); ++i) {
                    if (!(i == values.size() - 1)) {
                        coin_update.append("'" + values.get(keys.get(i)) +"', ");
                    } else {
                        coin_update.append("'" + values.get(keys.get(i)) +"'); ");
                    }
                }
                stmt.executeUpdate(coin_update.toString());
                //c.rollback();
                stmt.close();
                stmt = null;      
                return "Success";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Fail";
            }
        } else if (exists) {
            try {
                stmt = c.createStatement();
                coin_update = new StringBuffer();
                keys.addAll(values.keySet());
                coin_update.append("UPDATE coin SET ");                  
                for (int i = 0; i < values.size(); ++i) {
                    if (!(i == values.size() - 1)) {
                        coin_update.append(keys.get(i) + "='");
                        coin_update.append(values.get(keys.get(i)) +"', ");
                    } else {
                        coin_update.append(keys.get(i) + "='");
                        coin_update.append(values.get(keys.get(i)) +"' ");
                    }
                }
                coin_update.append("WHERE email='" + email + "';");
                stmt.executeUpdate(coin_update.toString());
                //c.rollback();
                stmt.close();
                stmt = null;      
                return "Success";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Fail";                
            }
        } 
        System.out.println("We didn't cover this case: " + values.toString() );
        return "Fail";
    }     
    /**
     * Returns current Coinbase users
     * @return null if no Coinbase users, HashMap containing all
     *         E-mails, API keys, API secrets, and preferred currency if users exist
     */
    public HashMap<Integer, HashMap<String, String>> getCoinbase() {
        HashMap<Integer, HashMap<String, String>> coinbase = new HashMap<>();
        
        if (c == null) {
            initDatabase();
        }

        try {
            stmt = c.createStatement();
            String sql_query = "SELECT email, api_key, api_secret, currency FROM coin";
            ResultSet rs = stmt.executeQuery(sql_query);
            if (rs.isBeforeFirst()) {
                int i = 0;                        
                while (rs.next()) {
                    HashMap<String, String> current_email = new HashMap<>();                    
                    current_email.put("email", rs.getString("email"));
                    current_email.put("api_key", rs.getString("api_key"));
                    current_email.put("api_secret", rs.getString("api_secret"));
                    current_email.put("currency", rs.getString("currency"));
                    coinbase.put(i, current_email);
                    ++i;
                }
                return coinbase;
            } else {
                return null;
            }            
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    /**
     * Removes Coinbase username by E-mail address
     * @param email
     * @return String "Success!" if command succeeds, "Fail" if command fails
     */
    public String removeCoinbase(String email) {
        if (c == null) {
            initDatabase();
        }
        try {
            stmt = c.createStatement();
            String sql_update = "DELETE FROM coin WHERE email='"+email +"';";
            stmt.executeUpdate(sql_update);
            stmt.close();
            stmt = null;
            return "Success!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Fail";
        }
    }
    /**
     * Add miner information if miner not in database
     * @param miner
     * @return String Fail or Success
     */
    public String addMiners(Map<String, String> miner) {
        miner.put("name", miner.get("name").toLowerCase());
        // proper list format is [["name":"miner_name", "ip":"miner_ip", "port":"miner_port"], ["name":"antminer1", "ip":"192.168.1.98", "port":"98"], ...]
        if (c == null) {
            initDatabase();
        }
        try {
              stmt = c.createStatement();
              String test_exists = "SELECT * FROM miners where name='"+miner.get("name")+"';";
              ResultSet rs = stmt.executeQuery(test_exists);
                if (!rs.next()) {
                    String add_miner = "INSERT INTO miners (name, ip, port) "+
                                                    "VALUES('"+
                                                    miner.get("name")+"','"+
                                                    miner.get("ip")+"','"+
                                                    miner.get("port")+
                                                    "');";
                    stmt.executeUpdate(add_miner);
                    stmt.close();
                    return "Miner Added";
                    
                }
                else {
                    return "Miner exists";
                }                 
        } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    return "SQL Error, please make sure the SQLite database is reachable";
        }
    }
    /**
     * Removes miner by name if in database
     * @param _miner IP or name of miner
     * @param _type "ip" or "name"
     * @return String Fail or Success
     */
    public String removeMiner(String _miner, String _type) {
        String miner = _miner.toLowerCase();
        String type = _type.toLowerCase();
        if ( c == null) {
            initDatabase();
        }
        try {
            stmt = c.createStatement();
            String sql = "SELECT "+type+" FROM miners WHERE "+type+"='"+miner+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return "Miner: " + miner +" does not exist.";
            } else {
                sql = "DELETE FROM miners WHERE "+type+"='"+miner+"'";
                stmt.executeUpdate(sql);
                return "Successfully removed "+miner+".";
            }
         } catch (SQLException e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return "Failed to remove "+miner+".";
        }
    }
    /**
     * Returns current miners in HashMap
     * @return HashMap <Integer, HashMap<String, String>>
     */
    public HashMap<Integer, HashMap<String, String>> getMiners() { 
       HashMap<Integer, HashMap<String, String>> miners = new HashMap<>();
       if (c == null) {
           initDatabase();
       }
       try {
       stmt = c.createStatement();
       String sql = "Select * from miners";
       ResultSet rs = stmt.executeQuery(sql);
       int i = 0;
       while (rs.next()) {
           HashMap<String, String> miner = new HashMap<>();         
           miner.put("name", rs.getString("name"));
           miner.put("ip", rs.getString("ip"));
           miner.put("port", rs.getString("port"));
           miners.put(i, miner);
           ++i;
       }
       if (miners.isEmpty()) {
           HashMap<String, String> miner = new HashMap<>();
           miner.put("name", "No Miners");
           miner.put("ip", "None");
           miner.put("port", "None");
           miners.put(0, miner);
           stmt.close();
           stmt = null;
           return miners;
       } else {
           stmt.close();
           stmt = null;
           return miners;
       }
       } catch (SQLException e) {
           System.out.println(e.getClass().getName() + ": " +e.getMessage());
           HashMap<String, String> miner = new HashMap<>();
           miner.put("ip", "none");
           miner.put("port", "none");
           miner.put("name", "none");
           miners.put(0, miner);
           return miners;
           
       }}
    /**
     * Returns HTML formatted list of current miners
     * @return String HTML of current miners
     */
    public String currentMiners() {
        StringBuffer miners = new StringBuffer("<html><table width=\"100%\" border=\"1\"><thead><tr><td>Name</td><td>IP:PORT</td></tr></thead>");
        if (c == null) {
            initDatabase();
        }
        try {
            String to_append;
            stmt = c.createStatement();
            String sql = "Select * from miners";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                to_append = "<tr><td>" + rs.getString("name")+"</td><td>"+rs.getString("ip")+":"+rs.getString("port")+"</td></tr>";
                miners.append(to_append);
            }
            miners.append("</table></html>");
            stmt.close();
            stmt = null;
            return miners.toString();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return e.getMessage();
        }
    }
    /**
     * Updates alarms for miner(s)
     * @param type alarm name in database
     * @param miner name of miner
     * @param value value of alarm
     * @return String Success or Fail
     */
    public String updateAlarms(String type, String miner, Object value) {
        if (c == null) {
            initDatabase();
        }
        try {
            stmt = c.createStatement();
            String sql_query = "SELECT miner FROM alarms WHERE miner='"+miner+"';";
            ResultSet rs = stmt.executeQuery(sql_query);
            if (rs.isBeforeFirst()) {
                String sql_update = "UPDATE alarms SET " + type + "='" + value +"' WHERE miner='" + miner + "';";
                stmt.executeUpdate(sql_update);
            } else {
                String sql_update = "INSERT INTO alarms (miner ," + type + ") VALUES ('" + miner + "', '" + value +"');";
                stmt.executeUpdate(sql_update);
            }
            stmt.close();
            stmt = null;
            return "Success!";
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Error updating alarm settings for miner: " + miner;
        }
    }
    /**
     * Returns current alarm values for miner
     * @param miner name of miner
     * @return HashMap<String, Object>
     */
    public HashMap<String, Object> getAlarms(String miner) {
        HashMap<String, Object> alarms = new HashMap<>();
        if (c == null) {
            initDatabase();
        }
        try {
            stmt = c.createStatement();
            String sql_query = "SELECT * FROM alarms WHERE miner='" + miner + "';";
            ResultSet rs = stmt.executeQuery(sql_query);
            if (!rs.isBeforeFirst()) {                       
                return null;
            } else {
                alarms.put("fan", rs.getFloat("fan"));
                alarms.put("temp", rs.getFloat("temp"));
                alarms.put("hash_rate", rs.getFloat("hash_rate"));
                alarms.put("hash_rate_type", rs.getString("hash_rate_type"));
                alarms.put("hw_err", rs.getFloat("hw_err"));
                return alarms;
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
    /**
     * Adds Eligius username if not in database
     * @param eligius_name Eligius username
     * @return String Success or Fail
     */    
    public String addEligius(String eligius_name) {
        if (c == null) {
            initDatabase();
        }   
        try {
            stmt = c.createStatement();
            String sql_query = "SELECT username FROM eligius_info";
            ResultSet rs = stmt.executeQuery(sql_query);
            while (rs.next()) {
                if (rs.getString("username").equals(eligius_name)) {
                    return "Username already in database";
                }
            }
            String sql_update = "INSERT INTO eligius_info (username) VALUES ('" + eligius_name + "');";
            stmt.executeUpdate(sql_update);
            stmt.close();
            stmt = null;
            return "Success!";
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Error adding " + eligius_name + " to configuration.";
        }
            
    }
    /**
     * Removes Eligius username if in database
     * @param eligius_name Eligius username
     * @return String Success or Fail
     */
    public String removeEligius(String eligius_name) {
        if (c == null) {
            initDatabase();
        }
        try {
            stmt = c.createStatement();
            String sql_delete = "DELETE FROM eligius_info WHERE username='" + eligius_name + "';";
            stmt.executeUpdate(sql_delete);
            stmt.close();
            stmt = null;
            return "Success!";
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Error removing " + eligius_name + " from configuration.";
        }
    }
    /**
     * Returns list of Eligius usernames
     * @return ArrayList<String>
     */
    public ArrayList<String> getEligius() {
        ArrayList<String> usernames = new ArrayList<>();
        if (c == null){
            initDatabase();            
        }
        try {
            stmt = c.createStatement();
            String eligius_info = "SELECT * FROM eligius_info";
            ResultSet rs = stmt.executeQuery(eligius_info);
            if (!rs.isBeforeFirst()) {
                usernames.add("Eligius not configured at this time");
                return usernames;
            }
            while (rs.next()) {
                String temp = rs.getString("username");              
                usernames.add(temp);
            }            
            stmt.close();
            stmt = null;
            return usernames;
        } catch (SQLException e) {
            usernames.add("ERROR");
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println(usernames.toString());
            return usernames; 
        }
    }
    /**
     * Determines if IP address is valid
     * @param ip string of IP address
     * @return boolean
     */
    public boolean validate(final String ip) {
        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
    /**
     * Determines if port is valid
     * @param port 
     * @return boolean
     */
    public boolean isPort(String port) {
        try {
            int value = Integer.parseInt(port);
            if (value > 65536 | value < 1) {
                return false;           
        }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}    