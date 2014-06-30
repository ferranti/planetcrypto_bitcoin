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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Halcyon <support@planetcrypto.com A2A1A088>
 */


public class PlanetCryptoBitcoinCoinbaseCurrency {
    public static String[] coinbaseCurrencies() {
        ArrayList<String> currency_names = new ArrayList<>();
        LinkedHashMap<String, String> currencies = new LinkedHashMap<>();
        currencies.put("   ", "----- SELECT -----");
        currencies.put("DZD","Algerian Dinar (DZD)");
        currencies.put("NAD","Namibian Dollar (NAD)");
        currencies.put("KMF","Comorian Franc (KMF)");
        currencies.put("EGP","Egyptian Pound (EGP)");
        currencies.put("BGN","Bulgarian Lev (BGN)");
        currencies.put("PAB","Panamanian Balboa (PAB)");
        currencies.put("BOB","Bolivian Boliviano (BOB)");
        currencies.put("DKK","Danish Krone (DKK)");
        currencies.put("BWP","Botswana Pula (BWP)");
        currencies.put("LBP","Lebanese Pound (LBP)");
        currencies.put("TZS","Tanzanian Shilling (TZS)");
        currencies.put("VND","Vietnamese Đồng (VND)");
        currencies.put("AOA","Angolan Kwanza (AOA)");
        currencies.put("KHR","Cambodian Riel (KHR)");
        currencies.put("GHS","Ghanaian Cedi (GHS)");
        currencies.put("KYD","Cayman Islands Dollar (KYD)");
        currencies.put("LYD","Libyan Dinar (LYD)");
        currencies.put("UAH","Ukrainian Hryvnia (UAH)");
        currencies.put("JOD","Jordanian Dinar (JOD)");
        currencies.put("AWG","Aruban Florin (AWG)");
        currencies.put("SAR","Saudi Riyal (SAR)");
        currencies.put("EUR","Euro (EUR)");
        currencies.put("HKD","Hong Kong Dollar (HKD)");
        currencies.put("CHF","Swiss Franc (CHF)");
        currencies.put("GIP","Gibraltar Pound (GIP)");
        currencies.put("BYR","Belarusian Ruble (BYR)");
        currencies.put("XPF","Cfp Franc (XPF)");
        currencies.put("MRO","Mauritanian Ouguiya (MRO)");
        currencies.put("HRK","Croatian Kuna (HRK)");
        currencies.put("DJF","Djiboutian Franc (DJF)");
        currencies.put("SZL","Swazi Lilangeni (SZL)");
        currencies.put("THB","Thai Baht (THB)");
        currencies.put("XAF","Central African Cfa Franc (XAF)");
        currencies.put("BND","Brunei Dollar (BND)");
        currencies.put("ISK","Icelandic Króna (ISK)");
        currencies.put("UYU","Uruguayan Peso (UYU)");
        currencies.put("NIO","Nicaraguan Córdoba (NIO)");
        currencies.put("LAK","Lao Kip (LAK)");
        currencies.put("SYP","Syrian Pound (SYP)");
        currencies.put("MAD","Moroccan Dirham (MAD)");
        currencies.put("MZN","Mozambican Metical (MZN)");
        currencies.put("PHP","Philippine Peso (PHP)");
        currencies.put("ZAR","South African Rand (ZAR)");
        currencies.put("NPR","Nepalese Rupee (NPR)");
        currencies.put("ZWL","Zimbabwean Dollar (ZWL)");
        currencies.put("NGN","Nigerian Naira (NGN)");
        currencies.put("CRC","Costa Rican Colón (CRC)");
        currencies.put("AED","United Arab Emirates Dirham (AED)");
        currencies.put("EEK","Estonian Kroon (EEK)");
        currencies.put("MWK","Malawian Kwacha (MWK)");
        currencies.put("LKR","Sri Lankan Rupee (LKR)");
        currencies.put("PKR","Pakistani Rupee (PKR)");
        currencies.put("HUF","Hungarian Forint (HUF)");
        currencies.put("BMD","Bermudian Dollar (BMD)");
        currencies.put("LSL","Lesotho Loti (LSL)");
        currencies.put("MNT","Mongolian Tögrög (MNT)");
        currencies.put("AMD","Armenian Dram (AMD)");
        currencies.put("UGX","Ugandan Shilling (UGX)");
        currencies.put("QAR","Qatari Riyal (QAR)");
        currencies.put("KRW","South Korean Won (KRW)");
        currencies.put("JMD","Jamaican Dollar (JMD)");
        currencies.put("GEL","Georgian Lari (GEL)");
        currencies.put("SHP","Saint Helenian Pound (SHP)");
        currencies.put("AFN","Afghan Afghani (AFN)");
        currencies.put("SBD","Solomon Islands Dollar (SBD)");
        currencies.put("KPW","North Korean Won (KPW)");
        currencies.put("TRY","Turkish Lira (TRY)");
        currencies.put("BDT","Bangladeshi Taka (BDT)");
        currencies.put("YER","Yemeni Rial (YER)");
        currencies.put("HTG","Haitian Gourde (HTG)");
        currencies.put("XOF","West African Cfa Franc (XOF)");
        currencies.put("MGA","Malagasy Ariary (MGA)");
        currencies.put("ANG","Netherlands Antillean Gulden (ANG)");
        currencies.put("LRD","Liberian Dollar (LRD)");
        currencies.put("XCD","East Caribbean Dollar (XCD)");
        currencies.put("NOK","Norwegian Krone (NOK)");
        currencies.put("MOP","Macanese Pataca (MOP)");
        currencies.put("INR","Indian Rupee (INR)");
        currencies.put("MXN","Mexican Peso (MXN)");
        currencies.put("CZK","Czech Koruna (CZK)");
        currencies.put("TJS","Tajikistani Somoni (TJS)");
        currencies.put("BTN","Bhutanese Ngultrum (BTN)");
        currencies.put("COP","Colombian Peso (COP)");
        currencies.put("MYR","Malaysian Ringgit (MYR)");
        currencies.put("MUR","Mauritian Rupee (MUR)");
        currencies.put("IDR","Indonesian Rupiah (IDR)");
        currencies.put("HNL","Honduran Lempira (HNL)");
        currencies.put("FJD","Fijian Dollar (FJD)");
        currencies.put("ETB","Ethiopian Birr (ETB)");
        currencies.put("PEN","Peruvian Nuevo Sol (PEN)");
        currencies.put("BZD","Belize Dollar (BZD)");
        currencies.put("ILS","Israeli New Sheqel (ILS)");
        currencies.put("DOP","Dominican Peso (DOP)");
        currencies.put("TMM","Turkmenistani Manat (TMM)");
        currencies.put("TWD","New Taiwan Dollar (TWD)");
        currencies.put("MDL","Moldovan Leu (MDL)");
        currencies.put("BSD","Bahamian Dollar (BSD)");
        currencies.put("SEK","Swedish Krona (SEK)");
        currencies.put("ZMK","Zambian Kwacha (ZMK)");
        currencies.put("MVR","Maldivian Rufiyaa (MVR)");
        currencies.put("AUD","Australian Dollar (AUD)");
        currencies.put("SRD","Surinamese Dollar (SRD)");
        currencies.put("CUP","Cuban Peso (CUP)");
        currencies.put("BBD","Barbadian Dollar (BBD)");
        currencies.put("ALL","Albanian Lek (ALL)");
        currencies.put("GMD","Gambian Dalasi (GMD)");
        currencies.put("VEF","Venezuelan Bolívar (VEF)");
        currencies.put("GTQ","Guatemalan Quetzal (GTQ)");
        currencies.put("CLP","Chilean Peso (CLP)");
        currencies.put("LTL","Lithuanian Litas (LTL)");
        currencies.put("CDF","Congolese Franc (CDF)");
        currencies.put("RWF","Rwandan Franc (RWF)");
        currencies.put("KZT","Kazakhstani Tenge (KZT)");
        currencies.put("RUB","Russian Ruble (RUB)");
        currencies.put("TTD","Trinidad and Tobago Dollar (TTD)");
        currencies.put("OMR","Omani Rial (OMR)");
        currencies.put("BRL","Brazilian Real (BRL)");
        currencies.put("MMK","Myanmar Kyat (MMK)");
        currencies.put("PLN","Polish Złoty (PLN)");
        currencies.put("PYG","Paraguayan Guaraní (PYG)");
        currencies.put("KES","Kenyan Shilling (KES)");
        currencies.put("SVC","Salvadoran Colón (SVC)");
        currencies.put("MKD","Macedonian Denar (MKD)");
        currencies.put("GBP","British Pound (GBP)");
        currencies.put("AZN","Azerbaijani Manat (AZN)");
        currencies.put("TOP","Tongan Paʻanga (TOP)");
        currencies.put("VUV","Vanuatu Vatu (VUV)");
        currencies.put("GNF","Guinean Franc (GNF)");
        currencies.put("WST","Samoan Tala (WST)");
        currencies.put("IQD","Iraqi Dinar (IQD)");
        currencies.put("ERN","Eritrean Nakfa (ERN)");
        currencies.put("BAM","Bosnia and Herzegovina Convertible Mark (BAM)");
        currencies.put("SCR","Seychellois Rupee (SCR)");
        currencies.put("CAD","Canadian Dollar (CAD)");
        currencies.put("CVE","Cape Verdean Escudo (CVE)");
        currencies.put("KWD","Kuwaiti Dinar (KWD)");
        currencies.put("BIF","Burundian Franc (BIF)");
        currencies.put("PGK","Papua New Guinean Kina (PGK)");
        currencies.put("SOS","Somali Shilling (SOS)");
        currencies.put("SGD","Singapore Dollar (SGD)");
        currencies.put("UZS","Uzbekistani Som (UZS)");
        currencies.put("STD","São Tomé and Príncipe Dobra (STD)");
        currencies.put("IRR","Iranian Rial (IRR)");
        currencies.put("CNY","Chinese Renminbi Yuan (CNY)");
        currencies.put("SLL","Sierra Leonean Leone (SLL)");
        currencies.put("TND","Tunisian Dinar (TND)");
        currencies.put("GYD","Guyanese Dollar (GYD)");
        currencies.put("NZD","New Zealand Dollar (NZD)");
        currencies.put("FKP","Falkland Pound (FKP)");
        currencies.put("LVL","Latvian Lats (LVL)");
        currencies.put("USD","United States Dollar (USD)");
        currencies.put("KGS","Kyrgyzstani Som (KGS)");
        currencies.put("ARS","Argentine Peso (ARS)");
        currencies.put("RON","Romanian Leu (RON)");
        currencies.put("RSD","Serbian Dinar (RSD)");
        currencies.put("BHD","Bahraini Dinar (BHD)");
        currencies.put("JPY","Japanese Yen (JPY)");
        currencies.put("SDG","Sudanese Pound (SDG)");
        //System.out.println(currencies.size()+ " " + currencies.get("USD"));
        for (Map.Entry<String, String> pairs : currencies.entrySet()) {
            currency_names.add(pairs.getValue());                        
        }            
        //System.out.println("Values: " + currency_names);
        String[] buffer = new String[currency_names.size()];
        Collections.sort(currency_names);
        buffer = currency_names.toArray(buffer);
        return buffer;
    }
}
