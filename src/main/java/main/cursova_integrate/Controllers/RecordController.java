package main.cursova_integrate.Controllers;


import main.cursova_integrate.DAL.DTOs.RecordToCreate;
import main.cursova_integrate.DAL.Models.Record;
import main.cursova_integrate.DAL.Repositories.IRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private IRecordRepository recordRepository;

    public RecordController() {}

    @GetMapping("/{id}")
    public ResponseEntity<List<Record>> get(@PathVariable UUID id) {
        try {
            List<Record> sections = recordRepository.findAll();
            List<Record> searchedApartments = new ArrayList<Record>();

            for (Record search : sections) {
                if (search.getSectionId().equals(id)){
                    searchedApartments.add(search);
                }
            }

            return new ResponseEntity<>(searchedApartments, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity post(@RequestBody RecordToCreate record){
        try {
            Record newRecord = new Record();

            newRecord.SectionId = record.SectionId;
            newRecord.Title = record.Title;
            newRecord.Text = record.Text;
            newRecord.Timestamp = new Timestamp(new Date().getTime());

            recordRepository.save(newRecord);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try {
            recordRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity put(@PathVariable UUID id, @RequestBody Record record){
        try {
            Optional<Record> presence = recordRepository.findById(id);

            if (presence.isPresent()) {
                recordRepository.save(record);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
