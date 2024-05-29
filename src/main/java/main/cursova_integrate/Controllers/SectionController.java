package main.cursova_integrate.Controllers;

import main.cursova_integrate.DAL.DTOs.SectionToCreate;
import main.cursova_integrate.DAL.Models.Section;
import main.cursova_integrate.DAL.Repositories.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private ISectionRepository sectionRepository;

    public SectionController() {}

    @GetMapping("/{id}")
    public ResponseEntity<List<Section>> get(@PathVariable UUID id) {
        try {
            List<Section> sections = sectionRepository.findAll();
            List<Section> searchedSection = new ArrayList<Section>();

            for (Section search : sections) {
                if (search.getUserId().equals(id)){
                    searchedSection.add(search);
                }
            }

            return new ResponseEntity<>(searchedSection, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("filter/{filter}")
    public ResponseEntity<List<Section>> filter(@PathVariable String filter) {
        try {
            List<Section> sections = sectionRepository.findAll();

            if(filter.equals("All")){
                return new ResponseEntity<>(sections, HttpStatus.OK);
            }

            List<Section> searchedSection = new ArrayList<Section>();

            for (Section search : sections) {
                if (search.getTitle().contains(filter) || search.getDetails().contains(filter)){
                    searchedSection.add(search);
                }
            }

            return new ResponseEntity<>(searchedSection, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Section>> getAll() {
        try {
            List<Section> sections = sectionRepository.findAll();

            return new ResponseEntity<>(sections, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity post(@RequestBody SectionToCreate section){
        try {
            Section newSection = new Section();

            newSection.UserId = section.UserId;
            newSection.Title = section.Title;
            newSection.Details = section.Details;
            newSection.Timestamp = new Timestamp(new Date().getTime());

            sectionRepository.save(newSection);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try {
            sectionRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity put(@PathVariable UUID id, @RequestBody Section section){
        try {
            Optional<Section> presence = sectionRepository.findById(id);

            if (presence.isPresent()) {
                sectionRepository.save(section);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
