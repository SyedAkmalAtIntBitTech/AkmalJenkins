/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 *
 * @author intbit
 */
public class GetColorFromImage extends Component {

        private static final Logger logger = Logger.getLogger(util.Utility.getClassName(GetColorFromImage.class));

    public static boolean ASC = true;
    public static boolean DESC = false;
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> colorMap;

    public String fetchColorForPixel(int pixel) {
        String hex = "";
        try {
            int red = (pixel & 0x00ff0000) >> 16;
            int green = (pixel & 0x0000ff00) >> 8;
            int blue = pixel & 0x000000ff;
            hex = String.format("#%02x%02x%02x", red, green, blue);
        } catch (Exception e) {
                      logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }
        return hex;
    }

    private ArrayList<String> marchThroughImage(BufferedImage image, int topNColors) {
        int w = image.getWidth();
        int h = image.getHeight();
        try {
            colorMap = new HashMap<>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    int pixel = image.getRGB(j, i);
                    String color = fetchColorForPixel(pixel);
                    Integer score = 0;
                    if (colorMap.containsKey(color)) {
                        score = colorMap.get(color);
                    }
                    colorMap.put(color, new Integer(score.intValue() + 1));
                }
            }

            System.out.println("After sorting ascending order......");
            Map<String, Integer> sortedMapAsc = sortByComparator(colorMap, ASC);
            // printMap(sortedMapAsc);

            ArrayList<String> topNColorsList = new ArrayList<String>(topNColors);
            int i = 0;
            for (Entry<String, Integer> entry : sortedMapAsc.entrySet()) {
                if (++i > topNColors) {
                    return topNColorsList;
                } else {
                    topNColorsList.add(entry.getKey());
                }
            }
        } catch (Exception e) {
                        logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }
        return null;
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> map, final boolean order) {
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        
        try{
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(
                map.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        for (Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        }catch (Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }
        return sortedMap;
    }

    public static void printMap(Map<String, Integer> sortedMapAsc) {
        try{
            for (Entry<String, Integer> entry : sortedMapAsc.entrySet()) {
                System.out.println("Key : " + entry.getKey() + " Value : "
                        + entry.getValue());
            }
        }catch(Exception e){
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

        }
    }

    public ArrayList getColors(String path) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            list = marchThroughImage(image, 10);

            for (String color : list) {
                System.out.println(color);
            }

        } catch (IOException e) {
                       logger.log(Level.SEVERE, util.Utility.logMessage(e, "Exception while updating org name:", null));

            
        }
        return list;
    }

}
