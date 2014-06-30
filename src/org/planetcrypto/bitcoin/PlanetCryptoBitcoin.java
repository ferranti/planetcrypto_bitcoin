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
import org.planetcrypto.bitcoin.PlanetCryptoBitcoinUI;
import java.io.*;

/**
 *
 * @author halcyon
 */
public class PlanetCryptoBitcoin {
    public static void main(String[] args) throws Exception {
       File configDir = new File(System.getProperty("user.home")+"/.planetcrypto");
        if (!configDir.exists()) {
            boolean result = configDir.mkdir();
            if (result) {
                System.out.println(configDir.toString() + " Created as config Directory");  
                PlanetCryptoBitcoinUI.main(args);
            } else {
                System.out.println("Failed to create configuration directory at " + configDir.toString() + " Please ensure write permissions exist here");
                System.exit(1);
            }
        } else {
            //System.out.println("Configuration directory exists, continuing");         
            PlanetCryptoBitcoinUI.main(args);
        }
     }
}
