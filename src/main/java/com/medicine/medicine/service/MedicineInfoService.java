package com.medicine.medicine.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.medicine.dto.MedicineInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineInfoService {

    private final RestTemplate restTemplate;

    public List<MedicineInfoResponseDTO> getMedicineInfo(String itemName, String apiUrl) {
        String response = restTemplate.getForObject(apiUrl, String.class);

        List<MedicineInfoResponseDTO> medicineInfoList = new ArrayList<>();

        try {
            //JSON 파싱 시도
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

            JsonNode items = root.path("body").path("items");

            for (JsonNode item : items) {
                MedicineInfoResponseDTO dto = new MedicineInfoResponseDTO();
                dto.setEntpName(item.path("entpName").asText());
                dto.setItemName(item.path("itemName").asText());
                dto.setEfcyQesitm(item.path("efcyQesitm").asText());
                dto.setUseMethodQesitm(item.path("useMethodQesitm").asText());
                dto.setAtpnWarnQesitm(item.path("atpnWarnQesitm").asText(null));
                dto.setAtpnQesitm(item.path("atpnQesitm").asText());
                dto.setIntrcQesitm(item.path("intrcQesitm").asText(null));
                dto.setSeQesitm(item.path("seQesitm").asText(null));
                dto.setDepositMethodQesitm(item.path("depositMethodQesitm").asText());
                medicineInfoList.add(dto);
            }
        } catch (Exception e) {
            try {
                // JSON 파싱 실패 시 XML 파싱 시도
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(new StringReader(response)));

                String errMsg = doc.getElementsByTagName("errMsg").item(0).getTextContent();
                String returnAuthMsg = doc.getElementsByTagName("returnAuthMsg").item(0).getTextContent();
                String returnReasonCode = doc.getElementsByTagName("returnReasonCode").item(0).getTextContent();

                System.err.println("Error: " + errMsg + ", Message: " + returnAuthMsg + ", Code: " + returnReasonCode);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return medicineInfoList;
    }
}
