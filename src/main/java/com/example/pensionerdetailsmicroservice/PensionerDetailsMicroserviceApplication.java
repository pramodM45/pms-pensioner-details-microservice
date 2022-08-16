package com.example.pensionerdetailsmicroservice;

import com.example.pensionerdetailsmicroservice.model.BankDetails;
import com.example.pensionerdetailsmicroservice.model.BankType;
import com.example.pensionerdetailsmicroservice.model.PensionType;
import com.example.pensionerdetailsmicroservice.model.PensionerDetail;
import com.example.pensionerdetailsmicroservice.repo.BankDetailsRepo;
import com.example.pensionerdetailsmicroservice.repo.PensionerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class PensionerDetailsMicroserviceApplication implements CommandLineRunner {

    @Autowired
    PensionerDetailsRepo pensionerDetailsRepo;

    @Autowired
    BankDetailsRepo bankDetailsRepo;

    public static void main(String[] args) {
        SpringApplication.run(PensionerDetailsMicroserviceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/pensionerdata.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        while(line!=null){
            String[] lineSplit = line.split(",");
            BankDetails bankDetails = BankDetails.builder().bankName(lineSplit[7]).accountNumber(lineSplit[8])
                    .bankType(BankType.valueOf(lineSplit[9])).build();
            bankDetailsRepo.save(bankDetails);
            PensionerDetail pensionerDetail = PensionerDetail.builder().aadhaarNumber(lineSplit[0])
                    .name(lineSplit[1]).dateOfBirth(LocalDate.parse(lineSplit[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .PAN(lineSplit[3]).SalaryEarned(new BigDecimal(lineSplit[4]))
                    .allowences(new BigDecimal(lineSplit[5]))
                    .pensionType(PensionType.valueOf(lineSplit[6]))
                    .bankDetails(bankDetails).build();
            pensionerDetailsRepo.save(pensionerDetail);
            line = reader.readLine();
        }
    }
}
