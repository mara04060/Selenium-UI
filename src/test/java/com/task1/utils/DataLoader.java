package com.task1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task1.selenium.config.Descriptions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataLoader {
    private List<Map<String, String>> products;
    public static final String FILE_PATH = "src/test/resources/data/dataProvider.json";

    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Download data in JSON-file
            List products = objectMapper.readValue(new File(FILE_PATH), List.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(Descriptions.ERROR_DOWNLOAD_FILE.getText());
        }
    }

    public DataLoader(List<Map<String, String>> products) {
        this.products = products;
    }

    public Object[][] getProducts() {
        Object[][] productData = new Object[products.size()][];

        for (int i = 0; i < products.size(); i++) {
            productData[i][0] = products.get(i).get("code");
            productData[i][1] = products.get(i).get("name");
        }

        return productData;
    }
}
