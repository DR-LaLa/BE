package com.medicine.medicine.controller;

import com.medicine.medicine.dto.MedicineInfoResponseDTO;
import com.medicine.medicine.service.MedicineInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MedicineController {

    private final MedicineInfoService medicineOpenApiService;

    @Value("${serviceKey}")
    String serviceKey;

    String getMedicineInfoBasicUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?";

    @GetMapping("/main/search/{itemName}")
    public List<MedicineInfoResponseDTO> getMedicineInfo(@PathVariable String itemName) {

        String apiUrl = getMedicineInfoBasicUrl + "ServiceKey=" + serviceKey + "&itemName=" + itemName + "&type=json";

        return medicineOpenApiService.getMedicineInfo(itemName,apiUrl);
    }
}
