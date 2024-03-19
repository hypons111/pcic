/*
 * Copyright (c) 2010-2020 MOI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of MOI.
 */
package gov.pcc.pwc.report;

import java.awt.font.TextAttribute;
import java.io.UnsupportedEncodingException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedCharacterIterator.Attribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.util.JRTextAttribute;

import org.apache.commons.lang3.StringUtils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;

/**
 * PDF輸出，並加入對難定的設定
 *
 * @author PaulChen
 */
public class PccPdfExporter extends JRPdfExporter {

    /*
     * (non-Javadoc)
     *
     * @see
     * net.sf.jasperreports.engine.export.JRPdfExporter#getPhrase(java.text.
     * AttributedString, java.lang.String,
     * net.sf.jasperreports.engine.JRPrintText)
     */
    @Override
    protected Phrase getPhrase(AttributedString as, String text, JRPrintText textElement) {
        Phrase phrase = new Phrase();
        int runLimit = 0;
        AttributedCharacterIterator iterator = as.getIterator();
        Locale locale = getTextLocale(textElement);

        boolean firstChunk = true;
        while ((runLimit < text.length()) && ((runLimit = iterator.getRunLimit()) <= text.length())) {
            Map<Attribute, Object> attributes = iterator.getAttributes();
            List<String[]> list = slitText(text.substring(iterator.getIndex(), runLimit));
            for (String[] texts : list) {
                Chunk chunk = getChunk(attributes, texts, locale);
                if (firstChunk) {
                    setAnchor(chunk, textElement, textElement);
                }
                JRPrintHyperlink hyperlink = textElement;
                if (hyperlink.getHyperlinkTypeValue() == HyperlinkTypeEnum.NONE) {
                    hyperlink = (JRPrintHyperlink) attributes.get(JRTextAttribute.HYPERLINK);
                }
                setHyperlinkInfo(chunk, hyperlink);
                phrase.add(chunk);
                firstChunk = false;
            }
            iterator.setIndex(runLimit);
        }
        return phrase;
    }

    /**
     * Gets the chunk.
     *
     * @param attributes
     *        the attributes
     * @param texts
     *        the texts
     * @param locale
     *        the locale
     * @return the chunk
     */
    private Chunk getChunk(Map<Attribute, Object> attributes, String[] texts, Locale locale) {
        Map<Attribute, Object> attrMap = new HashMap<Attribute, Object>(attributes);
        attrMap.put(TextAttribute.FAMILY, texts[0]);
        return getChunk(attrMap, texts[1], locale);
    }

    /**
     * 每個欄位的值，字串
     *
     * @param text
     *        字串
     * @return List<String[]>
     */
    private List<String[]> slitText(String text) {
        List<String[]> list = new ArrayList<String[]>();
        if (StringUtils.isEmpty(text))
            return list;
        try {
            byte[] bytes = text.getBytes("UTF-32BE");// 每個字拆成4個byte，byte[].length=4*字數
            StringBuffer sb = new StringBuffer();
            String lastFontName = "";
            int j = -1;
            for (int i = 0; i < bytes.length; i += 4) {
                byte b1 = bytes[i];
                byte b2 = bytes[(i + 1)];
                byte b3 = bytes[(i + 2)];
                byte b4 = bytes[(i + 3)];
                j++;

                if (StringUtils.equals(lastFontName, getFontName(b2))) {
                    sb.append(new String(new byte[] { b1, b2, b3, b4 }, "UTF-32BE"));
                } else {
                    if (i != 0) {
                        list.add(new String[] { lastFontName, sb.toString() });
                    }
                    sb = new StringBuffer(new String(new byte[] { b1, b2, b3, b4 }, "UTF-32BE"));
                    lastFontName = getFontName(b2);
                }
            }
            list.add(new String[] { lastFontName, sb.toString() });
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 取得字型檔名
     *
     * @param textByte
     *        第幾字面
     * @return textUnicode 這個單字的Unicode
     */
    private String getFontName(byte textByte) {
        if (textByte == 0) {
            return "中推會楷體0字面";
        }
        if (textByte == 2) {
            return "中推會楷體2字面";
        }
        if (textByte == 15) {
            return "中推會楷體F字面";
        }
        return "中推會宋體0字面";
    }
}
