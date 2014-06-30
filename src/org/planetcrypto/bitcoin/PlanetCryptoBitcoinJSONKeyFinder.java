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
import org.json.simple.parser.*;
import java.io.*;
/**
 *
 * @author halcyon
 */
public class PlanetCryptoBitcoinJSONKeyFinder implements ContentHandler {
    
    private Object value;
    private boolean found = false;
    private boolean end = false;
    private String key;
    private String matchKey;
        
    public void setMatchKey(String matchKey){
        this.matchKey = matchKey;
    }
        
    public Object getValue(){
        return value;
    }
        
    public boolean isEnd(){
        return end;
    }
        
    public void setFound(boolean found){
        this.found = found;
    }
        
    public boolean isFound(){
        return found;
    }
        
    /**
     *
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public void startJSON() throws ParseException, IOException {
        found = false;
        end = false;
    }

    /**
     *
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public void endJSON() throws ParseException, IOException {
        end = true;
    }

    /**
     *
     * @param value
     * @return boolean
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public boolean primitive(Object value) throws ParseException, IOException {
        if(key != null){
            if(key.equals(matchKey)){
                found = true;
                this.value = value;
                key = null;
                return false;
            }
        }
    return true;
    }

    /**
     *
     * @return boolean
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public boolean startArray() throws ParseException, IOException {
        return true;
    }

    /**
     *
     * @return boolean
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public boolean startObject() throws ParseException, IOException {
        return true;
    }

    /**
     *
     * @param key
     * @return boolean
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public boolean startObjectEntry(String key) throws ParseException, IOException {
        this.key = key;
        return true;
    }
        
    @Override
    public boolean endArray() throws ParseException, IOException {
        return false;
    }

    /**
     *
     * @return boolean
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public boolean endObject() throws ParseException, IOException {
        return true;
    }

    /**
     *
     * @return boolean
     * @throws ParseException
     * @throws IOException
     */
    @Override
    public boolean endObjectEntry() throws ParseException, IOException {
        return true;
    }
}