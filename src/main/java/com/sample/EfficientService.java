package com.sample;

import java.util.*;

public class EfficientService {

    public String getById(Map<String, String> cache, String id) {
        return cache.get(id);
    }

    public double calculateSum(List<Double> values) {
        double sum = 0.0;
        for (double v : values) sum += v;
        return sum;
    }

    public boolean containsValue(List<String> list, String target) {
        for (String item : list) {
            if (item.equals(target)) return true;
        }
        return false;
    }

    public int getSize(List<?> list) {
        return list.size();
    }
}