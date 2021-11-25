package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
class DownloadFileController {
    @GetMapping("/download_file")
    @ResponseBody
    public String getFromApi(@RequestParam String string, @RequestParam String filetype, @RequestParam String downloadtype) {
        String getFileApiUrl = "http://localhost:8081/get_file?string=";
        getFileApiUrl = getFileApiUrl.concat(string).concat("&filetype=").concat(filetype);
        RestTemplate restTemplate = new RestTemplate();
        String apiResponse = restTemplate.getForObject(getFileApiUrl, String.class);

        HashMap convertedResponse = convertResponse(apiResponse, filetype);

        switch (downloadtype){
            case "json":
                return convertedResponse.toString();
            case "csv":
                String csvResponse = "specialChars, numbers, upperLetters, lowerLetters, otherChars\n";
                csvResponse = csvResponse.concat(String.valueOf(convertedResponse.get("specialChars"))).concat(",");
                csvResponse = csvResponse.concat(String.valueOf(convertedResponse.get("numbers"))).concat(",");
                csvResponse = csvResponse.concat(String.valueOf(convertedResponse.get("upperLetters"))).concat(",");
                csvResponse = csvResponse.concat(String.valueOf(convertedResponse.get("lowerLetters"))).concat(",");
                csvResponse = csvResponse.concat(String.valueOf(convertedResponse.get("otherChars")));
                return csvResponse;
            case "xml":
                String xmlResponse = "<?xml version=\"0.8\" encoding=\"UTF-8\" standalone=\"no\"?>";
                xmlResponse = xmlResponse.concat("<specialChars>").concat(String.valueOf(convertedResponse.get("specialChars"))).concat("</specialChars>");
                xmlResponse = xmlResponse.concat("<numbers>").concat(String.valueOf(convertedResponse.get("numbers"))).concat("</numbers>");
                xmlResponse = xmlResponse.concat("<upperLetters>").concat(String.valueOf(convertedResponse.get("upperLetters"))).concat("</upperLetters>");
                xmlResponse = xmlResponse.concat("<lowerLetters>").concat(String.valueOf(convertedResponse.get("lowerLetters"))).concat("</lowerLetters>");
                xmlResponse = xmlResponse.concat("<otherChars>").concat(String.valueOf(convertedResponse.get("otherChars"))).concat("</otherChars>");
                return xmlResponse;
            case "txt":
                String txtResponse = convertedResponse.toString().replace("{", "").replace("}", "").replace(",","\n");
                return txtResponse;
            default:
                return "Unsupported file type!";
        }
    }

    private HashMap convertResponse(String apiResponse, String filetype) {

        HashMap<String, Integer> convertedResponse = new HashMap<>();
        String tmp = "";

        switch (filetype){
            case "json":
            case "csv":
            case "txt":
                tmp = apiResponse.replaceAll("\\D+","");
                break;

            case "xml":
                tmp = apiResponse.substring(45).replaceAll("\\D+","");
                break;

        }
        convertedResponse.put("specialChars", Character.getNumericValue(tmp.charAt(0)));
        convertedResponse.put("numbers", Character.getNumericValue(tmp.charAt(1)));
        convertedResponse.put("upperLetters", Character.getNumericValue(tmp.charAt(2)));
        convertedResponse.put("lowerLetters", Character.getNumericValue(tmp.charAt(3)));
        convertedResponse.put("otherChars", Character.getNumericValue(tmp.charAt(4)));
        return convertedResponse;
    }
}
